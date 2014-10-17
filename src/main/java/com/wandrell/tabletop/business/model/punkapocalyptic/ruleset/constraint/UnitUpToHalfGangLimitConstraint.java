package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.conf.punkapocalyptic.MessageBundleKey;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.service.punkapocalyptic.LocalizationService;

public final class UnitUpToHalfGangLimitConstraint implements
        UnitGangConstraint {

    private final LocalizationService localization;
    private String                    message;
    private String                    unit;

    public UnitUpToHalfGangLimitConstraint(final LocalizationService service) {
        super();

        checkNotNull(service, "Received a null pointer as localization service");

        localization = service;
    }

    public UnitUpToHalfGangLimitConstraint(
            final UnitUpToHalfGangLimitConstraint constraint) {
        super();

        checkNotNull(constraint, "Received a null pointer as constraint");

        localization = constraint.localization;
    }

    @Override
    public final UnitUpToHalfGangLimitConstraint createNewInstance() {
        return new UnitUpToHalfGangLimitConstraint(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final UnitUpToHalfGangLimitConstraint other;

        other = (UnitUpToHalfGangLimitConstraint) obj;
        return Objects.equals(unit, other.unit);
    }

    @Override
    public final String getErrorMessage() {
        if (message == null) {
            message = String.format(
                    getLocalizationService().getMessageString(
                            MessageBundleKey.HALF_GANG),
                    getLocalizationService().getUnitNameString(getUnit()));
        }

        return message;
    }

    @Override
    public final String getName() {
        return "up_to_half_points";
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unit);
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
        return localization;
    }

    private final String getUnit() {
        return unit;
    }

}
