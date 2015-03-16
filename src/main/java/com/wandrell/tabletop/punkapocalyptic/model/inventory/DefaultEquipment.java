/**
 * Copyright 2014 the original author or authors
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
package com.wandrell.tabletop.punkapocalyptic.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public final class DefaultEquipment implements Equipment {

    private final Integer equipCost;
    private final String  equipName;

    public DefaultEquipment(final DefaultEquipment equipment) {
        super();

        checkNotNull(equipment, "Received a null pointer as equipment");

        equipName = equipment.equipName;
        equipCost = equipment.equipCost;
    }

    public DefaultEquipment(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

        this.equipName = name;
        this.equipCost = cost;
    }

    @Override
    public final Equipment createNewInstance() {
        return new DefaultEquipment(this);
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

        final DefaultEquipment other;

        other = (DefaultEquipment) obj;
        return Objects.equals(equipName, other.equipName);
    }

    @Override
    public final Integer getCost() {
        return equipCost;
    }

    @Override
    public final String getName() {
        return equipName;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(equipName);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", equipName)
                .toString();
    }

}
