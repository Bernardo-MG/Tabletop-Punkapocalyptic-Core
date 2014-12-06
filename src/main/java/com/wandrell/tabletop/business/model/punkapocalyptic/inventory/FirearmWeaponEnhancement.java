package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

public final class FirearmWeaponEnhancement implements WeaponEnhancement {

    private final Integer cost;
    private final String  name;

    public FirearmWeaponEnhancement(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

        this.name = name;
        this.cost = cost;
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
    public final Boolean isValid(final Weapon weapon) {
        final Boolean valid;

        checkNotNull(weapon, "Received a null pointer as weapon");

        if ((weapon instanceof RangedWeapon)
                && (((RangedWeapon) weapon).isFirearm())) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).toString();
    }

}
