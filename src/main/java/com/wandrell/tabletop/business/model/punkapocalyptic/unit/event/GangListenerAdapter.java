package com.wandrell.tabletop.business.model.punkapocalyptic.unit.event;

import java.util.EventObject;

public class GangListenerAdapter implements GangListener {

    @Override
    public void statusChanged(EventObject e) {}

    @Override
    public void unitAdded(UnitEvent e) {}

    @Override
    public void unitRemoved(UnitEvent e) {}

}
