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

import java.util.Collection;

import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;

public final class DefaultMeleeWeapon extends AbstractWeapon implements
        MeleeWeapon {

    private final Integer combat;
    private final Integer penetration;
    private final Integer strength;

    public DefaultMeleeWeapon(final DefaultMeleeWeapon weapon) {
        super(weapon);

        if (weapon == null) {
            throw new NullPointerException("Received a null pointer as weapon");
        }

        combat = weapon.combat;
        penetration = weapon.penetration;
        strength = weapon.strength;
    }

    public DefaultMeleeWeapon(final String name, final Integer cost,
            final Integer strength, final Integer penetration,
            final Integer combat, final Collection<SpecialRule> rules) {
        super(name, cost, rules);

        if (combat == null) {
            throw new NullPointerException("Received a null pointer as combat");
        }

        if (penetration == null) {
            throw new NullPointerException(
                    "Received a null pointer as penetration");
        }

        if (strength == null) {
            throw new NullPointerException(
                    "Received a null pointer as strength");
        }

        this.combat = combat;
        this.penetration = penetration;
        this.strength = strength;
    }

    @Override
    public final Integer getCombat() {
        return combat;
    }

    @Override
    public final Integer getPenetration() {
        return penetration;
    }

    @Override
    public final Integer getStrength() {
        return strength;
    }

}
