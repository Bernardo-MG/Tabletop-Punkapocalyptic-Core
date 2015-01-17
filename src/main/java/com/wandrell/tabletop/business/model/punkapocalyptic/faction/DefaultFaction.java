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
package com.wandrell.tabletop.business.model.punkapocalyptic.faction;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.availability.FactionUnitAvailability;

public final class DefaultFaction implements Faction {

    private final String                              factionName;
    private final Collection<FactionUnitAvailability> units = new LinkedHashSet<>();

    public DefaultFaction(final DefaultFaction faction) {
        super();

        checkNotNull(faction, "Received a null pointer as faction");

        factionName = faction.factionName;
    }

    public DefaultFaction(final String name) {
        super();

        checkNotNull(name, "Received a null pointer as name");

        this.factionName = name;
    }

    @Override
    public final void addUnit(final FactionUnitAvailability unit) {
        checkNotNull(unit, "Received a null pointer as unit availability");

        getUnitsModifiable().add(unit);
    }

    @Override
    public final void clearUnits() {
        getUnitsModifiable().clear();
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

        final DefaultFaction other;

        other = (DefaultFaction) obj;
        return Objects.equals(factionName, other.factionName);
    }

    @Override
    public final String getName() {
        return factionName;
    }

    @Override
    public final Collection<FactionUnitAvailability> getUnits() {
        return Collections.unmodifiableCollection(getUnitsModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(factionName);
    }

    @Override
    public final void removeUnit(final FactionUnitAvailability unit) {
        getUnitsModifiable().remove(unit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", factionName)
                .toString();
    }

    private final Collection<FactionUnitAvailability> getUnitsModifiable() {
        return units;
    }

}
