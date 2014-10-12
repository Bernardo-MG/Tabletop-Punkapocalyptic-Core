package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.conf.punkapocalyptic.MessageBundleKey;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.service.punkapocalyptic.LocalizationService;

public final class UnitUpToACountConstraint implements UnitGangConstraint {

    private final Integer             count;
    private String                    message;
    private final LocalizationService serviceLocalization;
    private String                    unit;

    public UnitUpToACountConstraint(final Integer count,
            final LocalizationService serviceLocalization) {
        super();

        checkNotNull(count, "Received a null pointer as count");
        checkNotNull(serviceLocalization,
                "Received a null pointer as localization service");

        this.count = count;

        this.serviceLocalization = serviceLocalization;
    }

    public UnitUpToACountConstraint(final UnitUpToACountConstraint constraint) {
        super();

        checkNotNull(constraint, "Received a null pointer as constraint");

        count = constraint.count;

        serviceLocalization = constraint.serviceLocalization;
    }

    @Override
    public final UnitUpToACountConstraint createNewInstance() {
        return new UnitUpToACountConstraint(this);
    }

    @Override
    public final String getErrorMessage() {
        if (message == null) {
            message = String.format(
                    getLocalizationService().getMessageString(
                            MessageBundleKey.UNIT_SHOULD_BE_UNIQUE),
                    getLocalizationService().getUnitNameString(getUnit()));
        }

        return message;
    }

    @Override
    public final Boolean isValid(final Gang gang) {
        final Iterator<Unit> itr;
        Integer number;

        checkNotNull(gang, "Received a null pointer as gang");

        itr = gang.getUnits().iterator();
        number = 0;
        while ((itr.hasNext()) && (number <= getCount())) {
            if (itr.next().getUnitName().equals(getUnit())) {
                number++;
            }
        }

        return (number <= getCount());
    }

    @Override
    public final void setUnit(final String unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        message = null;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit)
                .add("count", count).toString();
    }

    private final Integer getCount() {
        return count;
    }

    private final LocalizationService getLocalizationService() {
        return serviceLocalization;
    }

    private final String getUnit() {
        return unit;
    }

}
