package br.com.emersonmorgado.peso.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.emersonmorgado.peso.controller.form.WeightProjection;
import br.com.emersonmorgado.peso.model.Weight;

@Repository
public interface WeightRepository extends PagingAndSortingRepository<Weight, Long>, JpaSpecificationExecutor<Weight> {

	Page<Weight> findByUserUsername(String username, Pageable pageable);
//
//	@Query(value = "SELECT w.weight, w.date FROM weight w", nativeQuery = true)
//	List<WeightProjection> findWeightSt();
//	
//	@Query("SELECT * FROM Weight w WHERE w.date=:date AND w.user_username=:username")
//	Weight getDateAndUsername(LocalDate date, String username);

	
	@Query(value = "SELECT w FROM Weight w WHERE w.date = :date AND w.user_username = :username",
			nativeQuery = true)
	Weight getDateAndUsername(LocalDate date, String username);
	
	
//	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
//			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
}




