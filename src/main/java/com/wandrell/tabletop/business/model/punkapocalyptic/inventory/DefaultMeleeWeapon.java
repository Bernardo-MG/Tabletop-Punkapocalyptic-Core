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

import java.util.Collection;

import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;

public final class DefaultMeleeWeapon extends AbstractWeapon implements
        MeleeWeapon {

    private final Integer weaponCombat;
    private final Integer weaponPenet;
    private final Integer weaponStr;

    public DefaultMeleeWeapon(final DefaultMeleeWeapon weapon) {
        super(weapon);

        checkNotNull(weapon, "Received a null pointer as weapon");

        weaponCombat = weapon.weaponCombat;
        weaponPenet = weapon.weaponPenet;
        weaponStr = weapon.weaponStr;
    }

    public DefaultMeleeWeapon(final String name, final Integer cost,
            final Integer strength, final Integer penetration,
            final Integer combat, final Collection<SpecialRule> rules) {
        super(name, cost, rules);

        checkNotNull(combat, "Received a null pointer as combat");
        checkNotNull(penetration, "Received a null pointer as penetration");
        checkNotNull(strength, "Received a null pointer as strength");

        this.weaponCombat = combat;
        this.weaponPenet = penetration;
        this.weaponStr = strength;
    }

    @Override
    public final DefaultMeleeWeapon createNewInstance() {
        return new DefaultMeleeWeapon(this);
    }

    @Override
    public final Integer getCombat() {
        return weaponCombat;
    }

    @Override
    public final Integer getPenetration() {
        return weaponPenet;
    }

    @Override
    public final Integer getStrength() {
        return weaponStr;
    }

}
