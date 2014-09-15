package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.valuehandler.module.store.StoreModule;

public class GangValorationStore extends StoreModule {

    private final Integer bulletCost;
    private Gang          gang;

    public GangValorationStore(final Gang gang, final Integer bulletCost) {
        super();

        if (gang == null) {
            throw new NullPointerException("Received a null pointer as gang");
        }

        if (bulletCost == null) {
            throw new NullPointerException(
                    "Received a null pointer as bullet cost");
        }

        this.gang = gang;
        this.bulletCost = bulletCost;
    }

    public GangValorationStore(final GangValorationStore store) {
        super(store);

        if (store == null) {
            throw new NullPointerException("Received a null pointer as store");
        }

        gang = store.gang;
        bulletCost = store.bulletCost;
    }

    @Override
    public final GangValorationStore createNewInstance() {
        return new GangValorationStore(this);
    }

    @Override
    public final Integer getValue() {
        Integer cost;

        cost = 0;
        for (final Unit unit : getBand().getUnits()) {
            cost += unit.getValoration().getStoredValue();
        }

        cost += (getBand().getBullets().getStoredValue() * getBulletCost());

        return cost;
    }

    public final void setBand(final Gang band) {
        this.gang = band;
    }

    protected final Gang getBand() {
        return gang;
    }

    protected final Integer getBulletCost() {
        return bulletCost;
    }

}
