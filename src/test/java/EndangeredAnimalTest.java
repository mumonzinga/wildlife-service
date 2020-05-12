import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.assertEquals;

public class EndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals *;";
            con.createQuery(sql).executeUpdate();
        }

    }

    @Test
    public void animal_instantiatesCorrectly() {
        Animal testAnimal = new EndangeredAnimal("Elephant","ill", "adult");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getType_Endangered() {
        Animal EndangeredAnimal = new EndangeredAnimal("Elephant","ill", "adult");
        assertEquals("Endangered", ((EndangeredAnimal) EndangeredAnimal).getTYPE());

    }

    @Test
    public void animal_getName_Elephant() {
        Animal animal = new EndangeredAnimal("Elephant","ill", "adult");
        assertEquals("Elephant", animal.getName());
    }

    @Test
    public void animal_gethealth_Ill() {
        Animal animal = new EndangeredAnimal("Elephant","ill", "adult");
        assertEquals("ill", ((EndangeredAnimal) animal).getHealth());
    }

    @Test
    public void animal_getsAge_Old() {
        Animal animal = new EndangeredAnimal("Elephant","ill", "adult");
        assertEquals("adult", ((EndangeredAnimal) animal).getAge());
    }

    @Test
    public void save_assignsIdToObject() {
        Animal animal = new EndangeredAnimal("Elephant","ill", "adult");
        animal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEndangeredAnimals().get(0);
        assertEquals(animal.getId() , animal.getId());
    }

    @Test
    public void findsEndangeredAnimal_true()
    {
        Animal animal = new EndangeredAnimal("Elephant","ill", "adult");
        animal.save();
        assertEquals(animal, EndangeredAnimal.find(animal.getId()));
    }



}