/**
 * Copyright 2014 the original author or authors
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
package com.wandrell.tabletop.punkapocalyptic.inventory;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

public final class DefaultRangedWeapon extends AbstractWeapon implements
        RangedWeapon {

    public final class DefaultRangedDistance implements RangedDistance {

        private final Integer distanceLong;
        private final Integer distanceMedium;
        private final Integer distanceShort;

        public DefaultRangedDistance(final Integer distanceShort,
                final Integer distanceMedium, final Integer distanceLong) {
            super();

            this.distanceShort = distanceShort;
            this.distanceMedium = distanceMedium;
            this.distanceLong = distanceLong;
        }

        @Override
        public final Integer getLongDistance() {
            return distanceLong;
        }

        @Override
        public final Integer getMediumDistance() {
            return distanceMedium;
        }

        @Override
        public final Integer getShortDistance() {
            return distanceShort;
        }

    }

    private final RangedDistance distancesCM;
    private final RangedDistance distancesInches;

    public DefaultRangedWeapon(final DefaultRangedWeapon weapon) {
        super(weapon);

        distancesCM = weapon.distancesCM;
        distancesInches = weapon.distancesInches;
    }

    public DefaultRangedWeapon(final String name, final Integer strength,
            final Integer penetration, final Collection<SpecialRule> rules,
            final RangedDistance distancesCM, RangedDistance distancesInches) {
        super(name, strength, penetration, rules);

        this.distancesCM = distancesCM;
        this.distancesInches = distancesInches;
    }

    @Override
    public final RangedDistance getDistancesImperialUnits() {
        return distancesInches;
    }

    @Override
    public final RangedDistance getDistancesMetricSystem() {
        return distancesCM;
    }

}
