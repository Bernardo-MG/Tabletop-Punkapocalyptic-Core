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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;
import com.wandrell.tabletop.business.model.valuehandler.AbstractValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.DefaultValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.module.generator.DefaultGenerator;
import com.wandrell.tabletop.business.model.valuehandler.module.interval.DefaultIntervalModule;
import com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic.UnitValorationStore;
import com.wandrell.tabletop.business.model.valuehandler.module.validator.IntervalValidator;

public final class DefaultUnit implements Unit {

    private final ValueHandler            actions;
    private final ValueHandler            agility;
    private Armor                         armor;
    private final ValueHandler            combat;
    private final Integer                 cost;
    private final Collection<Equipment>   equipment      = new LinkedHashSet<>();
    private Integer                       maxWeaponSlots = 2;
    private final String                  name;
    private final ValueHandler            precision;
    private final Collection<SpecialRule> rules          = new LinkedHashSet<>();
    private final ValueHandler            strength;
    private final ValueHandler            tech;
    private final ValueHandler            toughness;
    private final ValueHandler            valoration;
    private final Collection<Weapon>      weapons        = new LinkedHashSet<>();
    private final ValueHandler            weaponSlots;

    public DefaultUnit(final DefaultUnit unit) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as band");
        }

        name = unit.name;

        actions = unit.actions.createNewInstance();
        agility = unit.agility.createNewInstance();
        combat = unit.combat.createNewInstance();
        precision = unit.precision.createNewInstance();
        strength = unit.strength.createNewInstance();
        tech = unit.tech.createNewInstance();
        toughness = unit.toughness.createNewInstance();

        weaponSlots = unit.weaponSlots.createNewInstance();

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

        valoration = new DefaultValueHandler("valoration",
                new DefaultGenerator(), new DefaultIntervalModule(),
                new UnitValorationStore(this), new IntervalValidator());
    }

    public DefaultUnit(final String name, final ValueHandler actions,
            final ValueHandler agility, final ValueHandler combat,
            final ValueHandler precision, final ValueHandler strength,
            final ValueHandler tech, final ValueHandler toughness,
            final ValueHandler weaponSlots, final Integer cost,
            final Collection<SpecialRule> rules) {
        super();

        if (name == null) {
            throw new NullPointerException("Received a null pointer as name");
        }

        if (actions == null) {
            throw new NullPointerException("Received a null pointer as actions");
        }

        if (agility == null) {
            throw new NullPointerException("Received a null pointer as agility");
        }

        if (combat == null) {
            throw new NullPointerException("Received a null pointer as agility");
        }

        if (precision == null) {
            throw new NullPointerException(
                    "Received a null pointer as precision");
        }

        if (strength == null) {
            throw new NullPointerException(
                    "Received a null pointer as strength");
        }

        if (tech == null) {
            throw new NullPointerException("Received a null pointer as tech");
        }

        if (toughness == null) {
            throw new NullPointerException(
                    "Received a null pointer as toughness");
        }

        if (weaponSlots == null) {
            throw new NullPointerException(
                    "Received a null pointer as weaponSlots");
        }

        if (cost == null) {
            throw new NullPointerException("Received a null pointer as cost");
        }

        this.name = name;

        this.actions = actions;
        this.agility = agility;
        this.combat = combat;
        this.precision = precision;
        this.strength = strength;
        this.tech = tech;
        this.toughness = toughness;

        this.weaponSlots = weaponSlots;

        this.cost = cost;

        valoration = new DefaultValueHandler("valoration",
                new DefaultGenerator(), new DefaultIntervalModule(),
                new UnitValorationStore(this), new IntervalValidator());

        for (final SpecialRule rule : rules) {
            if (rule == null) {
                throw new NullPointerException(
                        "Received a null pointer as rule");
            }

            _getSpecialRules().add(rule);
        }
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        if (equipment == null) {
            throw new NullPointerException(
                    "Received a null pointer as equipment");
        }

        _getEquipment().add(equipment);

        ((AbstractValueHandler) getValoration())
                .fireValueChangedEvent(new ValueHandlerEvent(getValoration()));
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        if (weapon == null) {
            throw new NullPointerException("Received a null pointer as weapon");
        }

        if (getFreeWeaponSlots().getStoredValue() >= weapon.getHands()) {
            _getWeapons().add(weapon);
            getFreeWeaponSlots().addValue(-weapon.getHands());

            ((AbstractValueHandler) getValoration())
                    .fireValueChangedEvent(new ValueHandlerEvent(
                            getValoration()));
        }
    }

    @Override
    public final void clearEquipment() {
        _getEquipment().clear();

        ((AbstractValueHandler) getValoration())
                .fireValueChangedEvent(new ValueHandlerEvent(getValoration()));
    }

    @Override
    public final void clearRules() {
        _getSpecialRules().clear();
    }

    @Override
    public final void clearWeapons() {
        _getWeapons().clear();

        getFreeWeaponSlots().setValue(getMaxWeaponSlots());

        ((AbstractValueHandler) getValoration())
                .fireValueChangedEvent(new ValueHandlerEvent(getValoration()));
    }

    @Override
    public final DefaultUnit createNewInstance() {
        return new DefaultUnit(this);
    }

    @Override
    public final ValueHandler getActions() {
        return actions;
    }

    @Override
    public final ValueHandler getAgility() {
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
    public final ValueHandler getCombat() {
        return combat;
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return Collections.unmodifiableCollection(_getEquipment());
    }

    @Override
    public final ValueHandler getFreeWeaponSlots() {
        return weaponSlots;
    }

    @Override
    public final Integer getMaxWeaponSlots() {
        return maxWeaponSlots;
    }

    @Override
    public final ValueHandler getPrecision() {
        return precision;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(_getSpecialRules());
    }

    @Override
    public final ValueHandler getStrength() {
        return strength;
    }

    @Override
    public final ValueHandler getTech() {
        return tech;
    }

    @Override
    public final ValueHandler getToughness() {
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
        return Collections.unmodifiableCollection(_getWeapons());
    }

    @Override
    public final void setArmor(final Armor armor) {
        this.armor = armor;

        ((AbstractValueHandler) getValoration())
                .fireValueChangedEvent(new ValueHandlerEvent(getValoration()));
    }

    @Override
    public final String toString() {
        return getUnitName();
    }

    protected final Collection<Equipment> _getEquipment() {
        return equipment;
    }

    protected final Collection<SpecialRule> _getSpecialRules() {
        return rules;
    }

    protected final Collection<Weapon> _getWeapons() {
        return weapons;
    }

}
