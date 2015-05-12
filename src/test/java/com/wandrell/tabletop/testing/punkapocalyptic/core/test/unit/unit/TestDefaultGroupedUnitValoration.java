package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.unit;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultGroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;

public final class TestDefaultGroupedUnitValoration {

    public TestDefaultGroupedUnitValoration() {
        super();
    }

    @Test
    public final void testValoration() throws Exception {
        Assert.assertEquals(getGroupedUnit().getValoration(), (Integer) 30);
    }

    private final DefaultGroupedUnit getGroupedUnit() {
        final DefaultGroupedUnit unit;
        final UnitTemplate template;
        Equipment equip;
        UnitWeapon weapon;

        template = Mockito.mock(UnitTemplate.class);

        unit = new DefaultGroupedUnit(template);

        Mockito.when(template.getBaseCost()).thenReturn(1);

        weapon = Mockito.mock(UnitWeapon.class);
        Mockito.when(weapon.getCost()).thenReturn(2);

        unit.addWeapon(weapon);

        weapon = Mockito.mock(UnitWeapon.class);
        Mockito.when(weapon.getCost()).thenReturn(3);

        unit.addWeapon(weapon);

        equip = Mockito.mock(Equipment.class);
        Mockito.when(equip.getCost()).thenReturn(4);

        unit.addEquipment(equip);

        equip = Mockito.mock(Equipment.class);
        Mockito.when(equip.getCost()).thenReturn(5);

        unit.addEquipment(equip);

        unit.getGroupSize().setValue(2);

        return unit;
    }

}
