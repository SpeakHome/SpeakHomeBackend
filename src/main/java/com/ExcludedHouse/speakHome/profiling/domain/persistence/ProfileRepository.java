package com.ExcludedHouse.speakHome.profiling.domain.persistence;

import com.ExcludedHouse.speakHome.profiling.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
  List<Profile> findByRoleId(Long roleId);
  Page<Profile> findByRoleId(Long roleId, Pageable pageable);
  Optional<Profile> findByLastName(String lastName);
}
