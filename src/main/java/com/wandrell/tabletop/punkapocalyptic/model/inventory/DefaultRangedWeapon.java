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
package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

public final class DefaultRangedWeapon extends AbstractWeapon implements
        RangedWeapon {

    private final RangedValue distancesCM;
    private final RangedValue distancesInches;
    private final Boolean     firearm;
    private final RangedValue penetrationRanges;
    private final RangedValue strengthRanges;

    public DefaultRangedWeapon(final DefaultRangedWeapon weapon) {
        super(weapon);

        checkNotNull(weapon, "Received a null pointer as weapon");

        distancesCM = weapon.distancesCM;
        distancesInches = weapon.distancesInches;

        penetrationRanges = weapon.penetrationRanges;
        strengthRanges = weapon.strengthRanges;

        firearm = weapon.firearm;
    }

    public DefaultRangedWeapon(final String name, final Integer cost,
            final Boolean twoHanded, final Collection<SpecialRule> rules,
            final RangedValue penetration, final RangedValue strength,
            final RangedValue distanceCM, final RangedValue distanceInches,
            final Boolean firearm) {
        super(name, cost, twoHanded, rules);

        checkNotNull(distanceCM,
                "Received a null pointer as the distances in cm");
        checkNotNull(distanceInches,
                "Received a null pointer as the distances in inches");

        checkNotNull(penetration,
                "Received a null pointer as penetration ranges");
        checkNotNull(strength, "Received a null pointer as strength ranges");

        checkNotNull(firearm, "Received a null pointer as firearm flag");

        distancesCM = distanceCM;
        distancesInches = distanceInches;

        penetrationRanges = penetration;
        strengthRanges = strength;

        this.firearm = firearm;
    }

    @Override
    public final DefaultRangedWeapon createNewInstance() {
        return new DefaultRangedWeapon(this);
    }

    @Override
    public final RangedValue getDistancesImperialUnits() {
        return distancesInches;
    }

    @Override
    public final RangedValue getDistancesMetricSystem() {
        return distancesCM;
    }

    @Override
    public final Integer getLongPenetration() {
        return getPenetrationRanges().getLongValue();
    }

    @Override
    public final Integer getLongStrength() {
        return getStrengthRanges().getLongValue();
    }

    @Override
    public final Integer getMediumPenetration() {
        return getPenetrationRanges().getMediumValue();
    }

    @Override
    public final Integer getMediumStrength() {
        return getStrengthRanges().getMediumValue();
    }

    @Override
    public final Integer getShortPenetration() {
        return getPenetrationRanges().getShortValue();
    }

    @Override
    public final Integer getShortStrength() {
        return getStrengthRanges().getShortValue();
    }

    @Override
    public final Boolean isFirearm() {
        return firearm;
    }

    private final RangedValue getPenetrationRanges() {
        return penetrationRanges;
    }

    private final RangedValue getStrengthRanges() {
        return strengthRanges;
    }

}
