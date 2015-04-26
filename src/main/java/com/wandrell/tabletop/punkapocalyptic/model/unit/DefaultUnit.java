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
import com.wandrell.tabletop.punkapocalyptic.model.event.ValorationListener;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultArmor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.AttributesListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.UnitBonusAttributesHolder;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class DefaultUnit implements Unit, MutantUnit {

    private Armor                         armor     = new DefaultArmor(
                                                            "unarmored",
                                                            0,
                                                            new LinkedList<SpecialRule>());
    private final AttributesHolder        attributes;
    private final DerivedValuesBuilder    derivedBuilder;
    private final Collection<Equipment>   equipment = new LinkedHashSet<Equipment>();
    private final EventListenerList       listeners = new EventListenerList();
    private final ValorationListener      listenerStatus;
    private final Collection<Mutation>    mutations = new LinkedHashSet<Mutation>();
    private final Collection<SpecialRule> rules     = new LinkedHashSet<SpecialRule>();
    private final UnitTemplate            template;
    private final ValueBox                valoration;
    private final Collection<Weapon>      weapons   = new LinkedHashSet<Weapon>();

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

        template = unit.template;
        attributes = new UnitBonusAttributesHolder(this);

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

        valoration = derivedBuilder.getValoration(this);

        setAttributesListeners();
    }

    public DefaultUnit(final UnitTemplate template,
            final Collection<SpecialRule> rules,
            final DerivedValuesBuilder derivedBuilder) {
        super();

        checkNotNull(template, "Received a null pointer as template");

        checkNotNull(rules, "Received a null pointer as special rules");

        checkNotNull(derivedBuilder,
                "Received a null pointer as valoration builder");

        this.template = template;

        this.derivedBuilder = derivedBuilder;

        attributes = new UnitBonusAttributesHolder(this);

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
    public final Armor getArmor() {
        return armor;
    }

    @Override
    public final AttributesHolder getAttributes() {
        return attributes;
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
    public final String getName() {
        return getUnitTemplate().getNameToken();
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final UnitTemplate getUnitTemplate() {
        return template;
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
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final void fireActionsChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.actionsChanged(evt);
        }
    }

    private final void fireAgilityChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.agilityChanged(evt);
        }
    }

    private final void fireCombatChangedEvent(final ValueChangeEvent evt) {
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

    private final void firePrecisionChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.precisionChanged(evt);
        }
    }

    private final void fireStrengthChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.strengthChanged(evt);
        }
    }

    private final void fireTechChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.techChanged(evt);
        }
    }

    private final void fireToughnessChangedEvent(final ValueChangeEvent evt) {
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

    private final Collection<Equipment> getEquipmentModifiable() {
        return equipment;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<Mutation> getMutationsModifiable() {
        return mutations;
    }

    private final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

    private final ValorationListener getStatusListener() {
        return listenerStatus;
    }

    private final ValueBox getValorationValueBox() {
        return valoration;
    }

    private final Collection<Weapon> getWeaponsModifiable() {
        return weapons;
    }

    private final void setAttributesListeners() {
        getAttributes().addAttributesListener(new AttributesListener() {

            @Override
            public final void actionsChanged(final ValueChangeEvent event) {
                fireActionsChangedEvent(event);
            }

            @Override
            public final void agilityChanged(final ValueChangeEvent event) {
                fireAgilityChangedEvent(event);
            }

            @Override
            public final void combatChanged(final ValueChangeEvent event) {
                fireCombatChangedEvent(event);
            }

            @Override
            public final void precisionChanged(final ValueChangeEvent event) {
                firePrecisionChangedEvent(event);
            }

            @Override
            public final void strengthChanged(final ValueChangeEvent event) {
                fireStrengthChangedEvent(event);
            }

            @Override
            public final void techChanged(final ValueChangeEvent event) {
                fireTechChangedEvent(event);
            }

            @Override
            public final void toughnessChanged(final ValueChangeEvent event) {
                fireToughnessChangedEvent(event);
            }

        });
    }

}
