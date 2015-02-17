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
package com.wandrell.tabletop.punkapocalyptic.model.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class DefaultFactionUnitAvailability implements
        FactionUnitAvailability {

    private final Unit                   availUnit;
    private final Faction                faction;
    private final Collection<Constraint> unitConstrn = new LinkedHashSet<Constraint>();

    public DefaultFactionUnitAvailability(final Faction faction,
            final Unit unit, final Collection<Constraint> constraints) {
        super();

        checkNotNull(faction, "Received a null pointer as faction");
        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(constraints, "Received a null pointer as constraints");

        this.faction = faction;
        this.availUnit = unit;

        for (final Constraint constraint : constraints) {
            checkNotNull(constraint, "Received a null pointer as a constraint");

            this.unitConstrn.add(constraint);
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

        DefaultFactionUnitAvailability other;

        other = (DefaultFactionUnitAvailability) obj;
        return Objects.equals(availUnit, other.availUnit)
                && Objects.equals(unitConstrn, other.unitConstrn);
    }

    @Override
    public final Collection<Constraint> getConstraints() {
        return Collections.unmodifiableCollection(getConstraintsModifiable());
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Unit getUnit() {
        return availUnit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unitConstrn, availUnit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", availUnit)
                .add("constraints", unitConstrn).toString();
    }

    private final Collection<Constraint> getConstraintsModifiable() {
        return unitConstrn;
    }

}
