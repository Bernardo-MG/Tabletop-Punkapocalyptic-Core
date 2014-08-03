package com.wandrell.tabletop.punkapocalyptic.unit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.wandrell.tabletop.punkapocalyptic.faction.Faction;
import com.wandrell.tabletop.valuehandler.ValueHandler;

public final class DefaultBand implements Band {
    
    private final ValueHandler<Integer> bullets;
    private final Collection<Unit> units = new LinkedList<>();
    private final Faction faction;

    public DefaultBand(final DefaultBand band) {
	super();
	
	faction = band.faction;
	bullets = band.bullets.createNewInstance();
	
	for(final Unit unit : band.units){
	    units.add(unit.createNewInstance());
	}
    }

    public DefaultBand(final Faction faction,final ValueHandler<Integer> bullets) {
	super();
	
	this.faction = faction;
	this.bullets = bullets;
    }

    @Override
    public final void addUnit(final Unit unit) {
	_getUnits().add(unit);
    }
    @Override
    public final void clearUnits() {
	_getUnits().clear();
    }

    @Override
    public final DefaultBand createNewInstance() {
	return new DefaultBand(this);
    }

    @Override
    public final ValueHandler<Integer> getBullets() {
	return bullets;
    }

    @Override
    public final Collection<Unit> getUnits() {
	return Collections.unmodifiableCollection(_getUnits());
    }

    protected final Collection<Unit> _getUnits() {
	return units;
    }

    @Override
    public final Faction getFaction() {
	return faction;
    }

}
