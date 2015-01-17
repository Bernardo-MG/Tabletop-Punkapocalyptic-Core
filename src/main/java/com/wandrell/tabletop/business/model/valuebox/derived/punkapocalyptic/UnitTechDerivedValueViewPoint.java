package com.wandrell.tabletop.business.model.valuebox.derived.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.AttributeBonusMutation;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.MutantUnit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Mutation;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitListenerAdapter;
import com.wandrell.tabletop.business.model.valuebox.derived.AbstractDerivedValueViewPoint;
import com.wandrell.tabletop.business.util.event.ValueChangeEvent;

public final class UnitTechDerivedValueViewPoint extends
        AbstractDerivedValueViewPoint {

    private final Integer baseValue;
    private final Unit    unit;

    public UnitTechDerivedValueViewPoint(final Integer baseValue,
            final Unit unit) {
        super();

        this.baseValue = baseValue;
        this.unit = unit;

        unit.addUnitListener(new UnitListenerAdapter() {

            @Override
            public final void mutationChanged(final EventObject e) {
                final Integer value;

                value = getValue();

                fireValueChangedEvent(new ValueChangeEvent(this, value, value));
            }

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
                            .getTechBonus();
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
