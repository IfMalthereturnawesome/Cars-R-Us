package dat3.cars.entity;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends UserWithRoles {

    private String firstName;


    private String lastname;

    private String street;

    private String city;

    private int zip;

    private int approved;

    private int ranking;


    public Member() {

    }

    public Member(String user, String password, String email, String firstName) {
        super(user, password, email);
        this.firstName = firstName;
    }

    public Member(String username, String email, String password, String firstName, String lastname, String street, String city, int zip, int approved, int ranking) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }
}
