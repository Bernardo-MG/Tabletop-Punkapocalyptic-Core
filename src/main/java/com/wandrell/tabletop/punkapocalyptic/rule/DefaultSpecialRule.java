package com.wandrell.tabletop.punkapocalyptic.rule;

public final class DefaultSpecialRule implements SpecialRule {

    private final String name;

    public DefaultSpecialRule(final String name) {
        super();

        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return getName();
    }

}
