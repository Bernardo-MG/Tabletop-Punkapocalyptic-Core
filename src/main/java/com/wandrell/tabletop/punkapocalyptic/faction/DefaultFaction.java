package com.wandrell.tabletop.punkapocalyptic.faction;

public final class DefaultFaction implements Faction {
    
    final String name;
    
    public DefaultFaction(final String name){
	super();
	
	this.name = name;
    }
    
    public DefaultFaction(final DefaultFaction faction){
	super();
	
	name = faction.name;
    }

    @Override
    public final String getName() {
	return name;
    }

}
