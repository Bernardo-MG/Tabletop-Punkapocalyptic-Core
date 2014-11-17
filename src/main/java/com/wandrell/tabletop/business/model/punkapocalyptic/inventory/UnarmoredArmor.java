package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import java.util.Collection;
import java.util.LinkedList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;

public final class UnarmoredArmor implements Armor {

    private static final String name = "unarmored";

    public UnarmoredArmor() {
        super();

        // TODO: Acquire with a factory, make singleton
    }

    @Override
    public final Armor createNewInstance() {
        return this;
    }

    @Override
    public final Integer getArmor() {
        return 0;
    }

    @Override
    public final Integer getCost() {
        return 0;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return new LinkedList<>();
    }

    @Override
    public final void setCost(final Integer cost) {}

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

}
