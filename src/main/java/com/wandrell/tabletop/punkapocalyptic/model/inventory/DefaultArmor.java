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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;

public final class DefaultArmor implements Armor {

    private final Integer                 armorArmor;
    private final String                  armorName;
    private final Collection<SpecialRule> armorRules = new LinkedHashSet<SpecialRule>();

    public DefaultArmor(final DefaultArmor armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.armorArmor = armor.armorArmor;

        armorName = armor.armorName;

        armorRules.addAll(armor.armorRules);
    }

    public DefaultArmor(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(armor, "Received a null pointer as armor");
        checkNotNull(rules, "Received a null pointer as rules");

        this.armorName = name;
        this.armorArmor = armor;

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            this.armorRules.add(rule);
        }
    }

    @Override
    public final DefaultArmor createNewInstance() {
        return new DefaultArmor(this);
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

        final DefaultArmor other;

        other = (DefaultArmor) obj;
        return Objects.equals(armorName, other.armorName);
    }

    @Override
    public final Integer getArmor() {
        return armorArmor;
    }

    @Override
    public final String getName() {
        return armorName;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(armorName);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", armorName)
                .toString();
    }

    private final Collection<SpecialRule> getSpecialRulesModifiable() {
        return armorRules;
    }

}
