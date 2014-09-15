package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.valuehandler.module.store.StoreModule;

public class UnitValorationStore extends StoreModule {

    private final Unit unit;

    public UnitValorationStore(final Unit unit) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as unit");
        }

        this.unit = unit;
    }

    public UnitValorationStore(final UnitValorationStore store) {
        super(store);

        if (store == null) {
            throw new NullPointerException("Received a null pointer as store");
        }

        unit = store.unit;
    }

    @Override
    public final UnitValorationStore createNewInstance() {
        return new UnitValorationStore(this);
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

    protected final Unit getUnit() {
        return unit;
    }

}
