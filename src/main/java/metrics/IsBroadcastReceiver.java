/*
 * Paprika - Detection of code smells in Android application
 *     Copyright (C)  2016  Geoffrey Hecht - INRIA - UQAM - University of Lille
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package metrics;

/**
 * Created by Geoffrey Hecht on 06/08/14.
 */

import entities.PaprikaClass;

public class IsBroadcastReceiver extends UnaryMetric<Boolean> {

    private IsBroadcastReceiver(PaprikaClass entity, boolean value) {
        this.value = value;
        this.entity = entity;
        this.name = "is_broadcast_receiver";
    }

    public static IsBroadcastReceiver createIsBroadcastReceiver(PaprikaClass entity, boolean value) {
        IsBroadcastReceiver isBroadcastReceiver= new IsBroadcastReceiver(entity, value);
        isBroadcastReceiver.updateEntity();
        return isBroadcastReceiver;
    }
}
