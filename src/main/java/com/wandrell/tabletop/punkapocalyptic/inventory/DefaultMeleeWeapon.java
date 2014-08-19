package com.wandrell.tabletop.punkapocalyptic.inventory;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

public final class DefaultMeleeWeapon extends AbstractWeapon implements
        MeleeWeapon {

    private final Integer combat;

    public DefaultMeleeWeapon(final DefaultMeleeWeapon weapon) {
        super(weapon);

        combat = weapon.combat;
    }

    public DefaultMeleeWeapon(final String name, final Integer strength,
            final Integer penetration, final Integer combat,
            final Collection<SpecialRule> rules) {
        super(name, strength, penetration, rules);

        this.combat = combat;
    }

    @Override
    public final Integer getCombatModifier() {
        return combat;
    }

}
