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
package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.event.ValorationListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.UnarmoredArmor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;
import com.wandrell.tabletop.business.model.valuehandler.ModularDerivedValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;
import com.wandrell.tabletop.business.util.tag.punkapocalyptic.UnitAware;

public final class DefaultUnit implements Unit {

    private final Integer                 actions;
    private final Integer                 agility;
    private Armor                         armor     = new UnarmoredArmor();
    private final Integer                 combat;
    private final Integer                 cost;
    private final Collection<Equipment>   equipment = new LinkedHashSet<>();
    private final EventListenerList       listeners = new EventListenerList();
    private final ValorationListener      listenerStatus;
    private final String                  name;
    private final Integer                 precision;
    private final Collection<SpecialRule> rules     = new LinkedHashSet<>();
    private final Integer                 strength;
    private final Integer                 tech;
    private final Integer                 toughness;
    private final ValueHandler            valoration;
    private final Collection<Weapon>      weapons   = new LinkedHashSet<>();

    {
        listenerStatus = new ValorationListener() {

            @Override
            public void valorationChanged(final EventObject e) {
                fireValorationChangedEvent(new EventObject(this));
            }

        };
    }

    public DefaultUnit(final DefaultUnit unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        name = unit.name;

        actions = unit.actions;
        agility = unit.agility;
        combat = unit.combat;
        precision = unit.precision;
        strength = unit.strength;
        tech = unit.tech;
        toughness = unit.toughness;

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

        valoration = unit.valoration.createNewInstance();
        // TODO: Do in another way
        ((UnitAware) ((ModularDerivedValueHandler) valoration).getStore())
                .setUnit(this);
    }

    public DefaultUnit(final String name, final Integer actions,
            final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness, final Integer cost,
            final Collection<SpecialRule> rules, final ValueHandler valoration) {
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

        this.name = name;

        this.actions = actions;
        this.agility = agility;
        this.combat = combat;
        this.precision = precision;
        this.strength = strength;
        this.tech = tech;
        this.toughness = toughness;

        this.cost = cost;

        this.valoration = valoration;

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            getSpecialRulesModifiable().add(rule);
        }
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        checkNotNull(equipment, "Received a null pointer as equipment");

        getEquipmentModifiable().add(equipment);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void addValorationListener(final ValorationListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(ValorationListener.class, listener);
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
        return actions;
    }

    @Override
    public final Integer getAgility() {
        return agility;
    }

    @Override
    public final Armor getArmor() {
        return armor;
    }

    @Override
    public final Integer getBaseCost() {
        return cost;
    }

    @Override
    public final Integer getCombat() {
        return combat;
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return Collections.unmodifiableCollection(getEquipmentModifiable());
    }

    @Override
    public final Integer getPrecision() {
        return precision;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final Integer getStrength() {
        return strength;
    }

    @Override
    public final Integer getTech() {
        return tech;
    }

    @Override
    public final Integer getToughness() {
        return toughness;
    }

    @Override
    public final String getUnitName() {
        return name;
    }

    @Override
    public final ValueHandler getValoration() {
        return valoration;
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
    public final void
            removeValorationListener(final ValorationListener listener) {
        getListeners().remove(ValorationListener.class, listener);
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

    private final void fireValorationChangedEvent(final EventObject evt) {
        final ValorationListener[] listnrs;

        listnrs = getListeners().getListeners(ValorationListener.class);
        for (final ValorationListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final Collection<Equipment> getEquipmentModifiable() {
        return equipment;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

    private final ValorationListener getStatusListener() {
        return listenerStatus;
    }

    private final Collection<Weapon> getWeaponsModifiable() {
        return weapons;
    }

}
