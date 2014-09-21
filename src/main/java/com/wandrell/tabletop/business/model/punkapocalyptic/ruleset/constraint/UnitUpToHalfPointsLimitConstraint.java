package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import java.util.Iterator;

import com.wandrell.tabletop.business.conf.punkapocalyptic.MessageBundleKey;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.service.punkapocalyptic.LocalizationService;

public final class UnitUpToHalfPointsLimitConstraint implements
        UnitGangConstraint {

    private String                    message;
    private final LocalizationService serviceLocalization;
    private String                    unit;

    public UnitUpToHalfPointsLimitConstraint(
            final LocalizationService serviceLocalization) {
        super();

        this.serviceLocalization = serviceLocalization;
    }

    public UnitUpToHalfPointsLimitConstraint(
            final UnitUpToHalfPointsLimitConstraint constraint) {
        super();

        serviceLocalization = constraint.serviceLocalization;
    }

    @Override
    public final UnitUpToHalfPointsLimitConstraint createNewInstance() {
        return new UnitUpToHalfPointsLimitConstraint(this);
    }

    @Override
    public final String getErrorMessage() {
        if (message == null) {
            message = String.format(
                    getLocalizationService().getMessageString(
                            MessageBundleKey.UNIT_SHOULD_BE_UP_TO_HALF_POINTS),
                    getLocalizationService().getUnitNameString(getUnit()));
        }

        return message;
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

    @Override
    public final void setUnit(final String unit) {
        this.unit = unit;
    }

    protected final LocalizationService getLocalizationService() {
        return serviceLocalization;
    }

    protected final String getUnit() {
        return unit;
    }

}
