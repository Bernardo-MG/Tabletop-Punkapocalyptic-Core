package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.RangedWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;

public final class FirearmSpecialRule implements SpecialRule,
        WeaponModifierSpecialRule {

    private final SpecialRule rule;

    public FirearmSpecialRule(final String name) {
        super();

        checkNotNull(name, "Received a null pointer as name");

        rule = new DefaultSpecialRule(name);
    }

    @Override
    public final void applyToWeapon(final Weapon weapon) {
        checkNotNull(weapon, "Received a null pointer as weapon");

        if (weapon instanceof RangedWeapon) {
            ((RangedWeapon) weapon).setFirearm(true);
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

        final FirearmSpecialRule other;

        other = (FirearmSpecialRule) obj;
        return Objects.equals(rule, other.rule);
    }

    @Override
    public final String getName() {
        return getBaseRule().getName();
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(rule);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    private final SpecialRule getBaseRule() {
        return rule;
    }

}
