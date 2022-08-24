package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int car1;
    static int car2;
    @BeforeAll
    public static void setupData(@Autowired CarRepository carRepository){

        Car c1 = new Car("Opel","o2",500,20);
        Car c2 = new Car("Opel","o1",400,20);
        carRepository.save(c1);
        carRepository.save(c2);

        car1 = c1.getId();
        car2 = c2.getId();

    }

    @Test
    void testFindCarByID(){
        Car found = carRepository.findById(1).get();
        assertEquals(car1,found.getId());
        assertEquals("Opel",found.getBrand());

    }

    @Test
    void testFindCarByModel(){
        Car found = carRepository.findCarByModel("o1");
        assertEquals(car2,found.getId());
        assertEquals("o1",found.getModel());

    }

}