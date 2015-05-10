package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.inventory.weapon.ranged;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.JPAStrengthRangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;

public final class TestUnitBasedStrengthRangedWeapon {

    private JPAStrengthRangedWeapon weapon;

    public TestUnitBasedStrengthRangedWeapon() {
        super();
    }

    @BeforeTest
    public final void initializeWeapon() {
        final RangedValue strength;
        final Unit unit;
        final AttributesHolder attributes;

        strength = Mockito.mock(RangedValue.class);

        unit = Mockito.mock(Unit.class);

        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(strength.getShortValue()).thenReturn(1);
        Mockito.when(strength.getMediumValue()).thenReturn(2);
        Mockito.when(strength.getLongValue()).thenReturn(3);

        Mockito.when(attributes.getStrength()).thenReturn(4);

        Mockito.when(unit.getAttributes()).thenReturn(attributes);

        weapon = new JPAStrengthRangedWeapon();

        weapon.setUnit(unit);
        weapon.setShortStrength(1);
        weapon.setMediumStrength(2);
        weapon.setLongStrength(3);
    }

    @Test
    public final void testStrength_Long() {
        Assert.assertEquals(weapon.getLongStrength(), (Integer) 7);
    }

    @Test
    public final void testStrength_Medium() {
        Assert.assertEquals(weapon.getMediumStrength(), (Integer) 6);
    }

    @Test
    public final void testStrength_Short() {
        Assert.assertEquals(weapon.getShortStrength(), (Integer) 5);
    }

}
