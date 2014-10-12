package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;

public final class RangedArmorWrapper implements RangedArmor {

    private final Armor       armor;
    private final RangedValue rangedArmor;

    public RangedArmorWrapper(final Armor armor, final RangedValue rangedArmor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");
        checkNotNull(rangedArmor,
                "Received a null pointer as ranged armor value");

        this.armor = armor;
        this.rangedArmor = rangedArmor;
    }

    public RangedArmorWrapper(final RangedArmorWrapper armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor.armor;
        rangedArmor = armor.rangedArmor;
    }

    @Override
    public final RangedArmorWrapper createNewInstance() {
        return new RangedArmorWrapper(this);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RangedArmorWrapper other = (RangedArmorWrapper) obj;
        return Objects.equals(armor, other.armor);
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
    public final int hashCode() {
        return Objects.hashCode(armor);
    }

    @Override
    public final void setCost(final Integer cost) {
        checkNotNull(armor, "Received a null pointer as cost");

        getWrappedArmor().setCost(cost);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final Armor getWrappedArmor() {
        return armor;
    }

}
