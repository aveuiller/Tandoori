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

package neo4j;

import org.neo4j.cypher.CypherException;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.io.IOException;

/**
 * Created by Geoffrey Hecht on 18/08/15.
 */
public class OverdrawQuery extends Query {

    private OverdrawQuery(QueryEngine queryEngine) {
        super(queryEngine);
    }

    public static OverdrawQuery createOverdrawQuery(QueryEngine queryEngine) {
        return new OverdrawQuery(queryEngine);
    }

    @Override
    public void execute(boolean details) throws CypherException, IOException {
        try (Transaction ignored = graphDatabaseService.beginTx()) {
            String query = "MATCH (a:App)-[:APP_OWNS_CLASS]->(:Class{is_view:true})-[:CLASS_OWNS_METHOD]->(n:Method{name:\"onDraw\"})-[:METHOD_OWNS_ARGUMENT]->(:Argument{position:0,name:\"android.graphics.Canvas\"}) \n" +
                    "WHERE NOT (n)-[:CALLS]->(:ExternalMethod{full_name:\"clipRect#android.graphics.Canvas\"}) AND NOT (n)-[:CALLS]->(:ExternalMethod{full_name:\"quickReject#android.graphics.Canvas\"})\n" +
                    " SET a.has_UIO=true RETURN a.commit_number as commit_number, n.app_key as key";
            if(details){
                query += ", n.full_name as instance, a.commit_status as commit_status";
            }else{
                query += ",count(n) as UIO";
            }
            Result result = graphDatabaseService.execute(query);
            queryEngine.resultToCSV(result, "_UIO.csv");
            ignored.success();
        }
    }
}
