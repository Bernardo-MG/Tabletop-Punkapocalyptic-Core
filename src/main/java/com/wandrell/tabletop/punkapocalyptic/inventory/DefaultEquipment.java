package com.wandrell.tabletop.punkapocalyptic.inventory;

public final class DefaultEquipment implements Equipment {

    public final String name;

    public DefaultEquipment(final DefaultEquipment equipment) {
        super();

        this.name = equipment.name;
    }

    public DefaultEquipment(final String name) {
        super();

        this.name = name;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultEquipment other = (DefaultEquipment) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public final String getName() {
        return name;
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

}
