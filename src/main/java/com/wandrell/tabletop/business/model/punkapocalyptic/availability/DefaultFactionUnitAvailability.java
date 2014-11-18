package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.procedure.Constraint;

public final class DefaultFactionUnitAvailability implements
        FactionUnitAvailability {

    private final Collection<Constraint> constraints = new LinkedHashSet<>();
    private final Unit                   unit;

    public DefaultFactionUnitAvailability(final Unit unit,
            final Collection<Constraint> constraints) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(constraints, "Received a null pointer as constraints");

        this.unit = unit;

        for (final Constraint constraint : constraints) {
            checkNotNull(constraint, "Received a null pointer as a constraint");

            this.constraints.add(constraint);
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
        return Objects.equals(unit, other.unit)
                && Objects.equals(constraints, other.constraints);
    }

    @Override
    public final Collection<Constraint> getConstraints() {
        return Collections.unmodifiableCollection(getConstraintsModifiable());
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(constraints, unit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit)
                .add("constraints", constraints).toString();
    }

    private final Collection<Constraint> getConstraintsModifiable() {
        return constraints;
    }

}
