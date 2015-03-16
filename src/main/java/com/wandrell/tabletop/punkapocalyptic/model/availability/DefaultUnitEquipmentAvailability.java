package com.wandrell.tabletop.punkapocalyptic.model.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class DefaultUnitEquipmentAvailability implements
        UnitEquipmentAvailability {

    private final Collection<Equipment> equipment;
    private final Unit                  unit;

    public DefaultUnitEquipmentAvailability(final Unit unit,
            final Collection<Equipment> equipment) {
        super();

        checkNotNull(unit, "Received a null pointer as unit");
        checkNotNull(equipment, "Received a null pointer as equipment");

        this.unit = unit;
        this.equipment = equipment;
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

        DefaultUnitEquipmentAvailability other;

        other = (DefaultUnitEquipmentAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Collection<Equipment> getEquipmentOptions() {
        return equipment;
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("unit", unit.getName())
                .add("equipment", equipment).toString();
    }

}
