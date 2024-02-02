package springjdbc.repository;

import org.junit.jupiter.api.Test;
import springjdbc.domain.Member;

import java.nio.charset.Charset;
import java.sql.SQLException;

public class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException{
        //save
        Member member = new Member("리얼테스트임",999999999);
        repository.save(member);


    }
}
