package com.wandrell.tabletop.business.model.valuehandler.module.store.punkapocalyptic;

import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerListener;
import com.wandrell.tabletop.business.model.valuehandler.module.store.AbstractStoreModule;

public final class GroupedUnitStoreModule extends AbstractStoreModule {

    private final ValueHandlerListener listener;
    private final ValueHandler         size;
    private final Integer              valoration;

    {
        listener = new ValueHandlerListener() {

            @Override
            public final void valueChanged(final ValueHandlerEvent event) {
                fireValueChangedEvent(new ValueHandlerEvent(this, getValue(),
                        getValue()));
            }

        };
    }

    public GroupedUnitStoreModule(final GroupedUnitStoreModule store) {
        super();

        valoration = store.valoration;
        size = store.size;
    }

    public GroupedUnitStoreModule(final Integer valoration,
            final ValueHandler size) {
        super();

        this.valoration = valoration;
        this.size = size;

        size.addValueEventListener(listener);
    }

    @Override
    public final GroupedUnitStoreModule createNewInstance() {
        return new GroupedUnitStoreModule(this);
    }

    @Override
    public final Integer getValue() {
        return getValoration() * getSize().getValue();
    }

    private final ValueHandler getSize() {
        return size;
    }

    private final Integer getValoration() {
        return valoration;
    }

}
