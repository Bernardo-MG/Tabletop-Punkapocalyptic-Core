package com.wandrell.tabletop.punkapocalyptic.faction;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.punkapocalyptic.unit.AvailabilityUnit;

public final class DefaultFaction implements Faction {

    private final String                       name;
    private final Collection<AvailabilityUnit> units = new LinkedHashSet<>();

    public DefaultFaction(final DefaultFaction faction) {
        super();

        name = faction.name;

        for (final AvailabilityUnit unit : faction.units) {
            units.add(unit);
        }
    }

    public DefaultFaction(final String name,
            final Collection<AvailabilityUnit> units) {
        super();

        this.name = name;

        for (final AvailabilityUnit unit : units) {
            units.add(unit);
        }
    }

    @Override
    public final Collection<AvailabilityUnit> getAvailableUnits() {
        return Collections
                .unmodifiableCollection(getAvailableUnitsModifiable());
    }

    @Override
    public final String getName() {
        return name;
    }

    protected final Collection<AvailabilityUnit> getAvailableUnitsModifiable() {
        return units;
    }

}
