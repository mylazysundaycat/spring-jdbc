package springjdbc.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springjdbc.domain.Member;

import java.nio.charset.Charset;
import java.sql.SQLException;

public class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException{
        //save
        Member member = new Member("방꾸미기",1);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        Assertions.assertThat(member).isEqualTo(findMember);


    }
}
