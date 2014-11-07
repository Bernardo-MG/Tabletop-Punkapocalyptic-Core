package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import java.util.Collection;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.WeaponEnhancement;

public final class DefaultWeaponOption implements WeaponOption {

    private final Collection<WeaponEnhancement> enhancements;
    private final Weapon                        weapon;

    public DefaultWeaponOption(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        super();

        this.weapon = weapon;
        this.enhancements = enhancements;
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
        return enhancements;
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

}
