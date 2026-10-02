package tw.brad.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring3.entity.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findByAccount(String account);
}
