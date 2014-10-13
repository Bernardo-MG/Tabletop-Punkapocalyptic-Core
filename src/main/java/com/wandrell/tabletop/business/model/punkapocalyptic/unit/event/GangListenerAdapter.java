package com.wandrell.tabletop.business.model.punkapocalyptic.unit.event;

import java.util.EventObject;

public class GangListenerAdapter implements GangListener {

    @Override
    public void statusChanged(final EventObject event) {}

    @Override
    public void unitAdded(final UnitEvent event) {}

    @Override
    public void unitRemoved(final UnitEvent event) {}

}
