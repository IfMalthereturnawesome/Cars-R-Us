package dat3.cars.service;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRespository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRespository memberRespository;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRespository memberRespository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRespository = memberRespository;
        this.carRepository = carRepository;
    }

    public List<ReservationResponse> findReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> reservationResponses = reservations.stream().map(reservation -> new ReservationResponse(reservation,true)).toList();
        return reservationResponses;
    }

    public void reserveCar(String username, int carId, LocalDate reservationDate) {
        Member member = memberRespository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this username " + username + " not found"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this Id " + carId  + " not found"));;

        boolean exist =  reservationRepository.existsByCar_IdAndRentalDate(carId, reservationDate);

        if (exist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this Id " + carId  + " is already reserved on this date");
        }

        // This reserves the car ALSO if already reserved
        Reservation reservation = Reservation.builder()
                .car(car)
                .member(member)
                .rentalDate(reservationDate)
                .build();


        reservationRepository.save(reservation);
    }

    public ReservationResponse findReservationById(@PathVariable int id) throws Exception{
        Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
        return new ReservationResponse(foundReservation,false);
    }

    public void deleteById(int id){
        reservationRepository.deleteById(id);
    }

}
