package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import java.util.Collection;

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
    public final Collection<WeaponEnhancement> getEnhancements() {
        return enhancements;
    }

    @Override
    public final Weapon getWeapon() {
        return weapon;
    }

}
