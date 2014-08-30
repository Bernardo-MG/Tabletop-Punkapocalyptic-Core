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
import java.util.Collections;

import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

public final class DefaultArmor implements Armor {

    private final Integer                 armor;
    private Integer                       cost = 0;
    private final String                  name;
    private final Collection<SpecialRule> rules;

    public DefaultArmor(final DefaultArmor armor) {
        super();

        this.armor = armor.armor;
        cost = armor.cost;

        name = armor.name;

        rules = armor.rules;
    }

    public DefaultArmor(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        super();

        this.name = name;
        this.armor = armor;

        this.rules = rules;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultArmor other = (DefaultArmor) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
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
        return Collections.unmodifiableCollection(_getSpecialRules());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    @Override
    public final String toString() {
        return getName();
    }

    protected final Collection<SpecialRule> _getSpecialRules() {
        return rules;
    }

}
