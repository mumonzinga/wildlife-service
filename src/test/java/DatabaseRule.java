

import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_service_test", "mumo", "kyalelove");
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteAnimalQuery = "DELETE FROM animals *;";
            con.createQuery(deleteAnimalQuery).executeUpdate();

            con.createQuery(deleteAnimalQuery).executeUpdate();
        }
    }





}