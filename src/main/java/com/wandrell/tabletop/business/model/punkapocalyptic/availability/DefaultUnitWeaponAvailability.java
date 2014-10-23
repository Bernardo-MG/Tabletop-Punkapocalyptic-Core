package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;

public final class DefaultUnitWeaponAvailability implements
        UnitWeaponAvailability {

    private final Integer            maxWeapons;
    private final Integer            minWeapons;
    private final Collection<Weapon> weaponOptions = new LinkedHashSet<>();

    public DefaultUnitWeaponAvailability(
            final Collection<Weapon> weaponOptions, final Integer minWeapons,
            final Integer maxWeapons) {
        super();

        checkNotNull(weaponOptions, "Received a null pointer as weapon options");
        checkNotNull(minWeapons, "Received a null pointer as min weapons");
        checkNotNull(maxWeapons, "Received a null pointer as max weapons");

        this.maxWeapons = maxWeapons;
        this.minWeapons = minWeapons;

        for (final Weapon weapon : weaponOptions) {
            checkNotNull(weapon, "Received a null pointer as weapon");

            this.weaponOptions.add(weapon);
        }

    }

    public DefaultUnitWeaponAvailability(
            final DefaultUnitWeaponAvailability availability) {
        super();

        checkNotNull(availability,
                "Received a null pointer as weapon availability");

        weaponOptions.addAll(availability.weaponOptions);

        minWeapons = availability.minWeapons;
        maxWeapons = availability.maxWeapons;
    }

    @Override
    public final Integer getMaxWeapons() {
        return maxWeapons;
    }

    @Override
    public final Integer getMinWeapons() {
        return minWeapons;
    }

    @Override
    public final Collection<Weapon> getWeaponOptions() {
        return Collections.unmodifiableCollection(getWeaponOptionsModifiable());
    }

    private final Collection<Weapon> getWeaponOptionsModifiable() {
        return weaponOptions;
    }

}
