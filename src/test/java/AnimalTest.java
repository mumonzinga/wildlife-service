


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static java.lang.Character.TYPE;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM animals *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Elephant");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalinstantiatesCorrectlyWithName_true() {
        Animal testAnimal = new Animal("Elephant");
        assertEquals("Elephant", testAnimal.getName());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal animal = new Animal("Elephant");
        animal.save();
        Animal animal1 = new Animal("Leopard");
        animal1.save();
        assertEquals(true, Animal.all().get(0).equals(animal));
        assertEquals(true, Animal.all().get(1).equals(animal1));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal animal = new Animal("Elephant");
        animal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(animal.getId(), savedAnimal.getId());
    }

    @Test
    public void getType_Not_Endangered() {
        Animal animal = new Animal("Elephant");
        assertEquals("Not Endangered", Animal.getType());

    }

    @Test
    public void find_returns_animal() {
        Animal animal = new Animal("Elephant");
        animal.save();
        assertEquals(animal, Animal.find(animal.getId()));
    }



}

