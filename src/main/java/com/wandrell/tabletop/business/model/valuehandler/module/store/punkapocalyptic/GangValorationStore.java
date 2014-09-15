package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Gang;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListenerAdapter;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.module.store.StoreModule;
import com.wandrell.tabletop.business.service.punkapocalyptic.RulesetService;

public class GangValorationStore extends StoreModule {

    private Gang                      gang;
    private final GangListenerAdapter listener;
    private final RulesetService      serviceRuleset;

    {
        listener = new GangListenerAdapter() {

            @Override
            public final void statusChanged(final EventObject e) {
                if (getParent() != null) {
                    fireValueChangedEvent(new ValueHandlerEvent(getParent()));
                }
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

    protected final Gang getGang() {
        return gang;
    }

    protected final GangListener getListener() {
        return listener;
    }

    protected final RulesetService getRulesetService() {
        return serviceRuleset;
    }

}
