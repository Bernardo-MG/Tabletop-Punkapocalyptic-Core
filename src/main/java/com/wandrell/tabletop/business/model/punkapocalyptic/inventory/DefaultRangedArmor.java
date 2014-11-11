package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;

public final class DefaultRangedArmor implements RangedArmor {

    private final Armor       armor;
    private final RangedValue rangedArmor;

    public DefaultRangedArmor(final DefaultRangedArmor armor) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor.armor;
        rangedArmor = armor.rangedArmor;
    }

    public DefaultRangedArmor(final String name, final Integer armor,
            final RangedValue rangedArmor, final Collection<SpecialRule> rules) {
        super();

        checkNotNull(rangedArmor,
                "Received a null pointer as ranged armor value");

        this.armor = new DefaultArmor(name, armor, rules);
        this.rangedArmor = rangedArmor;
    }

    @Override
    public final DefaultRangedArmor createNewInstance() {
        return new DefaultRangedArmor(this);
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

        final DefaultRangedArmor other;

        other = (DefaultRangedArmor) obj;
        return Objects.equals(armor, other.armor);
    }

    @Override
    public final Integer getArmor() {
        return getBaseArmor().getArmor();
    }

    @Override
    public final Integer getCost() {
        return getBaseArmor().getCost();
    }

    @Override
    public final String getName() {
        return getBaseArmor().getName();
    }

    @Override
    public final RangedValue getRangedArmor() {
        return rangedArmor;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getBaseArmor().getSpecialRules();
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(armor);
    }

    @Override
    public final void setCost(final Integer cost) {
        checkNotNull(armor, "Received a null pointer as cost");

        getBaseArmor().setCost(cost);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final Armor getBaseArmor() {
        return armor;
    }

}
