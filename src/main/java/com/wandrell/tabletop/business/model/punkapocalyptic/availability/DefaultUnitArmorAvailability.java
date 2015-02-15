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
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class DefaultUnitArmorAvailability implements
        UnitArmorAvailability {

    private final Collection<Armor> armorOptions = new LinkedHashSet<Armor>();
    private final Armor             initialArmor;
    private final Unit              unit;

    public DefaultUnitArmorAvailability(final Unit unit,
            final Collection<Armor> armors, final Armor initial) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(armors, "Received a null pointer as armor options");
        checkNotNull(initial, "Received a null pointer as initial armor");

        this.unit = unit;
        this.initialArmor = initial;

        for (final Armor armor : armors) {
            checkNotNull(armor, "Received a null pointer as armor");

            this.armorOptions.add(armor);
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

        DefaultUnitArmorAvailability other;

        other = (DefaultUnitArmorAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Collection<Armor> getArmorOptions() {
        return Collections.unmodifiableCollection(getArmorOptionsModifiable());
    }

    @Override
    public final Armor getInitialArmor() {
        return initialArmor;
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit.getUnitName())
                .add("initial", initialArmor).add("armors", armorOptions)
                .toString();
    }

    private final Collection<Armor> getArmorOptionsModifiable() {
        return armorOptions;
    }

}
