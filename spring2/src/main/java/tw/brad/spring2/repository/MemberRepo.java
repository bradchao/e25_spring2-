package tw.brad.spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring2.entity.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
}
