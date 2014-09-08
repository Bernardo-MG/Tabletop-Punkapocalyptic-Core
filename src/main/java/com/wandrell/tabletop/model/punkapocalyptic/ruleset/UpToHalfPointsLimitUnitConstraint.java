package com.wandrell.tabletop.model.punkapocalyptic.ruleset;

import java.util.Iterator;

import com.wandrell.tabletop.model.punkapocalyptic.unit.Band;
import com.wandrell.tabletop.model.punkapocalyptic.unit.Unit;

public final class UpToHalfPointsLimitUnitConstraint extends
        AbstractUnitConstraint {

    public UpToHalfPointsLimitUnitConstraint(final String unit,
            final String message) {
        super(unit, message);
    }

    @Override
    public final Boolean isValid(final Band band) {
        final Iterator<Unit> itr;
        Unit unit;
        Integer points;

        itr = band.getUnits().iterator();
        points = 0;
        while (itr.hasNext()) {
            unit = itr.next();
            if (unit.getUnitName().equals(getUnit())) {
                points = unit.getValoration().getStoredValue();
            }
        }

        return (points <= (band.getValoration().getStoredValue() / 2));
    }

}
