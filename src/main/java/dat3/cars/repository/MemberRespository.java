package dat3.cars.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import dat3.cars.entity.Member;

public interface MemberRespository extends JpaRepository<Member,String> {

    Member findMemberByUsername(String username);

}
