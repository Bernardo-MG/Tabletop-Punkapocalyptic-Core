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

    private final Collection<WeaponEnhancement> enhancements = new LinkedHashSet<>();
    private final Weapon                        weapon;

    public DefaultWeaponOption(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");
        checkNotNull(enhancements,
                "Received a null pointer as weapon enhancements");

        this.weapon = weapon;

        for (final WeaponEnhancement enhancement : enhancements) {
            checkNotNull(enhancement,
                    "Received a null pointer as weapon enhancement");

            this.enhancements.add(enhancement);
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
        return Objects.equal(weapon, other.weapon);
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return Collections.unmodifiableCollection(getEnhancementsModifiable());
    }

    @Override
    public final Weapon getWeapon() {
        return weapon;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(weapon);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("weapon", weapon)
                .add("enhancements", enhancements).toString();
    }

    private final Collection<WeaponEnhancement> getEnhancementsModifiable() {
        return enhancements;
    }

}
