package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car,Integer> {

    Car findCarByModel(String model);

    boolean existsCarById(int id);

    //Car findCarById(int id);

    Car findCarByBrand(String brand);

    Car deleteAllByBrand(String brand);

    List<Car> findAllByBrand(String brand);

    List<Car> findAllByModel(String model);

    Car findCarByPricePrDay(double pricePrDay);



}
