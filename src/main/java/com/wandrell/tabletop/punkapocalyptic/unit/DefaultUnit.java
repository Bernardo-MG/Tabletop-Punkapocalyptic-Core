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
package com.wandrell.tabletop.punkapocalyptic.unit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.wandrell.tabletop.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;
import com.wandrell.tabletop.valuehandler.ValueHandler;

public final class DefaultUnit implements Unit {

    private final ValueHandler            actions;
    private final ValueHandler            agility;
    private Armor                         armor;
    private final ValueHandler            combat;
    private final Collection<Equipment>   equipment = new LinkedList<>();
    private final String                  name;
    private final ValueHandler            precision;
    private final Collection<SpecialRule> rules     = new LinkedList<>();
    private final ValueHandler            strength;
    private final ValueHandler            tech;
    private final ValueHandler            toughness;
    private final Collection<Weapon>      weapons   = new LinkedList<>();
    private final ValueHandler            weaponSlots;

    public DefaultUnit(final DefaultUnit unit) {
        super();

        name = unit.name;

        actions = unit.actions.createNewInstance();
        agility = unit.agility.createNewInstance();
        combat = unit.combat.createNewInstance();
        precision = unit.precision.createNewInstance();
        strength = unit.strength.createNewInstance();
        tech = unit.tech.createNewInstance();
        toughness = unit.toughness.createNewInstance();

        weaponSlots = unit.weaponSlots.createNewInstance();

        for (final Equipment e : unit.equipment) {
            equipment.add(e);
        }

        for (final Weapon w : unit.weapons) {
            weapons.add(w);
        }

        for (final SpecialRule r : unit.rules) {
            rules.add(r);
        }
    }

    public DefaultUnit(final String name, final ValueHandler actions,
            final ValueHandler agility, final ValueHandler combat,
            final ValueHandler precision, final ValueHandler strength,
            final ValueHandler tech, final ValueHandler toughness,
            final ValueHandler weaponSlots) {
        super();

        this.name = name;

        this.actions = actions;
        this.agility = agility;
        this.combat = combat;
        this.precision = precision;
        this.strength = strength;
        this.tech = tech;
        this.toughness = toughness;

        this.weaponSlots = weaponSlots;
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        _getEquipment().add(equipment);
    }

    @Override
    public final void addRule(final SpecialRule rule) {
        _getSpecialRules().add(rule);
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        _getWeapons().add(weapon);
    }

    @Override
    public final void clearEquipment() {
        _getEquipment().clear();
    }

    @Override
    public final void clearRules() {
        _getSpecialRules().clear();
    }

    @Override
    public final void clearWeapons() {
        _getWeapons().clear();
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
    public final String getName() {
        return name;
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
    public final ValueHandler getValoration() {
        // TODO
        return null;
    }

    @Override
    public final Collection<Weapon> getWeapons() {
        return Collections.unmodifiableCollection(_getWeapons());
    }

    @Override
    public final void setArmor(final Armor armor) {
        this.armor = armor;
    }

    @Override
    public final String toString() {
        return getName();
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
