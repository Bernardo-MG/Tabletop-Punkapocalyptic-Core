package com.wandrell.tabletop.punkapocalyptic.model.unit.stats;

import javax.swing.event.EventListenerList;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.AttributesListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitActionsValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitAgilityValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitCombatValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitPrecisionValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitStrengthValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitTechValueBox;
import com.wandrell.tabletop.punkapocalyptic.valuebox.UnitToughnessValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class UnitBonusAttributesHolder implements AttributesHolder {

    private final ValueBox          actions;
    private final ValueBox          agility;
    private final ValueBox          combat;
    private final EventListenerList listeners = new EventListenerList();
    private final ValueBox          precision;
    private final ValueBox          strength;
    private final ValueBox          tech;
    private final ValueBox          toughness;

    public UnitBonusAttributesHolder(final Unit unit) {
        super();

        actions = new UnitActionsValueBox(unit);
        agility = new UnitAgilityValueBox(unit);
        combat = new UnitCombatValueBox(unit);
        precision = new UnitPrecisionValueBox(unit);
        strength = new UnitStrengthValueBox(unit);
        tech = new UnitTechValueBox(unit);
        toughness = new UnitToughnessValueBox(unit);

        setAttributesListeners();
    }

    @Override
    public final void addAttributesListener(final AttributesListener listener) {
        getListeners().add(AttributesListener.class, listener);
    }

    @Override
    public final Integer getActions() {
        return getActionsValueBox().getValue();
    }

    @Override
    public final Integer getAgility() {
        return getAgilityValueBox().getValue();
    }

    @Override
    public final Integer getCombat() {
        return getCombatValueBox().getValue();
    }

    @Override
    public final Integer getPrecision() {
        return getPrecisionValueBox().getValue();
    }

    @Override
    public final Integer getStrength() {
        return getStrengthValueBox().getValue();
    }

    @Override
    public final Integer getTech() {
        return getTechValueBox().getValue();
    }

    @Override
    public final Integer getToughness() {
        return getToughnessValueBox().getValue();
    }

    @Override
    public final void
            removeAttributesListener(final AttributesListener listener) {
        getListeners().remove(AttributesListener.class, listener);
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

    private final ValueBox getActionsValueBox() {
        return actions;
    }

    private final ValueBox getAgilityValueBox() {
        return agility;
    }

    private final ValueBox getCombatValueBox() {
        return combat;
    }

    private final EventListenerList getListeners() {
        return listeners;
    }

    private final ValueBox getPrecisionValueBox() {
        return precision;
    }

    private final ValueBox getStrengthValueBox() {
        return strength;
    }

    private final ValueBox getTechValueBox() {
        return tech;
    }

    private final ValueBox getToughnessValueBox() {
        return toughness;
    }

    private final void setAttributesListeners() {
        getActionsValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireActionsChangedEvent(event);
            }

        });
        getAgilityValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireAgilityChangedEvent(event);
            }

        });
        getCombatValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireCombatChangedEvent(event);
            }

        });
        getPrecisionValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        firePrecisionChangedEvent(event);
                    }

                });
        getStrengthValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireStrengthChangedEvent(event);
            }

        });
        getTechValueBox().addValueChangeListener(new ValueChangeListener() {

            @Override
            public final void valueChanged(final ValueChangeEvent event) {
                fireTechChangedEvent(event);
            }

        });
        getToughnessValueBox().addValueChangeListener(
                new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        fireToughnessChangedEvent(event);
                    }

                });
    }

}
