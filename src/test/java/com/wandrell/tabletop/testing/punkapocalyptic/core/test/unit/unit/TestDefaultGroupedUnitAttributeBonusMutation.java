package com.wandrell.tabletop.testing.punkapocalyptic.core.test.unit.unit;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultGroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.AttributeBonusMutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;

public final class TestDefaultGroupedUnitAttributeBonusMutation {

    public TestDefaultGroupedUnitAttributeBonusMutation() {
        super();
    }

    @Test
    public final void testActions() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(1);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(2);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testAgility() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(1);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(2);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testCombat() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(1);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(2);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testPrecision() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(1);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(2);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testStrength() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(1);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(2);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testTech() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(1);
        Mockito.when(attributes.getToughness()).thenReturn(0);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(2);
        Mockito.when(attributesBonus.getToughness()).thenReturn(0);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 0);
    }

    @Test
    public final void testToughness() {
        final MutantUnit unit;
        final UnitTemplate template;
        final AttributesHolder attributes;
        final AttributesHolder attributesBonus;
        final AttributeBonusMutation mutation;

        template = Mockito.mock(UnitTemplate.class);
        attributes = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributes.getActions()).thenReturn(0);
        Mockito.when(attributes.getAgility()).thenReturn(0);
        Mockito.when(attributes.getCombat()).thenReturn(0);
        Mockito.when(attributes.getPrecision()).thenReturn(0);
        Mockito.when(attributes.getStrength()).thenReturn(0);
        Mockito.when(attributes.getTech()).thenReturn(0);
        Mockito.when(attributes.getToughness()).thenReturn(1);

        Mockito.when(template.getAttributes()).thenReturn(attributes);

        mutation = Mockito.mock(AttributeBonusMutation.class);
        attributesBonus = Mockito.mock(AttributesHolder.class);

        Mockito.when(attributesBonus.getActions()).thenReturn(0);
        Mockito.when(attributesBonus.getAgility()).thenReturn(0);
        Mockito.when(attributesBonus.getCombat()).thenReturn(0);
        Mockito.when(attributesBonus.getPrecision()).thenReturn(0);
        Mockito.when(attributesBonus.getStrength()).thenReturn(0);
        Mockito.when(attributesBonus.getTech()).thenReturn(0);
        Mockito.when(attributesBonus.getToughness()).thenReturn(2);

        Mockito.when(mutation.getAttributesBonus()).thenReturn(attributesBonus);

        unit = new DefaultGroupedUnit(template);

        unit.addMutation(mutation);

        Assert.assertEquals(unit.getAttributes().getActions(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getAgility(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getCombat(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getPrecision(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getTech(), (Integer) 0);
        Assert.assertEquals(unit.getAttributes().getToughness(), (Integer) 3);
    }

}
