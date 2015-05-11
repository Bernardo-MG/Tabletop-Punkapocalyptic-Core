package com.wandrell.tabletop.punkapocalyptic.model.availability.option;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.JPAArmor;
import com.wandrell.util.persistence.PersistenceEntity;

@Entity(name = "ArmorOption")
@Table(name = "armor_options")
public final class JPAArmorOption implements ArmorOption, PersistenceEntity {

    @OneToOne
    @JoinColumn(name = "armor", referencedColumnName = "id")
    private JPAArmor armor;
    private Integer  cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id = -1;

    public JPAArmorOption() {
        super();
    }

    public JPAArmorOption(final JPAArmor armor, final Integer cost) {
        super();

        checkNotNull(armor, "Received a null pointer as armor");
        checkNotNull(cost, "Received a null pointer as cost");

        this.armor = armor;
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

        JPAArmorOption other;

        other = (JPAArmorOption) obj;
        return Objects.equals(armor, other.armor)
                && Objects.equals(cost, other.cost);
    }

    @Override
    public final Armor getArmor() {
        return armor;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(armor, cost);
    }

    public final void setArmor(final JPAArmor armor) {
        checkNotNull(armor, "Received a null pointer as armor");

        this.armor = armor;
    }

    public final void setCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        this.cost = cost;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("armor", armor)
                .add("cost", cost).toString();
    }

}
