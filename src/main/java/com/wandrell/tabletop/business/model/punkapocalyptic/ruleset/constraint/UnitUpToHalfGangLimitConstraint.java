package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.conf.punkapocalyptic.MessageBundleKey;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.service.punkapocalyptic.LocalizationService;

public final class UnitUpToHalfGangLimitConstraint implements
        UnitGangConstraint {

    private String                    message;
    private final LocalizationService serviceLocalization;
    private String                    unit;

    public UnitUpToHalfGangLimitConstraint(
            final LocalizationService serviceLocalization) {
        super();

        checkNotNull(serviceLocalization,
                "Received a null pointer as localization service");

        this.serviceLocalization = serviceLocalization;
    }

    public UnitUpToHalfGangLimitConstraint(
            final UnitUpToHalfGangLimitConstraint constraint) {
        super();

        checkNotNull(constraint, "Received a null pointer as constraint");

        serviceLocalization = constraint.serviceLocalization;
    }

    @Override
    public final UnitUpToHalfGangLimitConstraint createNewInstance() {
        return new UnitUpToHalfGangLimitConstraint(this);
    }

    @Override
    public final String getErrorMessage() {
        if (message == null) {
            message = String.format(
                    getLocalizationService().getMessageString(
                            MessageBundleKey.UNIT_SHOULD_BE_UP_TO_HALF_GANG),
                    getLocalizationService().getUnitNameString(getUnit()));
        }

        return message;
    }

    @Override
    public final Boolean isValid(final Gang gang) {
        final Iterator<Unit> itr;
        Unit unit;
        Integer count;

        checkNotNull(gang, "Received a null pointer as gang");

        itr = gang.getUnits().iterator();
        count = 0;
        while (itr.hasNext()) {
            unit = itr.next();
            if (unit.getUnitName().equals(getUnit())) {
                count++;
            }
        }

        return (count <= (gang.getUnits().size() / 2));
    }

    @Override
    public final void setUnit(final String unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        message = null;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit).toString();
    }

    private final LocalizationService getLocalizationService() {
        return serviceLocalization;
    }

    private final String getUnit() {
        return unit;
    }

}
