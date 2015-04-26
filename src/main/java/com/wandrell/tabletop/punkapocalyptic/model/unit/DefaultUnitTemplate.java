package com.wandrell.tabletop.punkapocalyptic.model.unit;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;

public final class DefaultUnitTemplate implements UnitTemplate {

    private final AttributesHolder        attributes;
    private final Integer                 cost;
    private final String                  name;
    private final Collection<SpecialRule> rules = new LinkedHashSet<SpecialRule>();

    public DefaultUnitTemplate(final String name,
            final AttributesHolder attributes, final Integer cost,
            final Collection<SpecialRule> rules) {
        super();

        this.name = name;
        this.attributes = attributes;
        this.cost = cost;
        this.rules.addAll(rules);
    }

    @Override
    public final AttributesHolder getAttributes() {
        return attributes;
    }

    @Override
    public final Integer getBaseCost() {
        return cost;
    }

    @Override
    public final String getNameToken() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return rules;
    }

}
