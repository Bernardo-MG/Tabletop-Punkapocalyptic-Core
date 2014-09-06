package com.wandrell.tabletop.model.punkapocalyptic.ruleset;

import java.util.Iterator;

import com.wandrell.tabletop.model.punkapocalyptic.unit.Band;
import com.wandrell.tabletop.model.punkapocalyptic.unit.Unit;

public final class UpToHalfPointsLimitUnitConstraint implements UnitConstraint {

    private final String message;
    private final String unit;

    public UpToHalfPointsLimitUnitConstraint(final String unit,
            final String message) {
        super();

        this.unit = unit;
        this.message = message;
    }

    @Override
    public final String getErrorMessage() {
        return String.format(getMessageTemplate(), getUnit());
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

        return (points <= (band.getValoration().getStoredValue()));
    }

    protected final String getMessageTemplate() {
        return message;
    }

    protected final String getUnit() {
        return unit;
    }

}
