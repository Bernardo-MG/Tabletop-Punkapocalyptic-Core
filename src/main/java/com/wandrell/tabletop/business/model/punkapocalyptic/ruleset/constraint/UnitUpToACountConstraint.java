package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class UnitUpToACountConstraint implements GangConstraint {

    private final Integer count;
    private final String  message;
    private final String  name;
    private final String  unit;

    public UnitUpToACountConstraint(final String name, final String unit,
            final Integer count, final String message) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(count, "Received a null pointer as count");
        checkNotNull(message, "Received a null pointer as message");

        this.name = name;
        this.unit = unit;
        this.count = count;
        this.message = message;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final UnitUpToACountConstraint other;

        other = (UnitUpToACountConstraint) obj;
        return Objects.equals(name, other.name)
                && Objects.equals(unit, other.unit)
                && Objects.equals(count, other.count);
    }

    @Override
    public final String getErrorMessage() {
        return message;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, unit, count);
    }

    @Override
    public final Boolean isValid(final Gang gang) {
        final Iterator<Unit> itr;
        Integer number;

        checkNotNull(gang, "Received a null pointer as gang");

        itr = gang.getUnits().iterator();
        number = 0;
        while ((itr.hasNext()) && (number <= getCount())) {
            if (itr.next().getUnitName().equals(getUnit())) {
                number++;
            }
        }

        return (number <= getCount());
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("unit", unit).add("count", count).toString();
    }

    private final Integer getCount() {
        return count;
    }

    private final String getUnit() {
        return unit;
    }

}
