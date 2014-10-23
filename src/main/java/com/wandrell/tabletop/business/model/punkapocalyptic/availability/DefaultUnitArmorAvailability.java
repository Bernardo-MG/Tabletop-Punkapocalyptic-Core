package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Armor;

public final class DefaultUnitArmorAvailability implements
        UnitArmorAvailability {

    private final Collection<Armor> armorOptions = new LinkedHashSet<>();
    private final Armor             initialArmor;

    public DefaultUnitArmorAvailability(final Collection<Armor> armorOptions,
            final Armor initialArmor) {
        super();

        checkNotNull(armorOptions, "Received a null pointer as armor options");
        checkNotNull(initialArmor, "Received a null pointer as initial armor");

        this.initialArmor = initialArmor;

        for (final Armor armor : armorOptions) {
            checkNotNull(armor, "Received a null pointer as armor");

            this.armorOptions.add(armor);
        }
    }

    public DefaultUnitArmorAvailability(
            final DefaultUnitArmorAvailability availability) {
        super();

        checkNotNull(availability,
                "Received a null pointer as the availability");

        this.initialArmor = availability.initialArmor;
        armorOptions.addAll(availability.armorOptions);
    }

    @Override
    public final Collection<Armor> getArmorOptions() {
        return Collections.unmodifiableCollection(getArmorOptionsModifiable());
    }

    @Override
    public final Armor getInitialArmor() {
        return initialArmor;
    }

    private final Collection<Armor> getArmorOptionsModifiable() {
        return armorOptions;
    }

}
