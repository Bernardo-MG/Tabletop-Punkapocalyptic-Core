package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import java.util.Iterator;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;

public final class UnitUpToHalfPointsLimitConstraint extends
        AbstractGangConstraint {

    public UnitUpToHalfPointsLimitConstraint(final String unit,
            final String message) {
        super(unit, message);
    }

    @Override
    public final Boolean isValid(final Gang gang) {
        final Iterator<Unit> itr;
        Unit unit;
        Integer points;

        itr = gang.getUnits().iterator();
        points = 0;
        while (itr.hasNext()) {
            unit = itr.next();
            if (unit.getUnitName().equals(getUnit())) {
                points = unit.getValoration().getStoredValue();
            }
        }

        return (points <= (gang.getValoration().getStoredValue() / 2));
    }

}
