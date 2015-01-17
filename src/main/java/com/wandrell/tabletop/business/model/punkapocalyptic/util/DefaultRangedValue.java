/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.business.model.punkapocalyptic.util;

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
