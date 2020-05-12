import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LocationTest {




    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM locations *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void location_instantiatesCorrectly() {
        Location location = new Location("Zone A");
        assertEquals(true, location instanceof  Location);
    }

    @Test
    public void location_getsName_true() {
        Location location = new Location("Zone A");
        assertEquals("Zone A", location.getName());
    }

    @Test
    public void all_returnsAllInstancesOfLocationss_true() {
        Location location = new Location("Zone A");
        location.save();
        Location location1 = new Location("Zone B");
        location1.save();
        assertEquals(Location.all().get(0), location);
        assertEquals(Location.all().get(1), location1);
    }

    @Test
    public void save_assignsIdToObject() {
        Location location = new Location("Zone A");
        location.save();
        Location savedLocation= Location.all().get(0);
        assertEquals(location.getId(), savedLocation.getId());
    }

    @Test
    public void finds_location_true() {
        Location location = new Location("Zone A");
        location.save();
        assertEquals(location, Location.find(location.getId()));
    }

    @Test
    public void false_when_not_instance() {
        Location location = new Location("Zone A");
        Animal animal = new Animal("Lion");
        assertNotEquals(location,animal);
    }
}