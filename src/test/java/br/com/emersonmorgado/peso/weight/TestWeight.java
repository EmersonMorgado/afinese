package br.com.emersonmorgado.peso.weight;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.emersonmorgado.peso.model.Authorities;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserStatus;
import br.com.emersonmorgado.peso.repository.UserRepository;
import br.com.emersonmorgado.peso.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class TestWeight {
	
	@Autowired
    private TestEntityManager em;
//	
//	@Autowired
//	private WeightRepository weightRepository;
//	
	@Autowired
	private UserRepository userRepository;
	
	@MockBean
	private UserService userService;

	
//	public void mustReceiveOnlyOneWeightPerDate(){
//
//		//given
//		User user = new User();
//		user.setUsername("Name Test");
//				
//		Weight weight = new Weight();
//		LocalDate date = LocalDate.now();
//		weight.setDate(date);
//		weight.setWeight(100.4);
//		
//		WeightForm weightForm = new WeightForm();
//		weightForm.setDate("10/10/2021");
//		weightForm.setUsername(user.getUsername());
//		weightForm.setWeight("100");
//		
//		entityManager.persist(weight);
//		entityManager.flush();
//		
//		//when
//		Sort sort = Sort.by(Sort.Direction.DESC,"date");
//		Pageable pageable = PageRequest.of(1, 10, sort);
//		weightRepository.findByUserUsername(user.getUsername(), pageable);
//		
//		//then
//		System.out.println(userRepository.findByUsername("Name Test"));
//		Assert.isNull(null,"Deve estar nulo");
//		//weightService.addWeigh(weightForm);
//		
//	}
	
	@Test
	public void mustListAllWeights() {
		
		Authorities authorities = new Authorities();
		authorities.setUsername("Test_Name");
		authorities.setAuthority(UserStatus.ROLE_ADM);
		em.persist(authorities);
		
		
		User user = new User();
		user.setUsername("Test_Name");
		user.setEmail("test@email.com.br");
		user.setPassword("ddddd");
		em.persist(user);
		
		userService.addUser(user, authorities);
		//userRepository.save(user);
		
		System.out.println("TESTE RESULT...................:" + userService.findAll());
		System.out.println("TESTE RESULT2...................:" + userRepository.findAll());
		assertNotNull(userRepository.findAll());
		
	}
	
}
