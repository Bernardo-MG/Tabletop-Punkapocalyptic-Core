package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset;

import java.util.Iterator;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class UpToHalfPointsLimitUnitConstraint extends
        AbstractUnitConstraint {

    public UpToHalfPointsLimitUnitConstraint(final String unit,
            final String message) {
        super(unit, message);
    }

    @Override
    public final Boolean isValid(final Gang band) {
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
