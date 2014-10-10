package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListenerAdapter;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.module.store.AbstractStoreModule;
import com.wandrell.tabletop.business.model.valuehandler.module.store.StoreModule;
import com.wandrell.tabletop.business.service.punkapocalyptic.RulesetService;

public final class GangValorationStore extends AbstractStoreModule {

    private Gang                      gang;
    private final GangListenerAdapter listener;
    private final RulesetService      serviceRuleset;

    {
        final StoreModule source = this;
        listener = new GangListenerAdapter() {

            @Override
            public final void statusChanged(final EventObject e) {
                fireValueChangedEvent(new ValueHandlerEvent(source,
                        source.getValue(), source.getValue()));
            }

        };
    }

    public GangValorationStore(final Gang gang, final RulesetService service) {
        super();

        if (gang == null) {
            throw new NullPointerException("Received a null pointer as gang");
        }

        if (service == null) {
            throw new NullPointerException(
                    "Received a null pointer as ruleset service");
        }

        this.gang = gang;
        serviceRuleset = service;

        gang.addGangListener(getListener());
    }

    public GangValorationStore(final GangValorationStore store) {
        super(store);

        if (store == null) {
            throw new NullPointerException("Received a null pointer as store");
        }

        gang = store.gang;
        serviceRuleset = store.serviceRuleset;
    }

    public GangValorationStore(final RulesetService service) {
        super();

        if (service == null) {
            throw new NullPointerException(
                    "Received a null pointer as ruleset service");
        }

        serviceRuleset = service;
    }

    @Override
    public final GangValorationStore createNewInstance() {
        return new GangValorationStore(this);
    }

    @Override
    public final Integer getValue() {
        return getRulesetService().getGangValoration(getGang());
    }

    public final void setGang(final Gang gang) {
        if (this.gang != null) {
            this.gang.removeGangListener(getListener());
        }

        this.gang = gang;

        gang.addGangListener(getListener());
    }

    private final Gang getGang() {
        return gang;
    }

    private final GangListener getListener() {
        return listener;
    }

    private final RulesetService getRulesetService() {
        return serviceRuleset;
    }

}
