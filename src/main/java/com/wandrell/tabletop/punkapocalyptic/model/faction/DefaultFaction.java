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
package com.wandrell.tabletop.punkapocalyptic.model.faction;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public final class DefaultFaction implements Faction {

    private final String factionName;

    public DefaultFaction(final DefaultFaction faction) {
        super();

        checkNotNull(faction, "Received a null pointer as faction");

        factionName = faction.factionName;
    }

    public DefaultFaction(final String name) {
        super();

        checkNotNull(name, "Received a null pointer as name");

        this.factionName = name;
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

        final DefaultFaction other;

        other = (DefaultFaction) obj;
        return Objects.equals(factionName, other.factionName);
    }

    @Override
    public final String getNameToken() {
        return factionName;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(factionName);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", factionName)
                .toString();
    }

}
