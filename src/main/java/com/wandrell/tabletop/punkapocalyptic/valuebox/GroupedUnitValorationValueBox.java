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

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.stat.event.ValueChangeEvent;
import com.wandrell.tabletop.stat.event.ValueChangeListener;
import com.wandrell.tabletop.stat.valuebox.AbstractValueBoxEventFirer;
import com.wandrell.tabletop.stat.valuebox.ValueBox;

public final class GroupedUnitValorationValueBox extends
        AbstractValueBoxEventFirer {

    private final ValueChangeListener listener;
    private final ValueBox            size;
    private final Unit                unit;

    {
        listener = new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireValueChangedEvent(new ValueChangeEvent(this, getValue(),
                        getValue()));
            }

        };
    }

    public GroupedUnitValorationValueBox(
            final GroupedUnitValorationValueBox store) {
        super();

        unit = store.unit;
        size = store.size;
    }

    public GroupedUnitValorationValueBox(final Unit unit, final ValueBox size) {
        super();

        this.unit = unit;
        this.size = size;

        size.addValueChangeListener(listener);
    }

    @Override
    public final Integer getValue() {
        return getUnit().getValoration() * getSize().getValue();
    }

    @Override
    public final void setValue(final Integer value) {
        throw new UnsupportedOperationException("Setting the value is disabled");
    }

    private final ValueBox getSize() {
        return size;
    }

    private final Unit getUnit() {
        return unit;
    }

}
