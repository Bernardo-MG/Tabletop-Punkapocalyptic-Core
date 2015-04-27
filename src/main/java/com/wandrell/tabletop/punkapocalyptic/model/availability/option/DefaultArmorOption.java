package com.wandrell.tabletop.punkapocalyptic.model.availability.option;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;

public final class DefaultArmorOption implements ArmorOption {

    private final Armor   armor;
    private final Integer cost;

    public DefaultArmorOption(final Armor armor, final Integer cost) {
        super();

        this.armor = armor;
        this.cost = cost;
    }

    @Override
    public final Armor getArmor() {
        return armor;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

}
