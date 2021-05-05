package br.com.emersonmorgado.peso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emersonmorgado.peso.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	UserProfile findByUserUsername(String username);
	boolean existsByUserUsername(String username);
}




