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
package com.wandrell.tabletop.model.punkapocalyptic.faction;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.model.punkapocalyptic.unit.AvailabilityUnit;

public final class DefaultFaction implements Faction {

    private final String                       name;
    private final Collection<AvailabilityUnit> units = new LinkedHashSet<>();

    public DefaultFaction(final DefaultFaction faction) {
        super();

        if (faction == null) {
            throw new NullPointerException("Received a null pointer as faction");
        }

        name = faction.name;

        for (final AvailabilityUnit unit : faction.units) {
            units.add(unit);
        }
    }

    public DefaultFaction(final String name,
            final Collection<AvailabilityUnit> units) {
        super();

        if (name == null) {
            throw new NullPointerException("Received a null pointer as name");
        }

        if (units == null) {
            throw new NullPointerException(
                    "Received a null pointer as units list");
        }

        this.name = name;

        for (final AvailabilityUnit unit : units) {
            if (unit == null) {
                throw new NullPointerException(
                        "Received a null pointer as unit");
            }

            this.units.add(unit);
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

        DefaultFaction other = (DefaultFaction) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

    @Override
    public final Collection<AvailabilityUnit> getAvailableUnits() {
        return Collections
                .unmodifiableCollection(getAvailableUnitsModifiable());
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }

    @Override
    public final String toString() {
        return getName();
    }

    protected final Collection<AvailabilityUnit> getAvailableUnitsModifiable() {
        return units;
    }

}
