package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

abstract class AbstractGangConstraint implements GangConstraint {

    private final String message;
    private final String unit;

    public AbstractGangConstraint(final String unit, final String message) {
        super();

        this.unit = unit;
        this.message = message;
    }

    @Override
    public final String getErrorMessage() {
        return message;
    }

    protected final String getUnit() {
        return unit;
    }

}
