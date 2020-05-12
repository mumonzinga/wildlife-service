import org.junit.Rule;
import org.junit.Test;

import  org.sql2o.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public  class SightingTest {

    @Rule
   public DatabaseRule database = new DatabaseRule();

    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings *;";
            con.createQuery(sql).executeUpdate();
        }
    }


    @Test
    public void sighting_getsAnimalId_1() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        assertEquals(1,sighting.getAnimalId());
    }


    @Test
    public void sighting_instantiatesCorrectly() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        assertEquals(true, sighting instanceof Sighting);

    }
/*
    @Test
    public void sighting_getsLocationId_Zone_A() {
        assertEquals("Zone A", sighting.getLocation());
    }

 */

    @Test
    public void ranger_getsRangerName_1() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        assertEquals("Ty", sighting.getRanger());
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        sighting.save();
        Sighting sighting2 = new Sighting("Lee", "Zone A", 2);
        sighting2.save();
        assertEquals(Sighting.all().get(0), sighting);
        assertEquals(Sighting.all().get(1), sighting2);
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        sighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(sighting.getId(), savedSighting.getId());
    }

    @Test
    public void finds_sighting_true() {
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        sighting.save();
        assertEquals(sighting, Sighting.find(sighting.getId()));
    }

    @Test
    public void notInstanceOfSighting_false() {
        Animal animal = new Animal("Lion");
        Sighting sighting = new Sighting("Ty", "Zone A", 1);
        assertNotEquals(sighting, animal);
    }

}
