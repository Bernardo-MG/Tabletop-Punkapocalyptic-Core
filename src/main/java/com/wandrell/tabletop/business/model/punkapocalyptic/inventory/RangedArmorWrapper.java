package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import java.util.Collection;

import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;

public final class RangedArmorWrapper implements RangedArmor {

    private final Armor       armor;
    private final RangedValue rangedArmor;

    public RangedArmorWrapper(final Armor armor, final RangedValue rangedArmor) {
        super();

        this.armor = armor;
        this.rangedArmor = rangedArmor;
    }

    public RangedArmorWrapper(final RangedArmorWrapper armor) {
        super();

        this.armor = armor.armor;
        rangedArmor = armor.rangedArmor;
    }

    @Override
    public final RangedArmorWrapper createNewInstance() {
        return new RangedArmorWrapper(this);
    }

    @Override
    public final Integer getArmor() {
        return getWrappedArmor().getArmor();
    }

    @Override
    public final Integer getCost() {
        return getWrappedArmor().getCost();
    }

    @Override
    public final String getName() {
        return getWrappedArmor().getName();
    }

    @Override
    public final RangedValue getRangedArmor() {
        return rangedArmor;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getWrappedArmor().getSpecialRules();
    }

    @Override
    public final void setCost(final Integer cost) {
        getWrappedArmor().setCost(cost);
    }

    private final Armor getWrappedArmor() {
        return armor;
    }

}
