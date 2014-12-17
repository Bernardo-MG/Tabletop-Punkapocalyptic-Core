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
import com.wandrell.tabletop.business.model.interval.DefaultInterval;
import com.wandrell.tabletop.business.model.punkapocalyptic.event.ValorationListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.faction.Faction;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.GangListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitEvent;
import com.wandrell.tabletop.business.model.valuehandler.AbstractValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.EditableValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.ModularDerivedValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.ModularEditableValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerEvent;
import com.wandrell.tabletop.business.model.valuehandler.event.ValueHandlerListener;
import com.wandrell.tabletop.business.model.valuehandler.module.generator.DefaultGenerator;
import com.wandrell.tabletop.business.util.tag.punkapocalyptic.GangAware;

public final class DefaultGang implements Gang {

    private final EditableValueHandler bullets;
    private final Faction              faction;
    private final EventListenerList    listeners = new EventListenerList();
    private final Collection<Unit>     units     = new LinkedList<>();
    private final ValueHandler         valoration;

    public DefaultGang(final DefaultGang gang) {
        super();

        checkNotNull(gang, "Received a null pointer as gang");

        faction = gang.faction;
        bullets = gang.bullets.createNewInstance();

        for (final Unit unit : gang.units) {
            units.add(unit.createNewInstance());
        }

        valoration = gang.valoration.createNewInstance();
        // TODO: Do in another way
        ((GangAware) ((ModularDerivedValueHandler) valoration).getStore())
                .setGang(this);

        ((AbstractValueHandler) bullets)
                .addValueEventListener(new ValueHandlerListener() {

                    @Override
                    public final void valueChanged(final ValueHandlerEvent evt) {
                        fireValorationChangedEvent(new EventObject(this));
                    }

                });
    }

    public DefaultGang(final Faction faction, final ValueHandler valoration) {
        super();

        checkNotNull(faction, "Received a null pointer as faction");
        checkNotNull(valoration, "Received a null pointer as valoration");

        this.faction = faction;

        bullets = new ModularEditableValueHandler("bullets",
                new DefaultGenerator(), new DefaultInterval(0,
                        Integer.MAX_VALUE), 0);

        this.valoration = valoration;

        ((AbstractValueHandler) bullets)
                .addValueEventListener(new ValueHandlerListener() {

                    @Override
                    public final void valueChanged(final ValueHandlerEvent evt) {
                        fireValorationChangedEvent(new EventObject(this));
                    }

                });
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
    public final void addValorationListener(final ValorationListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(ValorationListener.class, listener);
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
    public final EditableValueHandler getBullets() {
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
    public final ValueHandler getValoration() {
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
    public final void
            removeValorationListener(final ValorationListener listener) {
        getListeners().remove(ValorationListener.class, listener);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("faction", faction)
                .add("units", units).toString();
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
        final ValorationListener[] listnrs;

        listnrs = getListeners().getListeners(ValorationListener.class);
        for (final ValorationListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<Unit> getUnitsModifiable() {
        return units;
    }

}
