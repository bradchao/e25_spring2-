package tw.brad.spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring2.entity.Info;

@Repository
public interface InfoRepo extends JpaRepository<Info, Long> {
}
