package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.modifier;

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
    public final Armor modify(final Armor armor) {
        final RangedValue rangedArmor;

        // TODO: Load these values from a file
        rangedArmor = new DefaultRangedValue(5, 6, 7);

        return new RangedArmorWrapper(armor, rangedArmor);
    }

}
