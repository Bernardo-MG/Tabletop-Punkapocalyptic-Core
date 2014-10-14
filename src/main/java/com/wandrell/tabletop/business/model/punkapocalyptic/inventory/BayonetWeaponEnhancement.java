package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

public final class BayonetWeaponEnhancement implements WeaponEnhancement {

    private final Integer cost;
    private final String  name;

    public BayonetWeaponEnhancement(final Integer cost) {
        super();
        name = "bayonet";
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

        if ((weapon instanceof RangedWeapon)
                && (((RangedWeapon) weapon).isFirearm())) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

}
