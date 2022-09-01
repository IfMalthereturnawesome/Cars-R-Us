package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {
    int id;
    String brand;
    String model;
    double pricePrDay;

    public static Car getCarEntity(CarRequest c){
        return new Car(c.id,c.brand,c.model,c.pricePrDay);
    }

    // Car to CarRequest conversion
    public CarRequest(Car c){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
    }
}
