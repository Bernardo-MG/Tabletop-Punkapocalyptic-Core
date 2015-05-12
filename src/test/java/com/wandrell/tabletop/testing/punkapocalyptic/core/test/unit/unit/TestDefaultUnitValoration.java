package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.unit;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;

public final class TestDefaultUnitValoration {

    public TestDefaultUnitValoration() {
        super();
    }

    @Test
    public final void testValoration_BaseUnit() throws Exception {
        Assert.assertEquals(getUnit().getValoration(), (Integer) 15);
    }

    @Test
    public final void testValoration_MutantUnit() throws Exception {
        Assert.assertEquals(getMutantUnit().getValoration(), (Integer) 28);
    }

    private final MutantUnit getMutantUnit() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        Mutation mutation;
        Equipment equip;
        UnitWeapon weapon;

        template = Mockito.mock(UnitTemplate.class);

        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        unit = new DefaultUnit(template);

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

        mutation = Mockito.mock(Mutation.class);
        Mockito.when(mutation.getCost()).thenReturn(6);

        unit.addMutation(mutation);

        mutation = Mockito.mock(Mutation.class);
        Mockito.when(mutation.getCost()).thenReturn(7);

        unit.addMutation(mutation);

        return unit;
    }

    private final Unit getUnit() {
        final Unit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        Equipment equip;
        UnitWeapon weapon;

        template = Mockito.mock(UnitTemplate.class);

        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        unit = new DefaultUnit(template);

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

        return unit;
    }

}
