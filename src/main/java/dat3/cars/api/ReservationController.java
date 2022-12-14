package dat3.cars.api;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{memberId}/{carId}/{date}")
    public void makeReservation(@PathVariable String memberId , @PathVariable int carId, @PathVariable String date) {
        //date is assumed to come in as a string formatted like: day-month-year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate reservationDate = LocalDate.parse(date,formatter);
        reservationService.reserveCar(memberId,carId,reservationDate);
    }

    @GetMapping
    List<ReservationResponse> getReservations(){
        return reservationService.findReservations();
    }

    @GetMapping("/{reservationId}")
    ReservationResponse getReservationById(@PathVariable int reservationId) throws Exception{
        return reservationService.findReservationById(reservationId);
    }

  /*
  @PutMapping("/{reservationId}")
  ResponseEntity<Boolean> editReservation(@RequestBody ReservationRequest reservationRequest,@PathVariable int reservationId){
    reservationService.editReservation(reservationRequest,reservationId);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
   */

    @DeleteMapping("/{reservationId}")
    void deleteReservationByID(@PathVariable int reservationId){
        reservationService.deleteById(reservationId);
    }
}
