package com.wandrell.tabletop.punkapocalyptic.inventory;

import java.util.Collection;

import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

public final class DefaultRangedWeapon extends AbstractWeapon implements
	RangedWeapon {

    public final class DefaultRangedDistance implements RangedDistance {

	private final Integer distanceShort;
	private final Integer distanceMedium;
	private final Integer distanceLong;

	public DefaultRangedDistance(final Integer distanceShort,
		final Integer distanceMedium, final Integer distanceLong) {
	    super();

	    this.distanceShort = distanceShort;
	    this.distanceMedium = distanceMedium;
	    this.distanceLong = distanceLong;
	}

	@Override
	public final Integer getLongDistance() {
	    return distanceLong;
	}

	@Override
	public final Integer getMediumDistance() {
	    return distanceMedium;
	}

	@Override
	public final Integer getShortDistance() {
	    return distanceShort;
	}

    }

    private final RangedDistance distancesCM;
    private final RangedDistance distancesInches;

    public DefaultRangedWeapon(final String name, final Integer strength,
	    final Integer penetration, final Collection<SpecialRule> rules,
	    final RangedDistance distancesCM, RangedDistance distancesInches) {
	super(name, strength, penetration, rules);

	this.distancesCM = distancesCM;
	this.distancesInches = distancesInches;
    }

    public DefaultRangedWeapon(final DefaultRangedWeapon weapon) {
	super(weapon);

	distancesCM = weapon.distancesCM;
	distancesInches = weapon.distancesInches;
    }

    @Override
    public final RangedDistance getDistancesImperialUnits() {
	return distancesInches;
    }

    @Override
    public final RangedDistance getDistancesMetricSystem() {
	return distancesCM;
    }

}
