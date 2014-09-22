package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.MeleeWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.RangedWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;

public final class TwoHandedSpecialRule implements SpecialRule,
        WeaponModifierSpecialRule {

    private final SpecialRule rule;
    private final MeleeWeapon weapon;

    public TwoHandedSpecialRule(final String name, final MeleeWeapon weapon) {
        super();

        rule = new DefaultSpecialRule(name);
        this.weapon = weapon;
    }

    @Override
    public final void applyToWeapon(final Weapon weapon) {
        weapon.setHands(2);

        if (weapon instanceof RangedWeapon) {
            ((RangedWeapon) weapon).setMeleeEquivalent(getEquivalentWeapon());
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TwoHandedSpecialRule other = (TwoHandedSpecialRule) obj;
        if (rule == null) {
            if (other.rule != null)
                return false;
        } else if (!rule.equals(other.rule))
            return false;
        return true;
    }

    @Override
    public final String getName() {
        return getBaseRule().getName();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rule == null) ? 0 : rule.hashCode());
        return result;
    }

    @Override
    public final String toString() {
        return "two-handed";
    }

    private final SpecialRule getBaseRule() {
        return rule;
    }

    private final MeleeWeapon getEquivalentWeapon() {
        return weapon;
    }

}
