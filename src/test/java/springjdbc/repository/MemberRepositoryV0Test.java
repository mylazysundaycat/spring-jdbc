package springjdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springjdbc.domain.Member;

import java.nio.charset.Charset;
import java.sql.SQLException;

@Slf4j
public class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException{
        //save
        Member member = new Member("방꾸미기2",2);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}",findMember);
        Assertions.assertThat(member).isEqualTo(findMember);

        //update money 10000->20000
        repository.update(member.getMemberId(), 200000);
        Member updatedMember = repository.findById(member.getMemberId());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(200000);




    }
}
