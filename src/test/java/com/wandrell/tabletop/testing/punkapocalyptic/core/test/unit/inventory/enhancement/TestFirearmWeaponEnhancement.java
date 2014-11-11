package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.inventory.enhancement;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.FirearmWeaponEnhancement;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.RangedWeapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.Weapon;
import com.wandrell.tabletop.business.model.punkapocalyptic.inventory.WeaponEnhancement;

public final class TestFirearmWeaponEnhancement {

    private WeaponEnhancement enhancement;

    public TestFirearmWeaponEnhancement() {
        super();
    }

    @BeforeTest
    public final void initializeEnhancement() {
        enhancement = new FirearmWeaponEnhancement("firearm", 0);
    }

    @Test
    public final void testAccepts() {
        final RangedWeapon weapon;

        weapon = Mockito.mock(RangedWeapon.class);

        Mockito.when(weapon.isFirearm()).thenReturn(true);

        Assert.assertTrue(getWeaponEnhancement().isValid(weapon));
    }

    @Test
    public final void testRejects_NotFirearm() {
        final RangedWeapon weapon;

        weapon = Mockito.mock(RangedWeapon.class);

        Mockito.when(weapon.isFirearm()).thenReturn(false);

        Assert.assertTrue(!getWeaponEnhancement().isValid(weapon));
    }

    @Test
    public final void testRejects_NotRanged() {
        final Weapon weapon;

        weapon = Mockito.mock(Weapon.class);

        Assert.assertTrue(!getWeaponEnhancement().isValid(weapon));
    }

    private final WeaponEnhancement getWeaponEnhancement() {
        return enhancement;
    }

}
