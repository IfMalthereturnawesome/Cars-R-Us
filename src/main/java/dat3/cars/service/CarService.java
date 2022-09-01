package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse addCar(CarRequest carRequest){
        if (carRepository.existsCarById(carRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exist");
        }

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);
        
        return new CarResponse(newCar, true);
    }
    
    public void editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exist"));
      
        if (body.getId() != id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change ID");

        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        carRepository.save(car);
        throw new ResponseStatusException(HttpStatus.OK, "Car updated");
    }
    
    public List<CarResponse> getCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return response;
    }
    
    public CarResponse findCarById(@PathVariable int id) throws Exception{
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
        return new CarResponse(car, false);
    }
    
    public void deleteCar(@PathVariable int id) throws Exception{
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
        carRepository.delete(car);
    }
    
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
    
    public void deleteAllCarsByBrand(@PathVariable String brand){
        carRepository.deleteAllByBrand(brand);
    }
    
    public void setBestDiscount(int id, double discount){
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
        car.setBestDiscount(discount);
        carRepository.save(car);
    }
    
    public CarResponse findCarByBrand(@PathVariable String brand) throws Exception{
        Car car = carRepository.findCarByBrand(brand);
        return new CarResponse(car, false);
    }
    
    public List<CarResponse> findAllByBrand(@PathVariable String brand){
        List<Car> cars = carRepository.findAllByBrand(brand);
        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return response;
    }
    
    public List<CarResponse> findAllByModel(@PathVariable String model){
        List<Car> cars = carRepository.findAllByModel(model);
        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return response;
    }
    
    public CarResponse findCarByModel(@PathVariable String model) throws Exception{
        Car car = carRepository.findCarByModel(model);
        return new CarResponse(car, false);
    }

    public CarResponse findCarByPricePrDay(@PathVariable double pricePrDay) throws Exception{
        Car car = carRepository.findCarByPricePrDay(pricePrDay);
        return new CarResponse(car, false);
    }
    
    
}
