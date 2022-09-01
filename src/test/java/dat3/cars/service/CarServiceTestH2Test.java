package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTestH2Test {

    public CarService carService;

    public static CarRepository carRepository;

    @BeforeAll
    public static void setupData(@Autowired CarRepository car_Repository) {
        carRepository = car_Repository;
        List<Car> cars = List.of(
                new Car("BMW", "X12", 1200, 200),
                new Car("BMW", "X13", 1300, 300)
        );
        carRepository.saveAll(cars);
    }

    @BeforeEach
    public void setCarService(){
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() {
        Car c = new Car("BMW", "X14", 1400, 400);
        CarRequest request = new CarRequest(c);
        carService.addCar(request);
        assertEquals(3,carRepository.count());
    }

    @Test
    void editCar() throws Exception {

        CarRequest request = new CarRequest(new Car(1,"OPEL", "V4", 400, 100));
        carService.editCar(request,1);
        CarResponse response = carService.findCarById(1);

        assertEquals("OPEL",response.getBrand());
        assertEquals("V4",response.getModel());
        assertEquals(400,response.getPricePrDay());

        Car c = new Car("BMW", "X14", 1400, 400);
         request = new CarRequest(c);
        carService.addCar(request);
        assertEquals(3,carRepository.count());
        assertTrue(carRepository.findById(3).isPresent());
        assertEquals(c.getModel(),"X14");
        Car b = c;
        b.setModel("C15");
        assertEquals(b.getModel(),"C15");




    }

    @Test
    void getCars() {
    }

    @Test
    void findCarById() {
    }

    @Test
    void deleteCar() {
    }

    @Test
    void deleteAllCars() {
    }

    @Test
    void deleteAllCarsByBrand() {
    }

    @Test
    void setBestDiscount() {
    }

    @Test
    void findCarByBrand() {
    }

    @Test
    void findAllByBrand() {
    }

    @Test
    void findAllByModel() {
    }

    @Test
    void findCarByModel() {
    }

    @Test
    void findCarByPricePrDay() {
    }
}