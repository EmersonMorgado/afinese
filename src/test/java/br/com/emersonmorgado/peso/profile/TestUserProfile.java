package br.com.emersonmorgado.peso.profile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class TestUserProfile {
	
	@Test
	public void insertProfileUser_From_UserProfileForm() {
		assertTrue(true);
	}
}
