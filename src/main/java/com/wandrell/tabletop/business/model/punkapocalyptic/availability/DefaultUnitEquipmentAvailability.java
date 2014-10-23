package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Equipment;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.WeaponEnhancement;

public final class DefaultUnitEquipmentAvailability implements
        UnitEquipmentAvailability {

    private final Collection<Equipment>         equipment          = new LinkedHashSet<>();
    private final Collection<WeaponEnhancement> weaponEnhancements = new LinkedHashSet<>();

    public DefaultUnitEquipmentAvailability(
            final Collection<WeaponEnhancement> weaponEnhancements,
            final Collection<Equipment> equipment) {
        super();

        for (final WeaponEnhancement enhancement : weaponEnhancements) {
            checkNotNull(enhancement,
                    "Received a null pointer as weapon enhancement");

            this.weaponEnhancements.add(enhancement);
        }

        for (final Equipment equip : equipment) {
            checkNotNull(equip,
                    "Received a null pointer as equipment piece option");

            this.equipment.add(equip);
        }
    }

    @Override
    public final Collection<Equipment> getEquipmentOptions() {
        return Collections
                .unmodifiableCollection(getEquipmentOptionsModifiable());
    }

    @Override
    public final Collection<WeaponEnhancement> getWeaponEnhancementOptions() {
        return Collections
                .unmodifiableCollection(getWeaponEnhancementsModifiable());
    }

    private final Collection<Equipment> getEquipmentOptionsModifiable() {
        return equipment;
    }

    private final Collection<WeaponEnhancement>
            getWeaponEnhancementsModifiable() {
        return weaponEnhancements;
    }

}
