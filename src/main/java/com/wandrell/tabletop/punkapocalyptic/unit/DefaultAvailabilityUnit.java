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

import com.wandrell.tabletop.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;
import com.wandrell.tabletop.valuehandler.ValueHandler;

public final class DefaultAvailabilityUnit implements AvailabilityUnit {

    private final Collection<Armor>  armorOptions;
    private final Unit               unit;
    private final Collection<Weapon> weaponOptions;

    public DefaultAvailabilityUnit(final DefaultAvailabilityUnit unit) {
        super();

        this.unit = unit.unit.createNewInstance();
        armorOptions = unit.armorOptions;
        weaponOptions = unit.weaponOptions;
    }

    public DefaultAvailabilityUnit(final Unit unit,
            final Collection<Armor> armorOptions,
            final Collection<Weapon> weaponOptions) {
        super();

        this.unit = unit;
        this.armorOptions = armorOptions;
        this.weaponOptions = weaponOptions;
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        getUnit().addEquipment(equipment);
    }

    @Override
    public final void addRule(final SpecialRule rule) {
        getUnit().addRule(rule);
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        getUnit().addWeapon(weapon);
    }

    @Override
    public final void clearEquipment() {
        getUnit().clearEquipment();
    }

    @Override
    public final void clearRules() {
        getUnit().clearRules();
    }

    @Override
    public final void clearWeapons() {
        getUnit().clearWeapons();
    }

    @Override
    public final DefaultAvailabilityUnit createNewInstance() {
        return new DefaultAvailabilityUnit(this);
    }

    @Override
    public final ValueHandler getActions() {
        return getUnit().getActions();
    }

    @Override
    public final ValueHandler getAgility() {
        return getUnit().getAgility();
    }

    @Override
    public final Armor getArmor() {
        return getUnit().getArmor();
    }

    @Override
    public final Collection<Armor> getArmorOptions() {
        return Collections.unmodifiableCollection(getArmorOptionsModifiable());
    }

    @Override
    public final ValueHandler getCombat() {
        return getUnit().getCombat();
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return getUnit().getEquipment();
    }

    @Override
    public final ValueHandler getFreeWeaponSlots() {
        return getUnit().getFreeWeaponSlots();
    }

    @Override
    public final String getName() {
        return getUnit().getName();
    }

    @Override
    public final ValueHandler getPrecision() {
        return getUnit().getPrecision();
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getUnit().getSpecialRules();
    }

    @Override
    public final ValueHandler getStrength() {
        return getUnit().getStrength();
    }

    @Override
    public final ValueHandler getTech() {
        return getUnit().getTech();
    }

    @Override
    public final ValueHandler getToughness() {
        return getUnit().getToughness();
    }

    @Override
    public final ValueHandler getValoration() {
        return getUnit().getValoration();
    }

    @Override
    public final Collection<Weapon> getWeaponOptions() {
        return Collections.unmodifiableCollection(getWeaponOptionsModifiable());
    }

    @Override
    public final Collection<Weapon> getWeapons() {
        return getUnit().getWeapons();
    }

    @Override
    public final void setArmor(final Armor armor) {
        getUnit().setArmor(armor);
    }

    protected final Collection<Armor> getArmorOptionsModifiable() {
        return armorOptions;
    }

    protected final Unit getUnit() {
        return unit;
    }

    protected final Collection<Weapon> getWeaponOptionsModifiable() {
        return weaponOptions;
    }

}
