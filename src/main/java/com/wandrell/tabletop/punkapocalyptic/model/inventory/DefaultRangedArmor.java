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
package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

public final class DefaultRangedArmor implements RangedArmor {

    private final Armor       baseArmor;
    private final RangedValue rangedArmor;

    public DefaultRangedArmor(final DefaultRangedArmor armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.baseArmor = armor.baseArmor;
        rangedArmor = armor.rangedArmor;
    }

    public DefaultRangedArmor(final String name, final Integer armor,
            final RangedValue rangedArmor, final Collection<SpecialRule> rules) {
        super();

        checkNotNull(rangedArmor,
                "Received a null pointer as ranged armor value");

        this.baseArmor = new DefaultArmor(name, armor, rules);
        this.rangedArmor = rangedArmor;
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

        final DefaultRangedArmor other;

        other = (DefaultRangedArmor) obj;
        return Objects.equals(baseArmor, other.baseArmor);
    }

    @Override
    public final Integer getArmor() {
        return getBaseArmor().getArmor();
    }

    @Override
    public final String getName() {
        return getBaseArmor().getName();
    }

    @Override
    public final RangedValue getRangedArmor() {
        return rangedArmor;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getBaseArmor().getSpecialRules();
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(baseArmor);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final Armor getBaseArmor() {
        return baseArmor;
    }

}
