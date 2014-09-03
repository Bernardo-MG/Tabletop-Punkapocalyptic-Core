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
import java.util.LinkedHashSet;

import com.wandrell.tabletop.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;
import com.wandrell.tabletop.valuehandler.ValueHandler;

public final class DefaultAvailabilityUnit implements AvailabilityUnit {

    private final Collection<Armor>  armorOptions  = new LinkedHashSet<>();
    private final Integer            maxWeapons;
    private final Integer            minWeapons;
    private final Unit               unit;
    private final Collection<Weapon> weaponOptions = new LinkedHashSet<>();

    public DefaultAvailabilityUnit(final DefaultAvailabilityUnit unit) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as unit");
        }

        this.unit = unit.unit.createNewInstance();

        armorOptions.addAll(unit.armorOptions);
        weaponOptions.addAll(unit.weaponOptions);

        minWeapons = unit.minWeapons;
        maxWeapons = unit.maxWeapons;
    }

    public DefaultAvailabilityUnit(final Unit unit,
            final Collection<Armor> armorOptions,
            final Collection<Weapon> weaponOptions, final Integer minWeapons,
            final Integer maxWeapons) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as unit");
        }

        if (armorOptions == null) {
            throw new NullPointerException("Received a null pointer as armors");
        }

        if (weaponOptions == null) {
            throw new NullPointerException("Received a null pointer as weapons");
        }

        if (maxWeapons == null) {
            throw new NullPointerException(
                    "Received a null pointer as maximum weapons");
        }

        if (minWeapons == null) {
            throw new NullPointerException(
                    "Received a null pointer as minimum weapons");
        }

        this.unit = unit;

        this.maxWeapons = maxWeapons;
        this.minWeapons = minWeapons;

        for (final Armor armor : armorOptions) {
            if (armor == null) {
                throw new NullPointerException(
                        "Received a null pointer as armor");
            }

            this.armorOptions.add(armor);
        }

        for (final Weapon weapon : weaponOptions) {
            if (weapon == null) {
                throw new NullPointerException(
                        "Received a null pointer as weapon");
            }

            this.weaponOptions.add(weapon);
        }
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        if (equipment == null) {
            throw new NullPointerException(
                    "Received a null pointer as equipment");
        }

        getUnit().addEquipment(equipment);
    }

    @Override
    public final void addRule(final SpecialRule rule) {
        if (rule == null) {
            throw new NullPointerException("Received a null pointer as rule");
        }

        getUnit().addRule(rule);
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        if (weapon == null) {
            throw new NullPointerException("Received a null pointer as weapon");
        }

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
    public final boolean equals(final Object obj) {
        return getUnit().equals(obj);
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
    public final Integer getBaseCost() {
        return getUnit().getBaseCost();
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
    public final Integer getMaxWeapons() {
        return maxWeapons;
    }

    @Override
    public final Integer getMaxWeaponSlots() {
        return getUnit().getMaxWeaponSlots();
    }

    @Override
    public final Integer getMinWeapons() {
        return minWeapons;
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
    public final String getUnitName() {
        return getUnit().getUnitName();
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
    public final int hashCode() {
        return getUnit().hashCode();
    }

    @Override
    public final void setArmor(final Armor armor) {
        getUnit().setArmor(armor);
    }

    @Override
    public final String toString() {
        return getUnit().toString();
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
