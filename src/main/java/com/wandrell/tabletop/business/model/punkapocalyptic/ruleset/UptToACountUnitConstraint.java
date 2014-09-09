package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset;

import java.util.Iterator;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Band;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class UptToACountUnitConstraint extends AbstractUnitConstraint {

    private final Integer count;

    public UptToACountUnitConstraint(final String unit, final String message,
            final Integer count) {
        super(unit, message);

        this.count = count;
    }

    @Override
    public final Boolean isValid(final Band band) {
        final Iterator<Unit> itr;
        Integer number;

        itr = band.getUnits().iterator();
        number = 0;
        while ((itr.hasNext()) && (number <= getCount())) {
            if (!itr.next().getUnitName().equals(getUnit())) {
                number++;
            }
        }

        return (number <= getCount());
    }

    protected final Integer getCount() {
        return count;
    }

}
