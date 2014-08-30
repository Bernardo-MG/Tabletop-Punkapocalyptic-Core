package com.wandrell.tabletop.punkapocalyptic.valuehandler.module.generator;

import com.wandrell.tabletop.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.valuehandler.module.StoreModule;

public class UnitCostStore extends StoreModule {

    private final Unit unit;

    public UnitCostStore(final Unit unit) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as unit");
        }

        this.unit = unit;
    }

    public UnitCostStore(final UnitCostStore store) {
        super(store);

        if (store == null) {
            throw new NullPointerException("Received a null pointer as store");
        }

        unit = store.unit;
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

        cost = getUnit().getBaseCost();

        for (final Weapon weapon : getUnit().getWeapons()) {
            cost += weapon.getCost();
        }

        if (getUnit().getArmor() != null) {
            cost += getUnit().getArmor().getCost();
        }

        return cost;
    }

    @Override
    public final void setValue(final Integer value) {}

    protected final Unit getUnit() {
        return unit;
    }

}
