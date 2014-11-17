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
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;

public final class DefaultArmor implements Armor {

    private final Integer                 armor;
    private Integer                       cost  = 0;
    private final String                  name;
    private final Collection<SpecialRule> rules = new LinkedHashSet<>();

    public DefaultArmor(final DefaultArmor armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor.armor;
        cost = armor.cost;

        name = armor.name;

        rules.addAll(armor.rules);
    }

    public DefaultArmor(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(armor, "Received a null pointer as armor");
        checkNotNull(rules, "Received a null pointer as rules");

        this.name = name;
        this.armor = armor;

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            this.rules.add(rule);
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
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getArmor() {
        return armor;
    }

    @Override
    public final Integer getCost() {
        return cost;
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
    public final void setCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        this.cost = cost;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

}
