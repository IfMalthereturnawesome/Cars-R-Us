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
                new Car("Opel", "X13", 1300, 300)
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
        List<CarResponse> cars = carService.getCars();
        assertEquals(2,cars.size());
        assertThat(cars,hasItem(hasProperty("brand",is("BMW"))));
        assertThat(cars,hasItem(hasProperty("brand",is("Opel"))));

    }

    @Test
    void findCarById() throws Exception {

        CarResponse response = carService.findCarById(1);
        assertEquals("BMW",response.getBrand());
    }

    @Test
    void deleteCar() throws Exception {

        carService.deleteCar(1);
        assertEquals(1,carRepository.count());
        assertFalse(carRepository.findById(1).isPresent());
    }

    @Test
    void deleteAllCars() {

        carService.deleteAllCars();
        assertEquals(0,carRepository.count());
    }

    @Test
    void deleteAllCarsByBrand() {
        carService.deleteAllCarsByBrand("BMW");
        assertEquals(1,carRepository.count());
        assertFalse(carRepository.findById(1).isPresent());
    }

    @Test
    void setBestDiscount() {
        carService.setBestDiscount(1,10);
        assertEquals(10,carRepository.findById(1).get().getBestDiscount());
    }

    @Test
    void findCarByBrand() throws Exception {

        CarResponse car =  carService.findCarByBrand("BMW");
        assertNotEquals("Opel",car.getBrand());
        assertEquals("BMW",car.getBrand());
    }

    @Test
    void findAllByBrand() {

        List<CarResponse> cars = carService.findAllByBrand("BMW");
        assertEquals(1,cars.size());
        assertEquals("BMW",cars.get(0).getBrand());
    }

    @Test
    void findAllByModel() {

        List<CarResponse> cars = carService.findAllByModel("X12");
        assertEquals(1,cars.size());
        assertEquals("X12",cars.get(0).getModel());
    }

    @Test
    void findCarByModel() throws Exception {

        CarResponse car = carService.findCarByModel("X12");
        assertEquals("X12",car.getModel());
    }

    @Test
    void findCarByPricePrDay() throws Exception {

        CarResponse car = carService.findCarByPricePrDay(1200);
        assertEquals(1200,car.getPricePrDay());
        assertNotEquals(12300,car.getPricePrDay());
    }
}