package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Rental;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRespository;
import dat3.cars.repository.RentalRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    MemberRespository memberRespository;
    CarRepository carRepository;
    RentalRepository rentalRepository;
    ReservationRepository reservationRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MemberRespository memberRespository, CarRepository carRepository, RentalRepository rentalRepository, ReservationRepository reservationRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.memberRespository = memberRespository;
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
        this.reservationRepository = reservationRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        Member m1 = new Member("karl1",passwordUsedByAll,"h@hotmail.com","karl");
        m1.addRole(Role.USER);
        memberRespository.save(m1);
        Member m2 = new Member("Jakob2",passwordUsedByAll,"adl@akdl","Jakob","Hansen","Graver","København","2400",true,10);
        m2.addRole(Role.USER);
        memberRespository.save(m2);
        setupUserWithRoleUsers();
        Car c1 = new Car("BMW","M3",550,100);
        Car c2 = new Car("BMW","M1",750,100);
        carRepository.save(c1);
        carRepository.save(c2);

        Reservation r1 = Reservation.builder().car(c1).member(m1).rentalDate(LocalDate.now()).build();
        Reservation r2 = Reservation.builder().car(c2).member(m2).rentalDate(LocalDate.now()).build();

        m1.addReservation(r1);
        m2.addReservation(r2);
/*
        Rental rental1 = Rental.builder().car(c1).member(m1).rentalDate(LocalDate.now()).pricePrDay(100).build();
        Rental rental2 = Rental.builder().car(c2).member(m2).rentalDate(LocalDate.now()).pricePrDay(100).build();

        m1.addRental(rental1);
        m2.addRental(rental2);*/
        memberRespository.save(m1);
        memberRespository.save(m2);

    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
