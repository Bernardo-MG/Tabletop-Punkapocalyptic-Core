package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import java.util.Iterator;

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

        this.count = count;

        this.serviceLocalization = serviceLocalization;
    }

    public UnitUpToACountConstraint(final UnitUpToACountConstraint constraint) {
        super();

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
    public final Boolean isValid(final Gang band) {
        final Iterator<Unit> itr;
        Integer number;

        itr = band.getUnits().iterator();
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
        message = null;
        this.unit = unit;
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
