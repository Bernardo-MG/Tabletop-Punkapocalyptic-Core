package com.wandrell.tabletop.punkapocalyptic.model.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.JPASpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.JPAAttributesHolder;

@Entity(name = "UnitTemplate")
@Table(name = "unit_templates")
@Access(AccessType.FIELD)
public final class JPAUnitTemplate implements UnitTemplate, PersistenceEntity {

    @Embedded
    private final JPAAttributesHolder        attributes = new JPAAttributesHolder();
    @Column(name = "cost")
    private Integer                          cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                          id         = -1;
    @Column(name = "name")
    private String                           name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_template_rules", joinColumns = { @JoinColumn(
            name = "template_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id",
                    referencedColumnName = "id") })
    private final Collection<JPASpecialRule> rules      = new LinkedHashSet<JPASpecialRule>();

    public JPAUnitTemplate() {
        super();
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

        JPAUnitTemplate other;

        other = (JPAUnitTemplate) obj;
        return Objects.equals(name, other.name);
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
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getNameToken() {
        return name;
    }

    @Override
    public final Collection<JPASpecialRule> getSpecialRules() {
        return Collections.unmodifiableCollection(getSpecialRulesModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).toString();
    }

    private final Collection<JPASpecialRule> getSpecialRulesModifiable() {
        return rules;
    }

}
