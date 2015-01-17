package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.inventory.weapon.ranged;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.MeleeWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.UnitBasedStrengthRangedWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.unit.Unit;
import com.wandrell.tabletop.business.model.punkapocalyptic.util.RangedValue;

public final class TestUnitBasedStrengthRangedWeapon {

    private UnitBasedStrengthRangedWeapon weapon;

    public TestUnitBasedStrengthRangedWeapon() {
        super();
    }

    @BeforeTest
    public final void initializeWeapon() {
        final RangedValue penetration;
        final RangedValue strength;
        final RangedValue distanceCM;
        final RangedValue distanceInches;
        final MeleeWeapon weaponMelee;
        final Unit unit;

        penetration = Mockito.mock(RangedValue.class);
        strength = Mockito.mock(RangedValue.class);
        distanceCM = Mockito.mock(RangedValue.class);
        distanceInches = Mockito.mock(RangedValue.class);
        weaponMelee = Mockito.mock(MeleeWeapon.class);

        unit = Mockito.mock(Unit.class);

        Mockito.when(strength.getShortValue()).thenReturn(1);
        Mockito.when(strength.getMediumValue()).thenReturn(2);
        Mockito.when(strength.getLongValue()).thenReturn(3);

        Mockito.when(unit.getStrength()).thenReturn(4);

        weapon = new UnitBasedStrengthRangedWeapon("weapon", 0, penetration,
                strength, distanceCM, distanceInches, weaponMelee);

        weapon.setUnit(unit);
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
