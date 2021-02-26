package br.com.emersonmorgado.peso.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.emersonmorgado.peso.controller.form.WeightProjection;
import br.com.emersonmorgado.peso.model.Weight;

@Repository
public interface WeightRepository extends PagingAndSortingRepository<Weight, Long> {

	Page<Weight> findByUserUsername(String username, Pageable pageable);

	@Query(value = "SELECT w.weight, w.date FROM weight w", nativeQuery = true)
	List<WeightProjection> findWeightSt();

	//Weight findByDate(LocalDate date);

}




