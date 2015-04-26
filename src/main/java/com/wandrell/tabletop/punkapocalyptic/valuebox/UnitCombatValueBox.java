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
package com.wandrell.tabletop.punkapocalyptic.valuebox;

import java.util.EventObject;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListenerAdapter;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.AttributeBonusMutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.valuebox.AbstractValueBox;

public final class UnitCombatValueBox extends AbstractValueBox {

    private final Unit unit;

    public UnitCombatValueBox(final Unit unit) {
        super();

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
    public final UnitCombatValueBox createNewInstance() {
        return new UnitCombatValueBox(getUnit());
    }

    @Override
    public final Integer getValue() {
        Integer result;

        result = getUnit().getUnitTemplate().getAttributes().getCombat();

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

    private final Unit getUnit() {
        return unit;
    }

}
