package com.wandrell.tabletop.business.model.punkapocalyptic;

public class DefaultRangedValue implements RangedValue {

    private final Integer distanceLong;
    private final Integer distanceMedium;
    private final Integer distanceShort;

    public DefaultRangedValue(final Integer distanceShort,
            final Integer distanceMedium, final Integer distanceLong) {
        super();

        if (distanceShort == null) {
            throw new NullPointerException(
                    "Received a null pointer as short distance");
        }

        if (distanceMedium == null) {
            throw new NullPointerException(
                    "Received a null pointer as medium distance");
        }

        if (distanceLong == null) {
            throw new NullPointerException(
                    "Received a null pointer as long distance");
        }

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

}
