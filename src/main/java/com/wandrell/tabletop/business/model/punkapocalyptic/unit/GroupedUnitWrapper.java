package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.ruleset.SpecialRule;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.event.UnitListener;
import com.wandrell.tabletop.business.model.valuebox.EditableValueBox;
import com.wandrell.tabletop.business.model.valuebox.ValueBox;
import com.wandrell.tabletop.business.model.valuebox.derived.DerivedValueBox;
import com.wandrell.tabletop.business.model.valuebox.derived.DerivedValueViewPoint;
import com.wandrell.tabletop.business.model.valuebox.derived.punkapocalyptic.GroupedUnitValorationDerivedValueViewPoint;
import com.wandrell.tabletop.business.util.event.ValueChangeEvent;
import com.wandrell.tabletop.business.util.event.ValueChangeListener;

public final class GroupedUnitWrapper implements GroupedUnit, MutantUnit {

    private final EventListenerList listeners = new EventListenerList();
    private final EditableValueBox  size;
    private final Unit              unit;
    private final ValueBox          valoration;

    public GroupedUnitWrapper(final GroupedUnitWrapper unit) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit.unit.createNewInstance();
        size = unit.size.createNewInstance();

        final DerivedValueViewPoint store;

        store = new GroupedUnitValorationDerivedValueViewPoint(
                unit.getBaseCost(), size);
        valoration = new DerivedValueBox(store);

        size.addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireValorationChangedEvent(new EventObject(this));
            }

        });
    }

    public GroupedUnitWrapper(final Unit unit, final EditableValueBox size) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(size, "Received a null pointer as size");

        this.unit = unit;
        this.size = size;

        final DerivedValueViewPoint store;

        store = new GroupedUnitValorationDerivedValueViewPoint(
                unit.getBaseCost(), size);
        valoration = new DerivedValueBox(store);

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
    public final void addWeapon(final Weapon weapon) {
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
    public final GroupedUnitWrapper createNewInstance() {
        return new GroupedUnitWrapper(this);
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
    public final EditableValueBox getGroupSize() {
        return size;
    }

    @Override
    public final Collection<Mutation> getMutations() {
        final Collection<Mutation> mutations;

        if (getWrappedUnit() instanceof MutantUnit) {
            mutations = ((MutantUnit) getWrappedUnit()).getMutations();
        } else {
            mutations = new LinkedList<>();
        }

        return mutations;
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
    public final Integer getValoration() {
        return getValorationValueBox().getValue();
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
    public final void removeWeapon(final Weapon weapon) {
        getWrappedUnit().removeWeapon(weapon);
    }

    @Override
    public final void setArmor(final Armor armor) {
        getWrappedUnit().setArmor(armor);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", getUnitName())
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

    private final ValueBox getValorationValueBox() {
        return valoration;
    }

    private final Unit getWrappedUnit() {
        return unit;
    }

}
