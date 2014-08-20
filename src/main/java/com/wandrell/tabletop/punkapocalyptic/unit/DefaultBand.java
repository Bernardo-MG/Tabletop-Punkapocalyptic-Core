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
package com.wandrell.tabletop.punkapocalyptic.unit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.wandrell.tabletop.punkapocalyptic.faction.Faction;
import com.wandrell.tabletop.valuehandler.ValueHandler;

public final class DefaultBand implements Band {

    private final ValueHandler     bullets;
    private final Faction          faction;
    private final Collection<Unit> units = new LinkedList<>();

    public DefaultBand(final DefaultBand band) {
        super();

        faction = band.faction;
        bullets = band.bullets.createNewInstance();

        for (final Unit unit : band.units) {
            units.add(unit.createNewInstance());
        }
    }

    public DefaultBand(final Faction faction, final ValueHandler bullets) {
        super();

        this.faction = faction;
        this.bullets = bullets;
    }

    @Override
    public final void addUnit(final Unit unit) {
        _getUnits().add(unit);
    }

    @Override
    public final void clearUnits() {
        _getUnits().clear();
    }

    @Override
    public final DefaultBand createNewInstance() {
        return new DefaultBand(this);
    }

    @Override
    public final ValueHandler getBullets() {
        return bullets;
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Collection<Unit> getUnits() {
        return Collections.unmodifiableCollection(_getUnits());
    }

    protected final Collection<Unit> _getUnits() {
        return units;
    }

}
