/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.punkapocalyptic.model.unit.mutation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.AttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.JPAAttributesHolder;
import com.wandrell.util.persistence.PersistenceEntity;

@Entity(name = "Mutation")
@Table(name = "mutations")
public final class JPAMutation implements Mutation, AttributeBonusMutation,
        PersistenceEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "actions", column = @Column(
                    name = "actions_bonus")),
            @AttributeOverride(name = "agility", column = @Column(
                    name = "agility_bonus")),
            @AttributeOverride(name = "combat", column = @Column(
                    name = "combat_bonus")),
            @AttributeOverride(name = "precision", column = @Column(
                    name = "precision_bonus")),
            @AttributeOverride(name = "strength", column = @Column(
                    name = "strength_bonus")),
            @AttributeOverride(name = "tech", column = @Column(
                    name = "tech_bonus")),
            @AttributeOverride(name = "toughness", column = @Column(
                    name = "toughness_bonus")) })
    private final JPAAttributesHolder attributes = new JPAAttributesHolder();
    @Column(name = "cost")
    private Integer                   cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                   id         = -1;
    @Column(name = "name")
    private String                    name;

    public JPAMutation() {
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

        JPAMutation other;

        other = (JPAMutation) obj;
        return Objects.equal(name, other.name);
    }

    @Override
    public final AttributesHolder getAttributesBonus() {
        return attributes;
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
    public final String getNameToken() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
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

    public final void setName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

}
