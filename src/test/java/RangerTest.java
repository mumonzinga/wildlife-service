import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.assertEquals;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();


    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers *;";
            con.createQuery(sql).executeUpdate();
        }

    }
        @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Ty", "Eldagard", 234);
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test
    public void getfirstName_rangerinstantiatesCorrectlyWithfirstName_true() {
        Ranger testRanger = new Ranger("Ty", "Eldagard", 234);
        assertEquals("Ty", testRanger.getFirstName());
    }

    @Test
    public void getlastName_rangerinstantiatesCorrectlyWithlastName_true() {
        Ranger testRanger = new Ranger("Ty", "Eldagard", 234);
        assertEquals("Eldagard", testRanger.getLastName());
    }
    /*
    @Test
    public void getBadgeNumber_rangerinstantiatesCorrectlyWithBadgeNumber_true() {
        Ranger testRanger = new Ranger("Ty", "Eldagard", 234);
        assertEquals("234", .toString(testRanger.getBadgeNumber()));
    }
*/
    @Test
    public void all_returnsAllInstancesOfRangers_true() {
        Ranger ranger = new Ranger("Ty", "Eldagard", 234);
        ranger.save();
        Ranger ranger1 = new Ranger("Joe", "Michael" ,256);
        ranger1.save();
        assertEquals(true, Ranger.all().get(0).equals(ranger));
        assertEquals(true, Ranger.all().get(1).equals( ranger1));
    }


    @Test
    public void save_assignsIdToObject() {
        Ranger ranger = new Ranger("Ty", "Eldagard", 234);
        ranger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(ranger.getId(), savedRanger.getId());
    }


    }


