package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.modifier;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.DefaultRangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.RangedArmorWrapper;

public final class BulletproofArmorInitializerModifier implements
        ArmorInitializerModifier {

    public BulletproofArmorInitializerModifier() {
        super();
    }

    @Override
    public final String getName() {
        return "firearms_protection";
    }

    @Override
    public final Armor modify(final Armor armor) {
        final RangedValue rangedArmor;

        checkNotNull(armor, "Received a null pointer as armor");

        // TODO: Load these values from a file
        rangedArmor = new DefaultRangedValue(5, 6, 7);

        return new RangedArmorWrapper(armor, rangedArmor);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

}
