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
package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.WeaponEnhancement;

public final class DefaultWeaponOption implements WeaponOption {

    private final Weapon                        availWeapon;
    private final Collection<WeaponEnhancement> weaponEnhcn = new LinkedHashSet<>();

    public DefaultWeaponOption(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");
        checkNotNull(enhancements,
                "Received a null pointer as weapon enhancements");

        this.availWeapon = weapon;

        for (final WeaponEnhancement enhancement : enhancements) {
            checkNotNull(enhancement,
                    "Received a null pointer as weapon enhancement");

            this.weaponEnhcn.add(enhancement);
        }
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

        DefaultWeaponOption other;

        other = (DefaultWeaponOption) obj;
        return Objects.equal(availWeapon, other.availWeapon);
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return Collections.unmodifiableCollection(getEnhancementsModifiable());
    }

    @Override
    public final Weapon getWeapon() {
        return availWeapon;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(availWeapon);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("weapon", availWeapon)
                .add("enhancements", weaponEnhcn).toString();
    }

    private final Collection<WeaponEnhancement> getEnhancementsModifiable() {
        return weaponEnhcn;
    }

}
