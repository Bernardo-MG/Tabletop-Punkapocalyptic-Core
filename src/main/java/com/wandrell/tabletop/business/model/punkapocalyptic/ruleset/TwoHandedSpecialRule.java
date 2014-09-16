package com.wandrell.tabletop.business.model.punkapocalyptic.ruleset;

import com.wandrell.tabletop.business.conf.SpecialRuleNameConf;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;

public final class TwoHandedSpecialRule implements SpecialRule, WeaponModifier {

    private final SpecialRule rule;

    public TwoHandedSpecialRule() {
        super();

        rule = new DefaultSpecialRule(SpecialRuleNameConf.TWO_HANDED);
    }

    @Override
    public final void applyToWeapon(final Weapon weapon) {
        weapon.setHands(2);
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

    protected final SpecialRule getBaseRule() {
        return rule;
    }

}
