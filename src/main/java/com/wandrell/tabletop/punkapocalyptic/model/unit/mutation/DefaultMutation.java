/**
 * Copyright 2015 the original author or authors
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
package com.wandrell.tabletop.punkapocalyptic.model.unit.mutation;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class DefaultMutation implements Mutation, AttributeBonusMutation {

    private final Integer actionsBonus;
    private final Integer agilityBonus;
    private final Integer combatBonus;
    private final Integer cost;
    private final String  name;
    private final Integer precisionBonus;
    private final Integer strengthBonus;
    private final Integer techBonus;
    private final Integer toughnessBonus;

    public DefaultMutation(final String name, final Integer cost,
            final Integer actions, final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");
        checkNotNull(actions, "Received a null pointer as actions");
        checkNotNull(agility, "Received a null pointer as agility");
        checkNotNull(combat, "Received a null pointer as combat");
        checkNotNull(precision, "Received a null pointer as precision");
        checkNotNull(strength, "Received a null pointer as strength");
        checkNotNull(tech, "Received a null pointer as tech");
        checkNotNull(toughness, "Received a null pointer as toughness");

        this.name = name;
        this.cost = cost;

        actionsBonus = actions;
        agilityBonus = agility;
        combatBonus = combat;
        precisionBonus = precision;
        strengthBonus = strength;
        techBonus = tech;
        toughnessBonus = toughness;
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

        DefaultMutation other;

        other = (DefaultMutation) obj;
        return Objects.equal(name, other.name);
    }

    @Override
    public final Integer getActionsBonus() {
        return actionsBonus;
    }

    @Override
    public final Integer getAgilityBonus() {
        return agilityBonus;
    }

    @Override
    public final Integer getCombatBonus() {
        return combatBonus;
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
    public final Integer getPrecisionBonus() {
        return precisionBonus;
    }

    @Override
    public final Integer getStrengthBonus() {
        return strengthBonus;
    }

    @Override
    public final Integer getTechBonus() {
        return techBonus;
    }

    @Override
    public final Integer getToughnessBonus() {
        return toughnessBonus;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
