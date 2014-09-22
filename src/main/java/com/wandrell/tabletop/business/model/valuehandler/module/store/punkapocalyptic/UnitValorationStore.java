package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitListener;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.module.store.StoreModule;
import com.wandrell.tabletop.business.service.punkapocalyptic.RulesetService;

public final class UnitValorationStore extends StoreModule {

    private final UnitListener   listener;
    private final RulesetService serviceRuleset;
    private Unit                 unit;

    {
        listener = new UnitListener() {

            @Override
            public final void statusChanged(final EventObject e) {
                if (getParent() != null) {
                    fireValueChangedEvent(new ValueHandlerEvent(getParent()));
                }
            }

        };
    }

    public UnitValorationStore(final RulesetService service) {
        super();

        if (service == null) {
            throw new NullPointerException(
                    "Received a null pointer as ruleset service");
        }

        serviceRuleset = service;
    }

    public UnitValorationStore(final Unit unit, final RulesetService service) {
        super();

        if (unit == null) {
            throw new NullPointerException("Received a null pointer as unit");
        }

        if (service == null) {
            throw new NullPointerException(
                    "Received a null pointer as ruleset service");
        }

        this.unit = unit;
        serviceRuleset = service;

        unit.addUnitListener(getListener());
    }

    public UnitValorationStore(final UnitValorationStore store) {
        super(store);

        if (store == null) {
            throw new NullPointerException("Received a null pointer as store");
        }

        unit = store.unit;
        serviceRuleset = store.serviceRuleset;
    }

    @Override
    public final UnitValorationStore createNewInstance() {
        return new UnitValorationStore(this);
    }

    @Override
    public final Integer getValue() {
        return getRulesetService().getUnitValoration(getUnit());
    }

    public final void setUnit(final Unit unit) {
        if (this.unit != null) {
            this.unit.removeUnitListener(getListener());
        }

        this.unit = unit;

        unit.addUnitListener(getListener());
    }

    private final UnitListener getListener() {
        return listener;
    }

    private final RulesetService getRulesetService() {
        return serviceRuleset;
    }

    private final Unit getUnit() {
        return unit;
    }

}
