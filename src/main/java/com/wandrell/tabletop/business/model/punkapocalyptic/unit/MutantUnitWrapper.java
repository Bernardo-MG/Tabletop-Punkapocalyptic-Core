package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;

import javax.swing.event.EventListenerList;

import com.wandrell.tabletop.business.model.punkapocalyptic.event.ValorationListener;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.DefaultUnit.ValorationBuilder;
import com.wandrell.tabletop.business.model.valuebox.ValueBox;

public final class MutantUnitWrapper implements MutantUnit {

    private final EventListenerList    listeners = new EventListenerList();
    private final Collection<Mutation> mutations = new LinkedHashSet<>();
    private final Unit                 unit;
    private final ValueBox             valoration;
    private final ValorationBuilder    valorationBuilder;

    public MutantUnitWrapper(final MutantUnitWrapper unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit.unit.createNewInstance();

        this.valorationBuilder = unit.valorationBuilder;

        this.unit.addValorationListener(new ValorationListener() {

            @Override
            public final void valorationChanged(final EventObject e) {
                fireValorationChangedEvent(new EventObject(this));
            }

        });

        // TODO: Do this some other way
        valoration = valorationBuilder.getValoration(this);
    }

    public MutantUnitWrapper(final Unit unit,
            final ValorationBuilder valorationBuilder) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit;

        this.unit.addValorationListener(new ValorationListener() {

            @Override
            public final void valorationChanged(final EventObject e) {
                fireValorationChangedEvent(new EventObject(this));
            }

        });

        this.valorationBuilder = valorationBuilder;

        // TODO: Do this some other way
        valoration = valorationBuilder.getValoration(this);
    }

    @Override
    public final void addEquipment(final Equipment equipment) {
        getWrappedUnit().addEquipment(equipment);
    }

    @Override
    public final void addMutation(final Mutation mutation) {
        getMutationsModifiable().add(mutation);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void addValorationListener(final ValorationListener listener) {
        getWrappedUnit().addValorationListener(listener);

        getListeners().add(ValorationListener.class, listener);
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
    public final ValueBox getValoration() {
        return valoration;
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
    public final void removeMutation(final Mutation mutation) {
        getMutationsModifiable().remove(mutation);

        fireValorationChangedEvent(new EventObject(this));
    }

    @Override
    public final void
            removeValorationListener(final ValorationListener listener) {
        getWrappedUnit().removeValorationListener(listener);

        getListeners().remove(ValorationListener.class, listener);
    }

    @Override
    public final void removeWeapon(final Weapon weapon) {
        getWrappedUnit().removeWeapon(weapon);
    }

    @Override
    public final void setArmor(final Armor armor) {
        getWrappedUnit().setArmor(armor);
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

    private final Collection<Mutation> getMutationsModifiable() {
        return mutations;
    }

    private final Unit getWrappedUnit() {
        return unit;
    }

}
