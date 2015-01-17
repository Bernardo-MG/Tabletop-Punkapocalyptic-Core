/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.business.model.valuebox.derived.punkapocalyptic;

import java.util.EventObject;

import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitListenerAdapter;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.mutation.AttributeBonusMutation;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.mutation.MutantUnit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.mutation.Mutation;
import com.wandrell.tabletop.business.model.valuebox.derived.AbstractDerivedValueViewPoint;
import com.wandrell.tabletop.business.util.event.ValueChangeEvent;

public final class UnitCombatDerivedValueViewPoint extends
        AbstractDerivedValueViewPoint {

    private final Integer baseValue;
    private final Unit    unit;

    public UnitCombatDerivedValueViewPoint(final Integer baseValue,
            final Unit unit) {
        super();

        this.baseValue = baseValue;
        this.unit = unit;

        unit.addUnitListener(new UnitListenerAdapter() {

            @Override
            public final void mutationChanged(final EventObject event) {
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
                            .getCombatBonus();
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
