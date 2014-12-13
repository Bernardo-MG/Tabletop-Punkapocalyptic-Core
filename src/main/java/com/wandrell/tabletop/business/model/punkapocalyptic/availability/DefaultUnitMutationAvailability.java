package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Mutation;

public final class DefaultUnitMutationAvailability implements
        UnitMutationAvailability {

    private final Integer              max;
    private final Collection<Mutation> mutations = new LinkedHashSet<>();

    public DefaultUnitMutationAvailability(final Integer maxMutations,
            final Collection<Mutation> mutationOptions) {
        super();

        checkNotNull(maxMutations, "Received a null pointer as max mutations");
        checkNotNull(mutationOptions, "Received a null pointer as mutations");

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
        return Objects.equal(max, other.max)
                && Objects.equal(mutations, other.mutations);
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
    public final int hashCode() {
        return Objects.hashCode(max, mutations);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("max", max)
                .add("mutations", mutations).toString();
    }

    private final Collection<Mutation> getMutationOptionsModifiable() {
        return mutations;
    }

}
