package com.wandrell.tabletop.model.punkapocalyptic.ruleset;

import java.util.Iterator;

import com.wandrell.tabletop.model.punkapocalyptic.unit.Band;
import com.wandrell.tabletop.model.punkapocalyptic.unit.Unit;

public final class UniqueUnitConstraint implements UnitConstraint {

    private final String message;
    private final String unit;

    public UniqueUnitConstraint(final String unit, final String message) {
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
        Boolean valid;

        itr = band.getUnits().iterator();
        valid = true;
        while ((itr.hasNext()) && (valid)) {
            valid = (!itr.next().getUnitName().equals(getUnit()));
        }

        return valid;
    }

    protected final String getMessageTemplate() {
        return message;
    }

    protected final String getUnit() {
        return unit;
    }

}
