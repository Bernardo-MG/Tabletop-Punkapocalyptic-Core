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
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.mutation.Mutation;

public final class DefaultUnitMutationAvailability implements
        UnitMutationAvailability {

    private final Integer              max;
    private final Collection<Mutation> mutations = new LinkedHashSet<Mutation>();
    private final Unit                 unit;

    public DefaultUnitMutationAvailability(final Unit unit,
            final Integer maxMutations,
            final Collection<Mutation> mutationOptions) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(maxMutations, "Received a null pointer as max mutations");
        checkNotNull(mutationOptions, "Received a null pointer as mutations");

        this.unit = unit;

        max = maxMutations;
        for (final Mutation mutation : mutationOptions) {
            checkNotNull(mutation, "Received a null pointer as mutation");

            this.mutations.add(mutation);
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

        DefaultUnitMutationAvailability other;

        other = (DefaultUnitMutationAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Integer getMaxMutations() {
        return max;
    }

    @Override
    public final Collection<Mutation> getMutationOptions() {
        return Collections
                .unmodifiableCollection(getMutationOptionsModifiable());
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
                .add("max", max).add("mutations", mutations).toString();
    }

    private final Collection<Mutation> getMutationOptionsModifiable() {
        return mutations;
    }

}
