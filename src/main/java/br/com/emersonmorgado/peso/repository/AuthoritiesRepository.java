package br.com.emersonmorgado.peso.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.emersonmorgado.peso.model.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
	Authorities findByUsername(String username);
}
