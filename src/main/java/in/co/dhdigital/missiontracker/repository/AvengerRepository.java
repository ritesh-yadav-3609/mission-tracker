package in.co.dhdigital.missiontracker.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import in.co.dhdigital.missiontracker.entity.Avenger;

public interface AvengerRepository extends JpaRepository<Avenger, Long> {
	
	Optional<Avenger> findByUsername(String name);

	Object deleteByUsername(String username);

}
