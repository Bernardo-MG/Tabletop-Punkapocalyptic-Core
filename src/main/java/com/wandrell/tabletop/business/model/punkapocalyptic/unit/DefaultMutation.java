package com.wandrell.tabletop.business.model.punkapocalyptic.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class DefaultMutation implements Mutation {

    private final Integer cost;
    private final String  name;

    public DefaultMutation(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

        this.name = name;
        this.cost = cost;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DefaultMutation other;

        other = (DefaultMutation) obj;
        return Objects.equal(name, other.name);
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
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
