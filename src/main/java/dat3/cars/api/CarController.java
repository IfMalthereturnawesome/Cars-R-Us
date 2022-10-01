package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body){
        CarResponse res = carService.addCar(body);
        return res;
    }


    @GetMapping(path = "/car/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findCarById(id);
    }

    @GetMapping(path = "/brand/{brand}")
    CarResponse getCarByBrand(@PathVariable String brand) throws Exception {
        return carService.findCarByBrand(brand);
    }

    @GetMapping(path = "/model/{model}")
    CarResponse getCarByModel(@PathVariable String model) throws Exception {
        return carService.findCarByModel(model);
    }

    @GetMapping(path = "/{brand}")
    List<CarResponse> getCarsByBrand(@PathVariable String brand) throws Exception {
        return carService.findAllByBrand(brand);
    }

    @GetMapping(path = "/{model}")
    List<CarResponse> getCarsByModel(@PathVariable String model) throws Exception {
        return carService.findAllByModel(model);
    }

    @PutMapping("/car/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/bestdiscount/{id}/{discount}")
    ResponseEntity<Boolean> editCarDiscount(@PathVariable int id, @PathVariable double discount){
        carService.setBestDiscount(id, discount);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/car/{id}")
    ResponseEntity<Boolean> deleteCar(@PathVariable int id) throws Exception {
        carService.deleteCar(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{deleteallcars}") //delete all cars
    ResponseEntity<Boolean> deleteAllCars(){
        carService.deleteAllCars();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteallcarsbybrand/{brand}") //delete all cars by brand
    ResponseEntity<Boolean> deleteAllCarsByBrand(@PathVariable String brand){
        carService.deleteAllCarsByBrand(brand);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(path = "/cheapcars/{pricePrDay}")
    CarResponse getCarByPricePrDay(@PathVariable double pricePrDay) throws Exception {
        return carService.findCarByPricePrDay(pricePrDay);
    }

}
