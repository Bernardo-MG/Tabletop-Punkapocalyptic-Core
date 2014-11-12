package com.wandrell.tabletop.business.model.punkapocalyptic.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.tabletop.business.model.punkapocalyptic.RangedValue;
import com.wandrell.tabletop.business.model.punkapocalyptic.event.ValorationListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.specialrule.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.UnitDependant;

public final class UnitBasedStrengthRangedWeapon implements RangedWeapon,
        UnitDependant {

    private final RangedWeapon baseWeapon;
    private Unit               unit;

    public UnitBasedStrengthRangedWeapon(final String name, final Integer cost,
            final RangedValue penetration, final RangedValue strength,
            final RangedValue distanceCM, final RangedValue distanceInches,
            final MeleeWeapon weaponMelee) {
        super();

        baseWeapon = new DefaultRangedWeapon(name, cost, penetration, strength,
                distanceCM, distanceInches, weaponMelee);
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
    public final void setRules(final Collection<SpecialRule> rules) {
        getBaseWeapon().setRules(rules);
    }

    @Override
    public final void setTwoHanded(final Boolean twoHanded) {
        getBaseWeapon().setTwoHanded(twoHanded);
    }

    @Override
    public final void setUnit(final Unit unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit;
    }

    private final RangedWeapon getBaseWeapon() {
        return baseWeapon;
    }

    private final Unit getUnit() {
        return unit;
    }

}
