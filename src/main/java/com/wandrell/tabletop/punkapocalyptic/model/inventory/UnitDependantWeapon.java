package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public interface UnitDependantWeapon extends Weapon {

    public void setUnit(final Unit unit);

}
