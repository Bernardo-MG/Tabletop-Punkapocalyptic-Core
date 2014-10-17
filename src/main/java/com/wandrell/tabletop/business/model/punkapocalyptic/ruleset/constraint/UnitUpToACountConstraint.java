package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.constraint;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.conf.punkapocalyptic.MessageBundleKey;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.service.punkapocalyptic.LocalizationService;

public final class UnitUpToACountConstraint implements UnitGangConstraint {

    private final Integer             count;
    private final LocalizationService localization;
    private String                    message;
    private final String              name;
    private String                    unit;

    public UnitUpToACountConstraint(final String name, final Integer count,
            final LocalizationService service) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(count, "Received a null pointer as count");
        checkNotNull(service, "Received a null pointer as localization service");

        this.name = name;
        this.count = count;

        localization = service;
    }

    public UnitUpToACountConstraint(final UnitUpToACountConstraint constraint) {
        super();

        checkNotNull(constraint, "Received a null pointer as constraint");

        name = constraint.name;
        count = constraint.count;

        localization = constraint.localization;
    }

    @Override
    public final UnitUpToACountConstraint createNewInstance() {
        return new UnitUpToACountConstraint(this);
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

        final UnitUpToACountConstraint other;

        other = (UnitUpToACountConstraint) obj;
        return Objects.equals(name, other.name)
                && Objects.equals(unit, other.unit)
                && Objects.equals(count, other.count);
    }

    @Override
    public final String getErrorMessage() {
        if (message == null) {
            message = String.format(
                    getLocalizationService().getMessageString(
                            MessageBundleKey.UNIQUE), getLocalizationService()
                            .getUnitNameString(getUnit()));
        }

        return message;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, unit, count);
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
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("unit", unit).add("count", count).toString();
    }

    private final Integer getCount() {
        return count;
    }

    private final LocalizationService getLocalizationService() {
        return localization;
    }

    private final String getUnit() {
        return unit;
    }

}
