package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Member extends UserWithRoles {
    @Column(nullable = false,length = 450)
    private String firstName;

    @Column(nullable = false,length = 450)
    private String lastname;
    @Column(nullable = false,length = 450)
    private String street;
    @Column(nullable = false,length = 450)
    private String city;
    @Column(nullable = false,length = 450)
    private int zip;
    @Column(nullable = false,length = 450)
    private int approved;
    @Column(nullable = false,length = 450)
    private int ranking;


    public Member() {

    }

    public Member(String user, String password, String email, String firstName) {
        super(user, password, email);
        this.firstName = firstName;
    }
}
