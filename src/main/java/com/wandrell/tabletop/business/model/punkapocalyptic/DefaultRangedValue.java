package com.wandrell.tabletop.business.model.punkapocalyptic;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

public class DefaultRangedValue implements RangedValue {

    private final Integer distanceLong;
    private final Integer distanceMedium;
    private final Integer distanceShort;

    public DefaultRangedValue(final Integer distanceShort,
            final Integer distanceMedium, final Integer distanceLong) {
        super();

        checkNotNull(distanceShort, "Received a null pointer as short distance");
        checkNotNull(distanceMedium,
                "Received a null pointer as medium distance");
        checkNotNull(distanceLong, "Received a null pointer as long distance");

        this.distanceShort = distanceShort;
        this.distanceMedium = distanceMedium;
        this.distanceLong = distanceLong;
    }

    @Override
    public final Integer getLongValue() {
        return distanceLong;
    }

    @Override
    public final Integer getMediumValue() {
        return distanceMedium;
    }

    @Override
    public final Integer getShortValue() {
        return distanceShort;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("short", distanceShort)
                .add("medium", distanceMedium).add("long", distanceLong)
                .toString();
    }

}
