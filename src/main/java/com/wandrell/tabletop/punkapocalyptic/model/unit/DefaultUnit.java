/**
 * Copyright 2014 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.punkapocalyptic.model.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.punkapocalyptic.model.event.ValorationListener;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultArmor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class DefaultUnit implements Unit, MutantUnit {

    private final ValueBox                actions;
    private final ValueBox                agility;
    private Armor                         armor     = new DefaultArmor(
                                                            "unarmored",
                                                            0,
                                                            new LinkedList<SpecialRule>());
    private final Integer                 baseActions;
    private final Integer                 baseAgility;
    private final Integer                 baseCombat;
    private final Integer                 basePrecision;
    private final Integer                 baseStrength;
    private final Integer                 baseTech;
    private final Integer                 baseToughness;
    private final ValueBox                combat;
    private final Integer                 cost;
    private final DerivedValuesBuilder    derivedBuilder;
    private final Collection<Equipment>   equipment = new LinkedHashSet<Equipment>();
    private final EventListenerList       listeners = new EventListenerList();
    private final ValorationListener      listenerStatus;
    private final Collection<Mutation>    mutations = new LinkedHashSet<Mutation>();
    private final String                  name;
    private final ValueBox                precision;
    private final Collection<SpecialRule> rules     = new LinkedHashSet<SpecialRule>();
    private final ValueBox                strength;
    private final ValueBox                tech;
    private final ValueBox                toughness;
    private final ValueBox                valoration;
    private final Collection<Weapon>      weapons   = new LinkedHashSet<Weapon>();

    public interface DerivedValuesBuilder {

        public ValueBox getActions(final Integer baseValue, final Unit unit);

        public ValueBox getAgility(final Integer baseValue, final Unit unit);

        public ValueBox getCombat(final Integer baseValue, final Unit unit);

        public ValueBox getPrecision(final Integer baseValue, final Unit unit);

        public ValueBox getStrength(final Integer baseValue, final Unit unit);

        public ValueBox getTech(final Integer baseValue, final Unit unit);

        public ValueBox getToughness(final Integer baseValue, final Unit unit);

        public ValueBox getValoration(final Unit unit);

    }

    {
        listenerStatus = new ValorationListener() {

            @Override
            public void valorationChanged(final EventObject event) {
                fireValorationChangedEvent(new EventObject(this));
            }

        };
    }

    public DefaultUnit(final DefaultUnit unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        // TODO: Set the base armor in some other way

        name = unit.name;

        baseActions = unit.getBaseActions();
        baseAgility = unit.getBaseAgility();
        baseCombat = unit.getBaseCombat();
        basePrecision = unit.getBasePrecision();
        baseStrength = unit.getBaseStrength();
        baseTech = unit.getBaseTech();
        baseToughness = unit.getBaseToughness();

        cost = unit.cost;

        armor = unit.armor;

        for (final Equipment e : unit.equipment) {
            equipment.add(e);
        }

        for (final Weapon w : unit.weapons) {
            weapons.add(w);
        }

        for (final SpecialRule r : unit.rules) {
            rules.add(r);
        }

        derivedBuilder = unit.derivedBuilder;

        actions = derivedBuilder.getActions(getBaseActions(), this);
        agility = derivedBuilder.getAgility(getBaseAgility(), this);
        combat = derivedBuilder.getCombat(getBaseCombat(), this);
        precision = derivedBuilder.getPrecision(getBasePrecision(), this);
        strength = derivedBuilder.getStrength(getBaseStrength(), this);
        tech = derivedBuilder.getTech(getBaseTech(), this);
        toughness = derivedBuilder.getToughness(getBaseToughness(), this);

        valoration = derivedBuilder.getValoration(this);

        setAttributesListeners();
    }

    public DefaultUnit(final String name, final Integer actions,
            final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness, final Integer cost,
            final Collection<SpecialRule> rules,
            final DerivedValuesBuilder derivedBuilder) {
        super();

        checkNotNull(name, "Received a null pointer as name");

        checkNotNull(actions, "Received a null pointer as actions");
        checkNotNull(agility, "Received a null pointer as agility");
        checkNotNull(combat, "Received a null pointer as combat");
        checkNotNull(precision, "Received a null pointer as precision");
        checkNotNull(strength, "Received a null pointer as strength");
        checkNotNull(tech, "Received a null pointer as tech");
        checkNotNull(toughness, "Received a null pointer as toughness");

        checkNotNull(rules, "Received a null pointer as special rules");

        checkNotNull(cost, "Received a null pointer as cost");

        checkNotNull(derivedBuilder,
                "Received a null pointer as valoration builder");

        this.name = name;

        this.baseActions = actions;
        this.baseAgility = agility;
        this.baseCombat = combat;
        this.basePrecision = precision;
        this.baseStrength = strength;
        this.baseTech = tech;
        this.baseToughness = toughness;

        this.cost = cost;

        this.derivedBuilder = derivedBuilder;

        this.actions = derivedBuilder.getActions(getBaseActions(), this);
        this.agility = derivedBuilder.getAgility(getBaseAgility(), this);
        this.combat = derivedBuilder.getCombat(getBaseCombat(), this);
        this.precision = derivedBuilder.getPrecision(getBasePrecision(), this);
        this.strength = derivedBuilder.getStrength(getBaseStrength(), this);
        this.tech = derivedBuilder.getTech(getBaseTech(), this);
        this.toughness = derivedBuilder.getToughness(getBaseToughness(), this);

        this.valoration = derivedBuilder.getValoration(this);

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            getSpecialRulesModifiable().add(rule);
        }

        setAttributesListeners();
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        checkNotNull(equipment, "Received a null pointer as equipment");

        getEquipmentModifiable().add(equipment);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void addMutation(final Mutation mutation) {
        getMutationsModifiable().add(mutation);

        fireValorationChangedEvent(new EventObject(this));
        fireMutationChangedEvent(new EventObject(this));
    }

    @Override
    public final void addUnitListener(final UnitListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(UnitListener.class, listener);
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        checkNotNull(weapon, "Received a null pointer as weapon");

        getWeaponsModifiable().add(weapon);

        weapon.addValorationListener(getStatusListener());

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void clearEquipment() {
        getEquipmentModifiable().clear();

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void clearMutations() {
        getMutationsModifiable().clear();
    }

    @Override
    public final void clearWeapons() {
        getWeaponsModifiable().clear();

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final DefaultUnit createNewInstance() {
        return new DefaultUnit(this);
    }

    @Override
    public final Integer getActions() {
        return getActionsValueBox().getValue();
    }

    @Override
    public final Integer getAgility() {
        return getAgilityValueBox().getValue();
    }

    @Override
    public final Armor getArmor() {
        return armor;
    }

    public final Integer getBaseActions() {
        return baseActions;
    }

    public final Integer getBaseAgility() {
        return baseAgility;
    }

    public final Integer getBaseCombat() {
        return baseCombat;
    }

    @Override
    public final Integer getBaseCost() {
        return cost;
    }

    public final Integer getBasePrecision() {
        return basePrecision;
    }

    public final Integer getBaseStrength() {
        return baseStrength;
    }

    public final Integer getBaseTech() {
        return baseTech;
    }

    public final Integer getBaseToughness() {
        return baseToughness;
    }

    @Override
    public final Integer getCombat() {
        return getCombatValueBox().getValue();
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return Collections.unmodifiableCollection(getEquipmentModifiable());
    }

    @Override
    public final Collection<Mutation> getMutations() {
        return Collections.unmodifiableCollection(getMutationsModifiable());
    }

    @Override
    public final Integer getPrecision() {
        return getPrecisionValueBox().getValue();
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final Integer getStrength() {
        return getStrengthValueBox().getValue();
    }

    @Override
    public final Integer getTech() {
        return getTechValueBox().getValue();
    }

    @Override
    public final Integer getToughness() {
        return getToughnessValueBox().getValue();
    }

    @Override
    public final String getUnitName() {
        return name;
    }

    @Override
    public final Integer getValoration() {
        return getValorationValueBox().getValue();
    }

    @Override
    public final Collection<Weapon> getWeapons() {
        return Collections.unmodifiableCollection(getWeaponsModifiable());
    }

    @Override
    public final void removeEquipment(final Equipment equipment) {
        getEquipmentModifiable().remove(equipment);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void removeMutation(final Mutation mutation) {
        getMutationsModifiable().remove(mutation);

        fireValorationChangedEvent(new EventObject(this));
        fireMutationChangedEvent(new EventObject(this));
    }

    @Override
    public final void removeUnitListener(final UnitListener listener) {
        getListeners().remove(UnitListener.class, listener);
    }

    @Override
    public final void removeWeapon(final Weapon weapon) {
        checkNotNull(armor, "Received a null pointer as weapon");

        getWeaponsModifiable().remove(weapon);

        weapon.removeValorationListener(getStatusListener());

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void setArmor(final Armor armor) {
        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor;

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final void fireActionsChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.actionsChanged(evt);
        }
    }

    private final void fireAgilityChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.agilityChanged(evt);
        }
    }

    private final void fireCombatChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.combatChanged(evt);
        }
    }

    private final void fireMutationChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.mutationChanged(evt);
        }
    }

    private final void firePrecisionChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.precisionChanged(evt);
        }
    }

    private final void fireStrengthChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.strengthChanged(evt);
        }
    }

    private final void fireTechChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.techChanged(evt);
        }
    }

    private final void fireToughnessChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.toughnessChanged(evt);
        }
    }

    private final void fireValorationChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final ValueBox getActionsValueBox() {
        return actions;
    }

    private final ValueBox getAgilityValueBox() {
        return agility;
    }

    private final ValueBox getCombatValueBox() {
        return combat;
    }

    private final Collection<Equipment> getEquipmentModifiable() {
        return equipment;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<Mutation> getMutationsModifiable() {
        return mutations;
    }

    private final ValueBox getPrecisionValueBox() {
        return precision;
    }

    private final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

    private final ValorationListener getStatusListener() {
        return listenerStatus;
    }

    private final ValueBox getStrengthValueBox() {
        return strength;
    }

    private final ValueBox getTechValueBox() {
        return tech;
    }

    private final ValueBox getToughnessValueBox() {
        return toughness;
    }

    private final ValueBox getValorationValueBox() {
        return valoration;
    }

    private final Collection<Weapon> getWeaponsModifiable() {
        return weapons;
    }

    private final void setAttributesListeners() {
        getActionsValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireActionsChangedEvent(event);
            }

        });
        getAgilityValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireAgilityChangedEvent(event);
            }

        });
        getCombatValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireCombatChangedEvent(event);
            }

        });
        getPrecisionValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        firePrecisionChangedEvent(event);
                    }

                });
        getStrengthValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireStrengthChangedEvent(event);
            }

        });
        getTechValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireTechChangedEvent(event);
            }

        });
        getToughnessValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        fireToughnessChangedEvent(event);
                    }

                });
    }

}
