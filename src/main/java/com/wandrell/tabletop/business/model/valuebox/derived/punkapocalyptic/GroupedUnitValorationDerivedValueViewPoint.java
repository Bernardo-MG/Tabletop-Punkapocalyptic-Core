package com.wandrell.tabletop.business.model.valuebox.derived.punkapocalyptic;

import com.wandrell.tabletop.business.model.valuebox.ValueBox;
import com.wandrell.tabletop.business.model.valuebox.derived.AbstractDerivedValueViewPoint;
import com.wandrell.tabletop.business.model.valuebox.event.ValueBoxEvent;
import com.wandrell.tabletop.business.model.valuebox.event.ValueBoxListener;

public final class GroupedUnitValorationDerivedValueViewPoint extends
        AbstractDerivedValueViewPoint {

    private final ValueBoxListener listener;
    private final ValueBox         size;
    private final Integer          valoration;

    {
        listener = new ValueBoxListener() {

            @Override
            public final void valueChanged(final ValueBoxEvent event) {
                fireValueChangedEvent(new ValueBoxEvent(this, getValue(),
                        getValue()));
            }

        };
    }

    public GroupedUnitValorationDerivedValueViewPoint(
            final GroupedUnitValorationDerivedValueViewPoint store) {
        super();

        valoration = store.valoration;
        size = store.size;
    }

    public GroupedUnitValorationDerivedValueViewPoint(final Integer valoration,
            final ValueBox size) {
        super();

        this.valoration = valoration;
        this.size = size;

        size.addValueEventListener(listener);
    }

    @Override
    public final Integer getValue() {
        return getValoration() * getSize().getValue();
    }

    private final ValueBox getSize() {
        return size;
    }

    private final Integer getValoration() {
        return valoration;
    }

}
