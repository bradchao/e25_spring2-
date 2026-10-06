package tw.brad.spring4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring4.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
