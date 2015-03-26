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

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.valuebox.AbstractValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class GroupedUnitValorationValueBox extends AbstractValueBox {

    private final ValueChangeListener listener;
    private final ValueBox            size;
    private final Integer             valoration;

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

        valoration = store.valoration;
        size = store.size;
    }

    public GroupedUnitValorationValueBox(final Integer valoration,
            final ValueBox size) {
        super();

        this.valoration = valoration;
        this.size = size;

        size.addValueChangeListener(listener);
    }

    @Override
    public final GroupedUnitValorationValueBox createNewInstance() {
        return new GroupedUnitValorationValueBox(this);
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
