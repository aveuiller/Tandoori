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

import entities.PaprikaClass;

/**
 * Created by Geoffrey Hecht on 20/05/14.
 */
public class CouplingBetweenObjects extends UnaryMetric<Integer> {

    private CouplingBetweenObjects(PaprikaClass paprikaClass) {
        this.value = paprikaClass.getCouplingValue();
        this.entity = paprikaClass;
        this.name = "coupling_between_object_classes";
    }

    public static CouplingBetweenObjects createCouplingBetweenObjects(PaprikaClass paprikaClass) {
        CouplingBetweenObjects couplingBetweenObjects = new CouplingBetweenObjects(paprikaClass);
        couplingBetweenObjects.updateEntity();
        return couplingBetweenObjects;
    }
}
