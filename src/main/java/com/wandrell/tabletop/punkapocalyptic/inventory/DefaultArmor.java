package com.wandrell.tabletop.punkapocalyptic.inventory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

public final class DefaultArmor implements Armor {

    private final Integer                 armor;
    private final String                  name;
    private final Collection<SpecialRule> rules = new LinkedList<>();

    public DefaultArmor(final DefaultArmor armor) {
        super();

        this.armor = armor.armor;
        this.name = armor.name;
    }

    public DefaultArmor(final String name, final Integer armor) {
        super();

        this.name = name;
        this.armor = armor;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultArmor other = (DefaultArmor) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public final Integer getArmor() {
        return armor;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Collection<SpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(_getSpecialRules());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public final String toString() {
        return getName();
    }

    protected final Collection<SpecialRule> _getSpecialRules() {
        return rules;
    }

}
