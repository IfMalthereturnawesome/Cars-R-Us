package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    private int id;

    private String member_name;

    private int carId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime reservationDate;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationResponse(Reservation reservation, boolean includeAll){
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        if (includeAll){
            this.member_name = reservation.getMember().getUsername();
            this.carId = reservation.getCar().getId();
        }
    }


    public boolean hasReservation(Member member, List<Reservation> reservations) {
        for (Reservation r : reservations) {
            if (r.getMember().getUsername().equals(member.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
