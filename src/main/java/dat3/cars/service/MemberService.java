package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    MemberRespository memberRespository;

    public MemberService(MemberRespository memberRespository) {
        this.memberRespository = memberRespository;
    }

    public List<MemberResponse> findMembers() {
        List<Member> members = memberRespository.findAll();
        List<MemberResponse> response = members.stream().map(member -> new MemberResponse(member, false)).toList();
        return response;
    }
}
