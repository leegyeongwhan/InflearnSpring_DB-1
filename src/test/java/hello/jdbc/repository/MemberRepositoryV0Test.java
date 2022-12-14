package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.NoSuchMessageException;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV100", 10000);
        memberRepositoryV0.save(member);
        Member byId = memberRepositoryV0.findById(member.getMemberId());
        log.info("findMember{}", byId);
        assertThat(byId).isEqualTo(member);

        //upsate
        memberRepositoryV0.update(member.getMemberId(), 20000);
        Member updatedMember = memberRepositoryV0.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        memberRepositoryV0.delete(member.getMemberId());
        assertThatThrownBy(() -> memberRepositoryV0.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}