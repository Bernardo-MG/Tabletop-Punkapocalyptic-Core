package com.wandrell.tabletop.business.model.punkapocalyptic.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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

        DefaultUnitArmorAvailability other;

        other = (DefaultUnitArmorAvailability) obj;
        return Objects.equal(armorOptions, other.armorOptions)
                && Objects.equal(initialArmor, other.initialArmor);
    }

    @Override
    public final Collection<Armor> getArmorOptions() {
        return Collections.unmodifiableCollection(getArmorOptionsModifiable());
    }

    @Override
    public final Armor getInitialArmor() {
        return initialArmor;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(armorOptions, initialArmor);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("initial", initialArmor)
                .add("armors", armorOptions).toString();
    }

    private final Collection<Armor> getArmorOptionsModifiable() {
        return armorOptions;
    }

}
