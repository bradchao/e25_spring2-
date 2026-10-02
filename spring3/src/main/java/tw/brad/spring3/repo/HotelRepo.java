package tw.brad.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring3.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
}
