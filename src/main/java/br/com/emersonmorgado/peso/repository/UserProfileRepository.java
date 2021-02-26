package br.com.emersonmorgado.peso.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.emersonmorgado.peso.model.UserProfile;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long> {
}




