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
package com.wandrell.tabletop.punkapocalyptic.model.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.valuebox.DefaultValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class DefaultGroupedUnit implements GroupedUnit, MutantUnit {

    private final EventListenerList listeners = new EventListenerList();
    private final ValueBox          size      = new DefaultValueBox();
    private final Unit              unit;

    public DefaultGroupedUnit(final DefaultGroupedUnit unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit.unit.createNewInstance();

        size.addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireValorationChangedEvent(new EventObject(this));
            }

        });
    }

    public DefaultGroupedUnit(final UnitTemplate template) {
        super();

        checkNotNull(template, "Received a null pointer as unit template");

        this.unit = new DefaultUnit(template);

        size.addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireValorationChangedEvent(new EventObject(this));
            }

        });
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        getWrappedUnit().addEquipment(equipment);
    }

    @Override
    public final void addMutation(final Mutation mutation) {
        if (getWrappedUnit() instanceof MutantUnit) {
            ((MutantUnit) getWrappedUnit()).addMutation(mutation);
        }
    }

    @Override
    public final void addUnitListener(final UnitListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(UnitListener.class, listener);

        getWrappedUnit().addUnitListener(listener);
    }

    @Override
    public final void addWeapon(final UnitWeapon weapon) {
        getWrappedUnit().addWeapon(weapon);
    }

    @Override
    public final void clearEquipment() {
        getWrappedUnit().clearEquipment();
    }

    @Override
    public final void clearMutations() {
        if (getWrappedUnit() instanceof MutantUnit) {
            ((MutantUnit) getWrappedUnit()).clearMutations();
        }
    }

    @Override
    public final void clearWeapons() {
        getWrappedUnit().clearWeapons();
    }

    @Override
    public final DefaultGroupedUnit createNewInstance() {
        return new DefaultGroupedUnit(this);
    }

    @Override
    public final ArmorOption getArmor() {
        return getWrappedUnit().getArmor();
    }

    @Override
    public final AttributesHolder getAttributes() {
        return getWrappedUnit().getAttributes();
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return getWrappedUnit().getEquipment();
    }

    @Override
    public final ValueBox getGroupSize() {
        return size;
    }

    @Override
    public final Collection<Mutation> getMutations() {
        final Collection<Mutation> mutations;

        if (getWrappedUnit() instanceof MutantUnit) {
            mutations = ((MutantUnit) getWrappedUnit()).getMutations();
        } else {
            mutations = new LinkedList<Mutation>();
        }

        return mutations;
    }

    @Override
    public final String getName() {
        return getWrappedUnit().getName();
    }

    @Override
    public final UnitTemplate getUnitTemplate() {
        return getWrappedUnit().getUnitTemplate();
    }

    @Override
    public final Integer getValoration() {
        return getWrappedUnit().getValoration() * getGroupSize().getValue();
    }

    @Override
    public final Collection<UnitWeapon> getWeapons() {
        return getWrappedUnit().getWeapons();
    }

    @Override
    public final void removeEquipment(final Equipment equipment) {
        getWrappedUnit().removeEquipment(equipment);
    }

    @Override
    public final void removeMutation(final Mutation mutation) {
        if (getWrappedUnit() instanceof MutantUnit) {
            ((MutantUnit) getWrappedUnit()).removeMutation(mutation);
        }
    }

    @Override
    public final void removeUnitListener(final UnitListener listener) {
        getListeners().remove(UnitListener.class, listener);
        getWrappedUnit().removeUnitListener(listener);
    }

    @Override
    public final void removeWeapon(final UnitWeapon weapon) {
        getWrappedUnit().removeWeapon(weapon);
    }

    @Override
    public final void setArmor(final ArmorOption armor) {
        getWrappedUnit().setArmor(armor);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final void fireValorationChangedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Unit getWrappedUnit() {
        return unit;
    }

}
