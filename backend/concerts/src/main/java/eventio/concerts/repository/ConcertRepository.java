package eventio.concerts.repository;

import eventio.concerts.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID> {
    List<Concert> findAllByOrderByDateTimeAsc();
    List<Concert> findAllByVenueIdOrderByDateTimeAsc(UUID venueId);
    List<Concert> findAllByPerformerContainsIgnoreCaseOrderByDateTimeAsc(String performer);
    List<Concert> findAllByVenueIdAndPerformerContainsIgnoreCaseOrderByDateTimeAsc(UUID venueId, String performer);
}
