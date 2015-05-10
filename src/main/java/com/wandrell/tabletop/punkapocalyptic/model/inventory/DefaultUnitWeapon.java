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
package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.Objects;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.event.ValorationListener;

public final class DefaultUnitWeapon implements UnitWeapon {

    private final Collection<WeaponEnhancement> enhancements = new LinkedList<WeaponEnhancement>();
    private final EventListenerList             listeners    = new EventListenerList();
    private final Weapon                        template;

    public DefaultUnitWeapon(final DefaultUnitWeapon weapon) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");

        template = weapon.template;
        enhancements.addAll(weapon.enhancements);
    }

    public DefaultUnitWeapon(final Weapon template) {
        super();

        checkNotNull(template, "Received a null pointer as template");

        this.template = template;
    }

    @Override
    public final void addEnhancement(final WeaponEnhancement enhancement) {
        checkNotNull(enhancement,
                "Received a null pointer as weapon enhancement");

        getEnhancementsModifiable().add(enhancement);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void addValorationListener(final ValorationListener listener) {
        checkNotNull(listener, "Received a null pointer as listener");

        getListeners().add(ValorationListener.class, listener);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final DefaultUnitWeapon other;

        other = (DefaultUnitWeapon) obj;
        return Objects.equals(template.getNameToken(),
                other.template.getNameToken());
    }

    @Override
    public final Integer getCost() {
        Integer costEnhance;

        costEnhance = 0;
        for (final WeaponEnhancement enhance : getEnhancementsModifiable()) {
            costEnhance += enhance.getCost();
        }

        return getTemplate().getCost() + costEnhance;
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return Collections.unmodifiableCollection(getEnhancementsModifiable());
    }

    @Override
    public final Weapon getTemplate() {
        return template;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(template.getNameToken());
    }

    @Override
    public final void removeEnhancement(final WeaponEnhancement enhancement) {
        getEnhancementsModifiable().remove(enhancement);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void
            removeValorationListener(final ValorationListener listener) {
        getListeners().remove(ValorationListener.class, listener);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("template", template)
                .add("enhancements", enhancements).toString();
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

    protected final Collection<WeaponEnhancement> getEnhancementsModifiable() {
        return enhancements;
    }

}
