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
package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.WeaponModifierSpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.ValorationListener;

public abstract class AbstractWeapon implements Weapon {

    private final Integer                       cost;
    private final Collection<WeaponEnhancement> enhancements = new LinkedList<>();
    private final EventListenerList             listeners    = new EventListenerList();
    private final String                        name;
    private final Collection<SpecialRule>       rules        = new LinkedHashSet<>();
    private Boolean                             twoHanded    = false;

    public AbstractWeapon(final AbstractWeapon weapon) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");

        name = weapon.name;

        cost = weapon.cost;
        twoHanded = weapon.twoHanded;

        rules.addAll(weapon.rules);
        for (final SpecialRule rule : rules) {
            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }

        enhancements.addAll(weapon.enhancements);
    }

    public AbstractWeapon(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

        this.name = name;

        this.cost = cost;
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

        final AbstractWeapon other;

        other = (AbstractWeapon) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getCost() {
        Integer costEnhance;

        costEnhance = 0;
        for (final WeaponEnhancement enhance : getEnhancementsModifiable()) {
            costEnhance += enhance.getCost();
        }

        return cost + costEnhance;
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return Collections.unmodifiableCollection(getEnhancementsModifiable());
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final Boolean isTwoHanded() {
        return twoHanded;
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
    public final void setRules(final Collection<SpecialRule> rules) {
        checkNotNull(rules, "Received a null pointer as rules");

        getSpecialRulesModifiable().clear();

        for (final SpecialRule rule : rules) {
            checkNotNull(rule, "Received a null pointer as rule");

            getSpecialRulesModifiable().add(rule);

            if (rule instanceof WeaponModifierSpecialRule) {
                ((WeaponModifierSpecialRule) rule).applyToWeapon(this);
            }
        }
    }

    @Override
    public final void setTwoHanded(final Boolean twoHanded) {
        checkNotNull(twoHanded,
                "Received a null pointer as the two handed status");

        this.twoHanded = twoHanded;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).add("rules", rules)
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

    protected final Collection<SpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

}
