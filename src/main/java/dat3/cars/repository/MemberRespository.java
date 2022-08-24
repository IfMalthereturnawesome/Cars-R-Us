package dat3.cars.repository;

import org.springframework.data.repository.CrudRepository;

import dat3.cars.entity.Member;

public interface MemberRespository extends CrudRepository<Member,String> {

}
