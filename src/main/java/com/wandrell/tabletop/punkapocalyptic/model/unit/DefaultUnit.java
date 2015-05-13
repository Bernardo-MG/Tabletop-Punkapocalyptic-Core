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
package com.wandrell.tabletop.punkapocalyptic.model.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.JPAArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.event.ValorationListener;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.JPAArmor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.AttributesListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.UnitBonusAttributesHolder;

public final class DefaultUnit implements Unit, MutantUnit {

    private ArmorOption                  armor     = new JPAArmorOption(
                                                           new JPAArmor(
                                                                   "unarmored",
                                                                   0), 0);
    private final AttributesHolder       attributes;
    private final Collection<Equipment>  equipment = new LinkedHashSet<Equipment>();
    private final EventListenerList      listeners = new EventListenerList();
    private final ValorationListener     listenerStatus;
    private final Collection<Mutation>   mutations = new LinkedHashSet<Mutation>();
    private final UnitTemplate           template;
    private final Collection<UnitWeapon> weapons   = new LinkedHashSet<UnitWeapon>();

    {
        final Unit unit;

        unit = this;
        listenerStatus = new ValorationListener() {

            @Override
            public void valorationChanged(final ValueChangeEvent event) {
                fireValorationChangedEvent(new ValueChangeEvent(unit,
                        event.getOldValue(), event.getNewValue()));
            }

        };
    }

    public DefaultUnit(final DefaultUnit unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        // TODO: Set the base armor in some other way

        template = unit.template;
        attributes = new UnitBonusAttributesHolder(this);

        armor = unit.armor;

        for (final Equipment e : unit.equipment) {
            equipment.add(e);
        }

        for (final UnitWeapon w : unit.weapons) {
            weapons.add(w);
        }

        setAttributesListeners();
    }

    public DefaultUnit(final UnitTemplate template) {
        super();

        checkNotNull(template, "Received a null pointer as template");

        this.template = template;

        attributes = new UnitBonusAttributesHolder(this);

        setAttributesListeners();
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        checkNotNull(equipment, "Received a null pointer as equipment");
        final Integer valorationOld;

        valorationOld = getValoration();

        getEquipmentModifiable().add(equipment);

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final void addMutation(final Mutation mutation) {
        checkNotNull(equipment, "Received a null pointer as mutation");

        final Integer valorationOld;

        valorationOld = getValoration();

        getMutationsModifiable().add(mutation);

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
        fireMutationAddedEvent(new EventObject(this));
    }

    @Override
    public final void addUnitListener(final UnitListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(UnitListener.class, listener);
    }

    @Override
    public final void addWeapon(final UnitWeapon weapon) {
        checkNotNull(weapon, "Received a null pointer as weapon");

        final Integer valorationOld;

        valorationOld = getValoration();

        getWeaponsModifiable().add(weapon);

        weapon.addValorationListener(getStatusListener());

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final void clearEquipment() {
        final Integer valorationOld;

        valorationOld = getValoration();

        getEquipmentModifiable().clear();

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final void clearMutations() {
        getMutationsModifiable().clear();
    }

    @Override
    public final void clearWeapons() {
        final Integer valorationOld;

        valorationOld = getValoration();

        getWeaponsModifiable().clear();

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final DefaultUnit createNewInstance() {
        return new DefaultUnit(this);
    }

    @Override
    public final ArmorOption getArmor() {
        return armor;
    }

    @Override
    public final AttributesHolder getAttributes() {
        return attributes;
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return Collections.unmodifiableCollection(getEquipmentModifiable());
    }

    @Override
    public final Collection<Mutation> getMutations() {
        return Collections.unmodifiableCollection(getMutationsModifiable());
    }

    @Override
    public final String getName() {
        return getUnitTemplate().getNameToken();
    }

    @Override
    public final UnitTemplate getUnitTemplate() {
        return template;
    }

    @Override
    public final Integer getValoration() {
        Integer valoration;

        valoration = getUnitTemplate().getBaseCost();

        for (final UnitWeapon weapon : getWeapons()) {
            valoration += weapon.getCost();
        }

        for (final Equipment equipment : getEquipment()) {
            valoration += equipment.getCost();
        }

        if (getArmor() != null) {
            valoration += getArmor().getCost();
        }

        for (final Mutation mutation : getMutations()) {
            valoration += mutation.getCost();
        }

        return valoration;
    }

    @Override
    public final Collection<UnitWeapon> getWeapons() {
        return Collections.unmodifiableCollection(getWeaponsModifiable());
    }

    @Override
    public final void removeEquipment(final Equipment equipment) {
        checkNotNull(equipment, "Received a null pointer as equipment");

        final Integer valorationOld;

        valorationOld = getValoration();

        getEquipmentModifiable().remove(equipment);

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final void removeMutation(final Mutation mutation) {
        checkNotNull(mutation, "Received a null pointer as mutation");

        final Integer valorationOld;

        valorationOld = getValoration();

        getMutationsModifiable().remove(mutation);

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
        fireMutationRemovedEvent(new EventObject(this));
    }

    @Override
    public final void removeUnitListener(final UnitListener listener) {
        getListeners().remove(UnitListener.class, listener);
    }

    @Override
    public final void removeWeapon(final UnitWeapon weapon) {
        checkNotNull(armor, "Received a null pointer as weapon");

        final Integer valorationOld;

        valorationOld = getValoration();

        getWeaponsModifiable().remove(weapon);

        weapon.removeValorationListener(getStatusListener());

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final void setArmor(final ArmorOption armor) {
        checkNotNull(armor, "Received a null pointer as armor");

        final Integer valorationOld;

        valorationOld = getValoration();

        this.armor = armor;

        fireValorationChangedEvent(new ValueChangeEvent(this, valorationOld,
                getValoration()));
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getName())
                .toString();
    }

    private final void fireActionsChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.actionsChanged(evt);
        }
    }

    private final void fireAgilityChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.agilityChanged(evt);
        }
    }

    private final void fireCombatChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.combatChanged(evt);
        }
    }

    private final void fireMutationAddedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.mutationAdded(evt);
        }
    }

    private final void fireMutationRemovedEvent(final EventObject evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.mutationRemoved(evt);
        }
    }

    private final void firePrecisionChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.precisionChanged(evt);
        }
    }

    private final void fireStrengthChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.strengthChanged(evt);
        }
    }

    private final void fireTechChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.techChanged(evt);
        }
    }

    private final void fireToughnessChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.toughnessChanged(evt);
        }
    }

    private final void fireValorationChangedEvent(final ValueChangeEvent evt) {
        final UnitListener[] listnrs;

        listnrs = getListeners().getListeners(UnitListener.class);
        for (final UnitListener l : listnrs) {
            l.valorationChanged(evt);
        }
    }

    private final Collection<Equipment> getEquipmentModifiable() {
        return equipment;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final Collection<Mutation> getMutationsModifiable() {
        return mutations;
    }

    private final ValorationListener getStatusListener() {
        return listenerStatus;
    }

    private final Collection<UnitWeapon> getWeaponsModifiable() {
        return weapons;
    }

    private final void setAttributesListeners() {
        getAttributes().addAttributesListener(new AttributesListener() {

            @Override
            public final void actionsChanged(final ValueChangeEvent event) {
                fireActionsChangedEvent(event);
            }

            @Override
            public final void agilityChanged(final ValueChangeEvent event) {
                fireAgilityChangedEvent(event);
            }

            @Override
            public final void combatChanged(final ValueChangeEvent event) {
                fireCombatChangedEvent(event);
            }

            @Override
            public final void precisionChanged(final ValueChangeEvent event) {
                firePrecisionChangedEvent(event);
            }

            @Override
            public final void strengthChanged(final ValueChangeEvent event) {
                fireStrengthChangedEvent(event);
            }

            @Override
            public final void techChanged(final ValueChangeEvent event) {
                fireTechChangedEvent(event);
            }

            @Override
            public final void toughnessChanged(final ValueChangeEvent event) {
                fireToughnessChangedEvent(event);
            }

        });
    }

}
