package springjdbc.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import springjdbc.domain.Member;
import springjdbc.repository.MemberRepositoryV3;

import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_2 {

    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }
    public void accountTransfer(String fromId, String toId, int money) throws SQLException{
        txTemplate.executeWithoutResult(
                (status) -> {
                    try{
                        //비즈니스 로직
                        bizLogic(fromId, toId, money);
                    } catch(Exception e){
                        throw new IllegalStateException(e);
                    }
                }
        );
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException{
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney()-money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() - money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}

