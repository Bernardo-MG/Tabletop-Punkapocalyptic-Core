package com.wandrell.tabletop.punkapocalyptic.inventory;

import java.util.Collection;
import java.util.Collections;
import com.wandrell.tabletop.punkapocalyptic.rule.SpecialRule;

class AbstractWeapon implements Weapon {

    private final Integer penetration;
    private final Integer strength;
    private final String name;
    private final Collection<SpecialRule> rules;

    public AbstractWeapon(final String name, final Integer strength,
	    final Integer penetration, final Collection<SpecialRule> rules) {
	super();

	this.name = name;
	this.strength = strength;
	this.penetration = penetration;
	this.rules = rules;
    }

    public AbstractWeapon(final AbstractWeapon weapon) {
	super();

	name = weapon.name;
	strength = weapon.strength;
	penetration = weapon.penetration;
	rules = weapon.rules;
    }

    @Override
    public String toString() {
	return getName();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractWeapon other = (AbstractWeapon) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    protected final Collection<SpecialRule> _getSpecialRules() {
	return rules;
    }
    @Override
    public final Collection<SpecialRule> getSpecialRules() {
	return Collections.unmodifiableCollection(_getSpecialRules());
    }

    @Override
    public final Integer getStrength() {
	return strength;
    }

    @Override
    public final String getName() {
	return name;
    }

    @Override
    public final Integer getPenetration() {
	return penetration;
    }

}
