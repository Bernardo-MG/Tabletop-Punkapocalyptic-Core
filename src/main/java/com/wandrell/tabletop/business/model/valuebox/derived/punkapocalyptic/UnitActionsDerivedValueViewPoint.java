package com.wandrell.tabletop.business.model.valuebox.derived.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.AttributeBonusMutation;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.MutantUnit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Mutation;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitListener;
import com.wandrell.tabletop.business.model.valuebox.derived.AbstractDerivedValueViewPoint;
import com.wandrell.tabletop.business.model.valuebox.event.ValueBoxEvent;

public final class UnitActionsDerivedValueViewPoint extends
        AbstractDerivedValueViewPoint {

    private final Integer baseValue;
    private final Unit    unit;

    public UnitActionsDerivedValueViewPoint(final Integer baseValue,
            final Unit unit) {
        super();

        this.baseValue = baseValue;
        this.unit = unit;

        unit.addUnitListener(new UnitListener() {

            @Override
            public final void statusChanged(final EventObject e) {
                final Integer value;

                value = getValue();

                fireValueChangedEvent(new ValueBoxEvent(this, value, value));
            }

            @Override
            public final void valorationChanged(final EventObject e) {}

        });
    }

    @Override
    public final Integer getValue() {
        Integer result;

        result = getBaseValue();

        if (getUnit() instanceof MutantUnit) {
            for (final Mutation mutation : ((MutantUnit) getUnit())
                    .getMutations()) {
                if (mutation instanceof AttributeBonusMutation) {
                    result += ((AttributeBonusMutation) mutation)
                            .getActionsBonus();
                }
            }
        }

        return result;
    }

    private final Integer getBaseValue() {
        return baseValue;
    }

    private final Unit getUnit() {
        return unit;
    }

}
