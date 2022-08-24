package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 450)
    private String brand;
    @Column(nullable = false,length = 450)
    private String model;
    @Column(nullable = false,length = 450)
    private double pricePrDay;
    @Column(length = 450)
    private double bestDiscount;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    public Car() {
    }

    public Car(String brand, String model, double pricePrDay, double bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
