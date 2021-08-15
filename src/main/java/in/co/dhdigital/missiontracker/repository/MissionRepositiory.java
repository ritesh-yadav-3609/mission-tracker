package in.co.dhdigital.missiontracker.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.utils.Enums.Status;

public interface MissionRepositiory extends JpaRepository<Mission, Long> {

	@Modifying
	@Transactional
	@Query("update Mission m set m.status=:status where id=:id")
	Object updateStatus(@Param("status") Status status, @Param("id") Long id);

	List<Mission> findByStatus(Status status);

	List<Mission> findByAvenger(Avenger avenger);

	List<Mission> findByAvengerAndStatus(Avenger avenger, Status assign);

}
