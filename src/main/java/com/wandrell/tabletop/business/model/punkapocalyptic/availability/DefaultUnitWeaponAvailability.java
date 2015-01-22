/**
 * Copyright 2015 the original author or authors
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
package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class DefaultUnitWeaponAvailability implements
        UnitWeaponAvailability {

    private final Integer                  maxWeapons;
    private final Integer                  minWeapons;
    private final Unit                     unit;
    private final Collection<WeaponOption> weaponOptions = new LinkedHashSet<>();

    public DefaultUnitWeaponAvailability(final Unit unit,
            final Collection<WeaponOption> weapons, final Integer min,
            final Integer max) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(weapons, "Received a null pointer as weapon options");
        checkNotNull(min, "Received a null pointer as min weapons");
        checkNotNull(max, "Received a null pointer as max weapons");

        this.unit = unit;

        this.minWeapons = min;
        this.maxWeapons = max;

        for (final WeaponOption weapon : weapons) {
            checkNotNull(weapon, "Received a null pointer as weapon");

            this.weaponOptions.add(weapon);
        }

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
        return Objects.equal(unit, other.unit);
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
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final Collection<WeaponOption> getWeaponOptions() {
        return Collections.unmodifiableCollection(getWeaponOptionsModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit.getUnitName())
                .add("min", minWeapons).add("max", maxWeapons)
                .add("weapons", weaponOptions).toString();
    }

    private final Collection<WeaponOption> getWeaponOptionsModifiable() {
        return weaponOptions;
    }

}
