/**
 * Copyright 2014 the original author or authors
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
package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.faction.Faction;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitEvent;
import com.wandrell.tabletop.business.model.valuebox.DefaultEditableValueBox;
import com.wandrell.tabletop.business.model.valuebox.EditableValueBox;
import com.wandrell.tabletop.business.model.valuebox.ValueBox;
import com.wandrell.tabletop.business.model.valuebox.derived.DerivedValueBox;
import com.wandrell.tabletop.business.util.event.ValueChangeEvent;
import com.wandrell.tabletop.business.util.event.ValueChangeListener;
import com.wandrell.tabletop.business.util.tag.punkapocalyptic.GangAware;

public final class DefaultGang implements Gang {

    private final EditableValueBox  bullets;
    private final Faction           faction;
    private final EventListenerList listeners = new EventListenerList();
    private final Collection<Unit>  units     = new LinkedList<>();
    private final ValueBox          valoration;
    private final ValorationBuilder valorationBuilder;

    public interface ValorationBuilder {

        public ValueBox getValoration(final Gang gang);

    }

    public DefaultGang(final DefaultGang gang) {
        super();

        checkNotNull(gang, "Received a null pointer as gang");

        faction = gang.faction;
        bullets = gang.getBulletsValueBox().createNewInstance();

        for (final Unit unit : gang.units) {
            units.add(unit.createNewInstance());
        }

        valorationBuilder = gang.valorationBuilder;

        valoration = valorationBuilder.getValoration(this);
        // TODO: Do in another way
        ((GangAware) ((DerivedValueBox) getValorationValueBox()).getViewPoint())
                .setGang(this);

        setBulletsListeners();
    }

    public DefaultGang(final Faction faction,
            final ValorationBuilder valorationBuilder) {
        super();

        checkNotNull(faction, "Received a null pointer as faction");
        checkNotNull(valorationBuilder,
                "Received a null pointer as valoration builder");

        this.faction = faction;

        bullets = new DefaultEditableValueBox(0, 0, Integer.MAX_VALUE);

        this.valorationBuilder = valorationBuilder;

        this.valoration = valorationBuilder.getValoration(this);

        setBulletsListeners();
    }

    @Override
    public final void addGangListener(final GangListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(GangListener.class, listener);
    }

    @Override
    public final void addUnit(final Unit unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        getUnitsModifiable().add(unit);

        fireUnitAddedEvent(new UnitEvent(this, unit));

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void clearUnits() {
        final Collection<Unit> units;

        units = new LinkedList<>(getUnits());

        getUnitsModifiable().clear();

        for (final Unit unit : units) {
            fireUnitRemovedEvent(new UnitEvent(this, unit));
        }

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final DefaultGang createNewInstance() {
        return new DefaultGang(this);
    }

    @Override
    public final Integer getBullets() {
        return getBulletsValueBox().getValue();
    }

    public final EditableValueBox getBulletsValueBox() {
        return bullets;
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Collection<Unit> getUnits() {
        return Collections.unmodifiableCollection(getUnitsModifiable());
    }

    @Override
    public final Integer getValoration() {
        return getValorationValueBox().getValue();
    }

    public final ValueBox getValorationValueBox() {
        return valoration;
    }

    @Override
    public final void removeGangListener(final GangListener listener) {
        getListeners().remove(GangListener.class, listener);
    }

    @Override
    public final void removeUnit(final Unit unit) {
        final Iterator<Unit> itr;
        Boolean found;

        checkNotNull(unit, "Received a null pointer as unit");

        itr = getUnitsModifiable().iterator();
        found = false;
        while ((itr.hasNext()) && (!found)) {
            found = (itr.next() == unit);

            if (found) {
                itr.remove();
                fireUnitRemovedEvent(new UnitEvent(this, unit));
                fireValorationChangedEvent(new EventObject(this));
            }
        }

    }

    @Override
    public final void setBullets(final Integer bullets) {
        getBulletsValueBox().setValue(bullets);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("faction", faction)
                .add("units", units).toString();
    }

    private final void fireBulletsChangedEvent(final EventObject evt) {
        final GangListener[] listnrs;

        listnrs = getListeners().getListeners(GangListener.class);
        for (final GangListener l : listnrs) {
            l.bulletsChanged(evt);
        }
    }

    private final void fireUnitAddedEvent(final UnitEvent evt) {
        final GangListener[] listnrs;

        listnrs = getListeners().getListeners(GangListener.class);
        for (final GangListener l : listnrs) {
            l.unitAdded(evt);
        }
    }

    private final void fireUnitRemovedEvent(final UnitEvent evt) {
        final GangListener[] listnrs;

        listnrs = getListeners().getListeners(GangListener.class);
        for (final GangListener l : listnrs) {
            l.unitRemoved(evt);
        }
    }

    private final void fireValorationChangedEvent(final EventObject evt) {
        final GangListener[] listnrs;

        listnrs = getListeners().getListeners(GangListener.class);
        for (final GangListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<Unit> getUnitsModifiable() {
        return units;
    }

    private final void setBulletsListeners() {
        getBulletsValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent evt) {
                fireBulletsChangedEvent(new EventObject(this));
                fireValorationChangedEvent(new EventObject(this));
            }

        });
    }

}
