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
package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.model.event.ValorationListener;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

public final class UnitBasedStrengthRangedWeapon implements RangedWeapon,
        UnitDependantWeapon {

    private final RangedWeapon baseWeapon;
    private Unit               unit;

    public UnitBasedStrengthRangedWeapon(final Unit unit, final String name,
            final Integer cost, final Collection<SpecialRule> rules,
            final RangedValue penetration, final RangedValue strength,
            final RangedValue distanceCM, final RangedValue distanceInches,
            final MeleeWeapon weaponMelee) {
        super();

        // TODO: Maybe this should be on the framework library

        this.unit = unit;

        baseWeapon = new DefaultRangedWeapon(name, cost, rules, penetration,
                strength, distanceCM, distanceInches, weaponMelee);
    }

    public UnitBasedStrengthRangedWeapon(
            final UnitBasedStrengthRangedWeapon weapon) {
        super();

        checkNotNull(weapon, "Received a null pointer as weapon");

        baseWeapon = weapon.baseWeapon.createNewInstance();
        unit = weapon.unit;
    }

    @Override
    public final void addEnhancement(final WeaponEnhancement enhancement) {
        getBaseWeapon().addEnhancement(enhancement);
    }

    @Override
    public final void addValorationListener(final ValorationListener listener) {
        getBaseWeapon().addValorationListener(listener);
    }

    @Override
    public final UnitBasedStrengthRangedWeapon createNewInstance() {
        return new UnitBasedStrengthRangedWeapon(this);
    }

    @Override
    public final Integer getCost() {
        return getBaseWeapon().getCost();
    }

    @Override
    public final RangedValue getDistancesImperialUnits() {
        return getBaseWeapon().getDistancesImperialUnits();
    }

    @Override
    public final RangedValue getDistancesMetricSystem() {
        return getBaseWeapon().getDistancesMetricSystem();
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return getBaseWeapon().getEnhancements();
    }

    @Override
    public final Integer getLongPenetration() {
        return getBaseWeapon().getLongPenetration();
    }

    @Override
    public final Integer getLongStrength() {
        return getUnit().getStrength() + getBaseWeapon().getLongStrength();
    }

    @Override
    public final Integer getMediumPenetration() {
        return getBaseWeapon().getMediumPenetration();
    }

    @Override
    public final Integer getMediumStrength() {
        return getUnit().getStrength() + getBaseWeapon().getMediumStrength();
    }

    @Override
    public final MeleeWeapon getMeleeEquivalent() {
        return getBaseWeapon().getMeleeEquivalent();
    }

    @Override
    public final String getName() {
        return getBaseWeapon().getName();
    }

    @Override
    public final Integer getShortPenetration() {
        return getBaseWeapon().getShortPenetration();
    }

    @Override
    public final Integer getShortStrength() {
        return getUnit().getStrength() + getBaseWeapon().getShortStrength();
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getBaseWeapon().getSpecialRules();
    }

    @Override
    public final Boolean isFirearm() {
        return getBaseWeapon().isFirearm();
    }

    @Override
    public final Boolean isTwoHanded() {
        return getBaseWeapon().isTwoHanded();
    }

    @Override
    public final void removeEnhancement(final WeaponEnhancement enhancement) {
        getBaseWeapon().removeEnhancement(enhancement);
    }

    @Override
    public final void
            removeValorationListener(final ValorationListener listener) {
        getBaseWeapon().removeValorationListener(listener);
    }

    @Override
    public final void setFirearm(final Boolean firearm) {
        getBaseWeapon().setFirearm(firearm);
    }

    @Override
    public final void setMeleeEquivalent(final MeleeWeapon weapon) {
        getBaseWeapon().setMeleeEquivalent(weapon);
    }

    @Override
    public final void setTwoHanded(final Boolean twoHanded) {
        getBaseWeapon().setTwoHanded(twoHanded);
    }

    @Override
    public final void setUnit(final Unit unit) {
        this.unit = unit;
    }

    private final RangedWeapon getBaseWeapon() {
        return baseWeapon;
    }

    private final Unit getUnit() {
        return unit;
    }

}
