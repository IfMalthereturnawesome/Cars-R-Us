package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRespositoryTest {

    @Autowired
    MemberRespository memberRespository;

    static String member1;
    static String member2;
    @BeforeAll
    public static void setupData(@Autowired MemberRespository memberRespository){
        Member m1 = new Member("Kal","amdo@","test12","Karl","Hansen","Graver","København",2400,1,2);
        Member m2 = new Member("Jakob","aodm@","test12",true, LocalDateTime.now(),LocalDateTime.now(),"Jakob","Jak","Dad","AFA",2400,2,3);
        memberRespository.save(m1);
        memberRespository.save(m2);

        member1 = m1.getUsername();
        member2 = m2.getUsername();
    }

    @Test
    void testFindMemberByUsername(){
        Member found = memberRespository.findMemberByUsername("Kal");
        assertEquals(member1,found.getUsername());
        assertEquals("Kal",found.getUsername());
    }

}