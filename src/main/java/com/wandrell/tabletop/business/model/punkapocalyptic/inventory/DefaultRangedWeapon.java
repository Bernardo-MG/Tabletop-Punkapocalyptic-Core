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
package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.tabletop.business.model.punkapocalyptic.DefaultRangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;

public final class DefaultRangedWeapon extends AbstractWeapon implements
        RangedWeapon {

    private final RangedValue distancesCM;
    private final RangedValue distancesInches;
    private Boolean           firearm;
    private MeleeWeapon       melee;
    private final Integer     penetrationLong;
    private final Integer     penetrationMedium;
    private final Integer     penetrationShort;
    private final Integer     strengthLong;
    private final Integer     strengthMedium;
    private final Integer     strengthShort;

    {
        firearm = false;
    }

    public DefaultRangedWeapon(final DefaultRangedWeapon weapon) {
        super(weapon);

        checkNotNull(weapon, "Received a null pointer as weapon");

        distancesCM = weapon.distancesCM;
        distancesInches = weapon.distancesInches;

        penetrationShort = weapon.penetrationShort;
        penetrationMedium = weapon.penetrationMedium;
        penetrationLong = weapon.penetrationLong;

        strengthShort = weapon.strengthShort;
        strengthMedium = weapon.strengthMedium;
        strengthLong = weapon.strengthLong;

        melee = weapon.melee;
    }

    public DefaultRangedWeapon(final String name, final Integer cost,
            final Integer penetrationShort, final Integer penetrationMedium,
            final Integer penetrationLong, final Integer strengthShort,
            final Integer strengthMedium, final Integer strengthLong,
            final Integer distanceShortCM, final Integer distanceMediumCM,
            final Integer distanceLongCM, final Integer distanceShortInches,
            final Integer distanceMediumInches,
            final Integer distanceLongInches, final MeleeWeapon weaponMelee) {
        super(name, cost);

        checkNotNull(distanceShortCM,
                "Received a null pointer as short distance in cm");
        checkNotNull(distanceMediumCM,
                "Received a null pointer as medium distance in cm");
        checkNotNull(distanceLongCM,
                "Received a null pointer as long distance in cm");

        checkNotNull(distanceShortInches,
                "Received a null pointer as short distance in inches");
        checkNotNull(distanceMediumInches,
                "Received a null pointer as medium distance in inches");
        checkNotNull(distanceLongInches,
                "Received a null pointer as long distance in inches");

        checkNotNull(penetrationShort,
                "Received a null pointer as short range penetration");
        checkNotNull(penetrationMedium,
                "Received a null pointer as medium range penetration");
        checkNotNull(penetrationLong,
                "Received a null pointer as long range penetration");

        checkNotNull(strengthShort,
                "Received a null pointer as short range strength");
        checkNotNull(strengthMedium,
                "Received a null pointer as medium range strength");
        checkNotNull(strengthLong,
                "Received a null pointer as long range strength");

        checkNotNull(weaponMelee, "Received a null pointer as melee equivalent");

        this.distancesCM = new DefaultRangedValue(distanceShortCM,
                distanceMediumCM, distanceLongCM);
        this.distancesInches = new DefaultRangedValue(distanceShortInches,
                distanceMediumInches, distanceLongInches);

        this.penetrationShort = penetrationShort;
        this.penetrationMedium = penetrationMedium;
        this.penetrationLong = penetrationLong;

        this.strengthShort = strengthShort;
        this.strengthMedium = strengthMedium;
        this.strengthLong = strengthLong;

        melee = weaponMelee;
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
    public final MeleeWeapon getMeleeEquivalent() {
        return melee;
    }

    @Override
    public final Integer getShortPenetration() {
        return penetrationShort;
    }

    @Override
    public final Integer getShortStrength() {
        return strengthShort;
    }

    @Override
    public final Boolean isFirearm() {
        return firearm;
    }

    @Override
    public final void setMeleeEquivalent(final MeleeWeapon weapon) {
        checkNotNull(weapon, "Received a null pointer as melee equivalent");

        melee = weapon;
    }

}
