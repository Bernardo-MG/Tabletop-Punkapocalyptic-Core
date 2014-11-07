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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.WeaponModifierSpecialRule;

public abstract class AbstractWeapon implements Weapon {

    private final Integer                       cost;
    private final Collection<WeaponEnhancement> enhancements = new LinkedList<>();
    private final String                        name;
    private final Collection<SpecialRule>       rules        = new LinkedHashSet<>();
    private Boolean                             twoHanded    = false;

    public AbstractWeapon(final AbstractWeapon weapon) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");

        name = weapon.name;

        cost = weapon.cost;
        twoHanded = weapon.twoHanded;

        rules.addAll(weapon.rules);
        for (final SpecialRule rule : rules) {
            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }
    }

    public AbstractWeapon(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

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

        final AbstractWeapon other;

        other = (AbstractWeapon) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhacements() {
        return Collections.unmodifiableCollection(getEnhacementsModifiable());
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
        return Objects.hashCode(name);
    }

    @Override
    public final Boolean isTwoHanded() {
        return twoHanded;
    }

    public final void setRules(final Collection<SpecialRule> rules) {
        checkNotNull(rules, "Received a null pointer as rules");

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            this.rules.add(rule);

            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }
    }

    @Override
    public final void setTwoHanded(final Boolean twoHanded) {
        checkNotNull(twoHanded,
                "Received a null pointer as the two handed status");

        this.twoHanded = twoHanded;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).add("rules", rules)
                .add("enhancements", enhancements).toString();
    }

    protected final Collection<WeaponEnhancement> getEnhacementsModifiable() {
        return enhancements;
    }

    protected final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

}
