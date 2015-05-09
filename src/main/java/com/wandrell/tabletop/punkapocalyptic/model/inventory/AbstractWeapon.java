package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;

public abstract class AbstractWeapon implements Weapon {

    private final Integer                 cost;
    private final String                  name;
    private final Collection<SpecialRule> rules = new LinkedList<>();
    private final Boolean                 twoHanded;

    public AbstractWeapon(final AbstractWeapon weapon) {
        super();

        this.name = weapon.name;
        this.cost = weapon.cost;
        this.twoHanded = weapon.twoHanded;
        this.rules.addAll(weapon.rules);
    }

    public AbstractWeapon(final String name, final Integer cost,
            final Boolean twoHanded, final Collection<SpecialRule> rules) {
        super();

        this.name = name;
        this.cost = cost;
        this.twoHanded = twoHanded;
        this.rules.addAll(rules);
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesEditable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final Boolean isTwoHanded() {
        return twoHanded;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).add("rules", rules).toString();
    }

    private final Collection<SpecialRule> getSpecialRulesEditable() {
        return rules;
    }

}
