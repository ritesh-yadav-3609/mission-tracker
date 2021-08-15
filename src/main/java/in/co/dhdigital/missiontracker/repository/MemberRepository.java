package in.co.dhdigital.missiontracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.dhdigital.missiontracker.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByUsername(String username);
}
