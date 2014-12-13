package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.event.ValorationListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;
import com.wandrell.tabletop.business.model.valuehandler.ValueHandler;

public final class MutantUnitWrapper implements MutantUnit {

    private final Collection<Mutation> mutations = new LinkedHashSet<>();
    private final Unit                 unit;

    public MutantUnitWrapper(final MutantUnitWrapper unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit.unit.createNewInstance();
    }

    public MutantUnitWrapper(final Unit unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit;
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        getWrappedUnit().addEquipment(equipment);
    }

    @Override
    public final void addMutation(final Mutation mutation) {
        getMutationsModifiable().add(mutation);
    }

    @Override
    public final void addValorationListener(final ValorationListener listener) {
        getWrappedUnit().addValorationListener(listener);
    }

    @Override
    public final void addWeapon(final Weapon weapon) {
        getWrappedUnit().addWeapon(weapon);
    }

    @Override
    public final void clearEquipment() {
        getWrappedUnit().clearEquipment();
    }

    @Override
    public final void clearMutations() {
        getMutationsModifiable().clear();
    }

    @Override
    public final void clearWeapons() {
        getWrappedUnit().clearWeapons();
    }

    @Override
    public final MutantUnitWrapper createNewInstance() {
        return new MutantUnitWrapper(this);
    }

    @Override
    public final Integer getActions() {
        return getWrappedUnit().getActions();
    }

    @Override
    public final Integer getAgility() {
        return getWrappedUnit().getAgility();
    }

    @Override
    public final Armor getArmor() {
        return getWrappedUnit().getArmor();
    }

    @Override
    public final Integer getBaseCost() {
        return getWrappedUnit().getBaseCost();
    }

    @Override
    public final Integer getCombat() {
        return getWrappedUnit().getCombat();
    }

    @Override
    public final Collection<Equipment> getEquipment() {
        return getWrappedUnit().getEquipment();
    }

    @Override
    public final Collection<Mutation> getMutations() {
        return Collections.unmodifiableCollection(getMutationsModifiable());
    }

    @Override
    public final Integer getPrecision() {
        return getWrappedUnit().getPrecision();
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return getWrappedUnit().getSpecialRules();
    }

    @Override
    public final Integer getStrength() {
        return getWrappedUnit().getStrength();
    }

    @Override
    public final Integer getTech() {
        return getWrappedUnit().getTech();
    }

    @Override
    public final Integer getToughness() {
        return getWrappedUnit().getToughness();
    }

    @Override
    public final String getUnitName() {
        return getWrappedUnit().getUnitName();
    }

    @Override
    public final ValueHandler getValoration() {
        return getWrappedUnit().getValoration();
    }

    @Override
    public final Collection<Weapon> getWeapons() {
        return getWrappedUnit().getWeapons();
    }

    @Override
    public final void removeEquipment(final Equipment equipment) {
        getWrappedUnit().removeEquipment(equipment);
    }

    @Override
    public final void
            removeValorationListener(final ValorationListener listener) {
        getWrappedUnit().removeValorationListener(listener);
    }

    @Override
    public final void removeWeapon(final Weapon weapon) {
        getWrappedUnit().removeWeapon(weapon);
    }

    @Override
    public final void setArmor(final Armor armor) {
        getWrappedUnit().setArmor(armor);
    }

    private final Collection<Mutation> getMutationsModifiable() {
        return mutations;
    }

    private final Unit getWrappedUnit() {
        return unit;
    }

}
