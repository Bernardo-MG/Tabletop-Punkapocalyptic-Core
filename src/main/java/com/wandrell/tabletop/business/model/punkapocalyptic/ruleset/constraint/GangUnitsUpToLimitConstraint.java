package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;

public final class GangUnitsUpToLimitConstraint implements GangConstraint {

    private final String       message;
    private final ValueHandler unitsLimit;

    public GangUnitsUpToLimitConstraint(final ValueHandler unitsLimit,
            final String message) {
        super();

        checkNotNull(unitsLimit, "Received a null pointer as units limit");
        checkNotNull(message, "Received a null pointer as message");

        this.unitsLimit = unitsLimit;
        this.message = message;
    }

    @Override
    public final String getErrorMessage() {
        return message;
    }

    @Override
    public final Boolean isValid(final Gang gang) {
        return (gang.getUnits().size() < getUnitsLimit());
    }

    private final Integer getUnitsLimit() {
        return unitsLimit.getValue();
    }

}
