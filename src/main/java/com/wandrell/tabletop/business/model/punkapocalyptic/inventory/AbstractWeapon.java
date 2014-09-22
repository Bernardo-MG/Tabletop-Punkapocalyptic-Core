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
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.WeaponModifierSpecialRule;

public abstract class AbstractWeapon implements Weapon {

    private final Integer                 cost;
    private Integer                       hands;
    private final String                  name;
    private final Collection<SpecialRule> rules;

    {
        hands = 1;

        rules = new LinkedHashSet<>();
    }

    public AbstractWeapon(final AbstractWeapon weapon) {
        super();

        if (weapon == null) {
            throw new NullPointerException("Received a null pointer as weapon");
        }

        name = weapon.name;

        cost = weapon.cost;
        hands = weapon.hands;

        rules.addAll(weapon.rules);
        for (final SpecialRule rule : rules) {
            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }
    }

    public AbstractWeapon(final String name, final Integer cost) {
        super();

        if (name == null) {
            throw new NullPointerException("Received a null pointer as name");
        }

        if (cost == null) {
            throw new NullPointerException("Received a null pointer as cost");
        }

        if (hands == null) {
            throw new NullPointerException("Received a null pointer as hands");
        }

        this.name = name;

        this.cost = cost;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        AbstractWeapon other = (AbstractWeapon) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getHands() {
        return hands;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public final void setHands(final Integer hands) {
        this.hands = hands;
    }

    public final void setRules(final Collection<SpecialRule> rules) {
        if (rules == null) {
            throw new NullPointerException("Received a null pointer as rules");
        }

        for (final SpecialRule rule : rules) {
            if (rule == null) {
                throw new NullPointerException(
                        "Received a null pointer as rule");
            }

            this.rules.add(rule);

            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }
    }

    @Override
    public final String toString() {
        return getName();
    }

    protected final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

}
