package com.wandrell.tabletop.punkapocalyptic.valuehandler.module.generator;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.valuehandler.module.StoreModule;

public class UnitCostStore extends StoreModule {

    private final Armor              armor;
    private final Integer            baseCost;
    private final Collection<Weapon> weapons;

    public UnitCostStore(final Integer baseCost,
            final Collection<Weapon> weapons, final Armor armor) {
        super();

        this.baseCost = baseCost;
        this.weapons = weapons;
        this.armor = armor;
    }

    public UnitCostStore(final UnitCostStore store) {
        super(store);

        baseCost = store.baseCost;
        weapons = store.weapons;
        armor = store.armor;
    }

    @Override
    public final void addValue(final Integer value) {}

    @Override
    public final UnitCostStore createNewInstance() {
        return new UnitCostStore(this);
    }

    @Override
    public final Integer getValue() {
        Integer cost;

        cost = getBaseCost();

        for (final Weapon weapon : getWeapons()) {
            cost += weapon.getCost();
        }

        cost += getArmor().getCost();

        return cost;
    }

    @Override
    public final void setValue(final Integer value) {}

    protected final Armor getArmor() {
        return armor;
    }

    protected Integer getBaseCost() {
        return baseCost;
    }

    protected final Collection<Weapon> getWeapons() {
        return weapons;
    }

}
