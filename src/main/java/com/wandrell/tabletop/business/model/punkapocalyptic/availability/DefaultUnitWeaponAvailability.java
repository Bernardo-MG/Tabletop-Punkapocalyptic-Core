package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class DefaultUnitWeaponAvailability implements
        UnitWeaponAvailability {

    private final Integer                  maxWeapons;
    private final Integer                  minWeapons;
    private final Collection<WeaponOption> weaponOptions = new LinkedHashSet<>();

    public DefaultUnitWeaponAvailability(
            final Collection<WeaponOption> weaponOptions,
            final Integer minWeapons, final Integer maxWeapons) {
        super();

        checkNotNull(weaponOptions, "Received a null pointer as weapon options");
        checkNotNull(minWeapons, "Received a null pointer as min weapons");
        checkNotNull(maxWeapons, "Received a null pointer as max weapons");

        this.minWeapons = minWeapons;
        this.maxWeapons = maxWeapons;

        for (final WeaponOption weapon : weaponOptions) {
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
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DefaultUnitWeaponAvailability other;

        other = (DefaultUnitWeaponAvailability) obj;
        return Objects.equal(minWeapons, other.minWeapons)
                && Objects.equal(maxWeapons, other.maxWeapons)
                && Objects.equal(weaponOptions, other.weaponOptions);
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
    public final Collection<WeaponOption> getWeaponOptions() {
        return Collections.unmodifiableCollection(getWeaponOptionsModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(minWeapons, maxWeapons, weaponOptions);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("min", minWeapons)
                .add("max", maxWeapons).add("weapons", weaponOptions)
                .toString();
    }

    private final Collection<WeaponOption> getWeaponOptionsModifiable() {
        return weaponOptions;
    }

}
