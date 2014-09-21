package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import java.util.Iterator;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class UnitUpToACountConstraint extends AbstractGangConstraint {

    private final Integer count;

    public UnitUpToACountConstraint(final String unit, final String message,
            final Integer count) {
        super(unit, message);

        this.count = count;
    }

    @Override
    public final Boolean isValid(final Gang band) {
        final Iterator<Unit> itr;
        Integer number;

        itr = band.getUnits().iterator();
        number = 0;
        while ((itr.hasNext()) && (number <= getCount())) {
            if (itr.next().getUnitName().equals(getUnit())) {
                number++;
            }
        }

        return (number <= getCount());
    }

    protected final Integer getCount() {
        return count;
    }

}
