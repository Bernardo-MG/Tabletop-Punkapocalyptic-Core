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
package com.wandrell.tabletop.model.punkapocalyptic.inventory;

import java.util.Collection;

import com.wandrell.tabletop.model.punkapocalyptic.rule.SpecialRule;

public final class DefaultRangedWeapon extends AbstractWeapon implements
        RangedWeapon {

    private final class DefaultRangedDistance implements RangedDistance {

        private final Integer distanceLong;
        private final Integer distanceMedium;
        private final Integer distanceShort;

        public DefaultRangedDistance(final Integer distanceShort,
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
    private final Integer        penetrationLong;
    private final Integer        penetrationMedium;
    private final Integer        penetrationShort;
    private final Integer        strengthLong;
    private final Integer        strengthMedium;
    private final Integer        strengthShort;

    public DefaultRangedWeapon(final DefaultRangedWeapon weapon) {
        super(weapon);

        if (weapon == null) {
            throw new NullPointerException("Received a null pointer as weapon");
        }

        distancesCM = weapon.distancesCM;
        distancesInches = weapon.distancesInches;

        penetrationShort = weapon.penetrationShort;
        penetrationMedium = weapon.penetrationMedium;
        penetrationLong = weapon.penetrationLong;

        strengthShort = weapon.strengthShort;
        strengthMedium = weapon.strengthMedium;
        strengthLong = weapon.strengthLong;
    }

    public DefaultRangedWeapon(final String name, final Integer cost,
            final Integer hands, final Integer penetrationShort,
            final Integer penetrationMedium, final Integer penetrationLong,
            final Integer strengthShort, final Integer strengthMedium,
            final Integer strengthLong, final Integer distanceShortCM,
            final Integer distanceMediumCM, final Integer distanceLongCM,
            final Integer distanceShortInches,
            final Integer distanceMediumInches,
            final Integer distanceLongInches,
            final Collection<SpecialRule> rules) {
        super(name, cost, hands, rules);

        if (distanceShortCM == null) {
            throw new NullPointerException(
                    "Received a null pointer as short distance in cm");
        }

        if (distanceMediumCM == null) {
            throw new NullPointerException(
                    "Received a null pointer as medium distance in cm");
        }

        if (distanceLongCM == null) {
            throw new NullPointerException(
                    "Received a null pointer as long distance in cm");
        }

        if (distanceShortInches == null) {
            throw new NullPointerException(
                    "Received a null pointer as short distance in inches");
        }

        if (distanceMediumInches == null) {
            throw new NullPointerException(
                    "Received a null pointer as medium distance in inches");
        }

        if (distanceLongInches == null) {
            throw new NullPointerException(
                    "Received a null pointer as long distance in inches");
        }

        if (penetrationShort == null) {
            throw new NullPointerException(
                    "Received a null pointer as short range penetration");
        }

        if (penetrationMedium == null) {
            throw new NullPointerException(
                    "Received a null pointer as medium range penetration");
        }

        if (penetrationLong == null) {
            throw new NullPointerException(
                    "Received a null pointer as long range penetration");
        }

        if (strengthShort == null) {
            throw new NullPointerException(
                    "Received a null pointer as short range strength");
        }

        if (strengthMedium == null) {
            throw new NullPointerException(
                    "Received a null pointer as medium range strength");
        }

        if (strengthLong == null) {
            throw new NullPointerException(
                    "Received a null pointer as long range strength");
        }

        this.distancesCM = new DefaultRangedDistance(distanceShortCM,
                distanceMediumCM, distanceLongCM);
        this.distancesInches = new DefaultRangedDistance(distanceShortInches,
                distanceMediumInches, distanceLongInches);

        this.penetrationShort = penetrationShort;
        this.penetrationMedium = penetrationMedium;
        this.penetrationLong = penetrationLong;

        this.strengthShort = strengthShort;
        this.strengthMedium = strengthMedium;
        this.strengthLong = strengthLong;
    }

    @Override
    public final RangedDistance getDistancesImperialUnits() {
        return distancesInches;
    }

    @Override
    public final RangedDistance getDistancesMetricSystem() {
        return distancesCM;
    }

    @Override
    public final Integer getLongPenetration() {
        return penetrationLong;
    }

    @Override
    public final Integer getLongStrength() {
        return strengthLong;
    }

    @Override
    public final Integer getMediumPenetration() {
        return penetrationMedium;
    }

    @Override
    public final Integer getMediumStrength() {
        return strengthMedium;
    }

    @Override
    public final Integer getShortPenetration() {
        return penetrationShort;
    }

    @Override
    public final Integer getShortStrength() {
        return strengthShort;
    }

}
