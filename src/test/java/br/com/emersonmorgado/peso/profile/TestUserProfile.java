package br.com.emersonmorgado.peso.profile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.emersonmorgado.peso.controller.form.UserProfileForm;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.repository.UserRepository;
import br.com.emersonmorgado.peso.service.ServiceUserProfile;

@DataJpaTest
@ActiveProfiles("test")
public class TestUserProfile {
	
	@Autowired
    private TestEntityManager em;
	
	@MockBean
	private ServiceUserProfile serviceUserProfile;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void insertProfileUser_From_UserProfileForm() {
		
		boolean flag = false;
		
		UserProfileForm upf = new UserProfileForm();
		upf.setName("Joao da Silva Gomes");
		upf.setHeight("70");
		upf.setSex("Male");
		upf.setUsername("joao");
		upf.setBirthday("1996/10/10");
		
//		try {
//			serviceUserProfile.addUserPrifile(upf);
//		} catch (Exception e) {
//			flag = true;
//		}
//		assertTrue(flag,"Must be true because hasn't the user in database");
		//serviceUserProfile can not add userprofile in database because user unregistered.
		
		User user = new User();
		user.setUsername(upf.getUsername());
		em.persistAndFlush(user);

		//assertTrue(flag,"There is the user in database");
		assertTrue(userRepository.existsById(upf.getUsername()),"There is the user in database");
	}
		
//		User user = new User();
//		user.setUsername("Joao");
//		user.setEmail("joao@email.com");
//		
//		UserProfile userProfile = new UserProfile(user);
//		Long year = 30L;
//		LocalDate birthday = LocalDate.now().plusYears(year);
//		userProfile.setBirthday(birthday);
//		userProfile.setHeight(75);
//		userProfile.setName("Joao Albert");
//		userProfile.setSex(Sex.MALE);
//		em.persist(userProfile);
//		
		
		//assertEquals(expected, actual, message);
		
		
		
		
		
//				
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate birthday = LocalDate.parse("12/10/1996", formatter);
//		
//		User user = new User();
//		user.setUsername("test");
//		
//		UserProfile userProfile = new UserProfile(user);
//		userProfile.setBirthday(birthday);
//		userProfile.setHeight(90.4);
//		userProfile.setName("Test Name Of Silva");
//		userProfile.setSex(Sex.MALE);
//	
//		
//		//user.setUserProfile(userProfile);
//		//Assert.hasText("12/10/1996", user.getUserProfile().getBirthday().toString());
//	}
}
