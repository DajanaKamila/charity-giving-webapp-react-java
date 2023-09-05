package myproject.CharityGivingWebApp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.model.User;
import myproject.CharityGivingWebApp.service.UserServiceImp;

@SpringBootTest
@AutoConfigureMockMvc
class TestUserController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImp mockUserServiceImp;

	@MockBean
	private Fundraising mockFundraising;

	@MockBean
	private Donation mockDonation;

	@Autowired
	private ObjectMapper objectMapper;

	private User user, user2, user3;

	@BeforeEach
	public void setup() {
		user = new User();
		user2 = new User();
		user3 = new User();

		user.setUsername("username");
		user.setPassword("password");
		user.setFirstName("John");
		user.setLastName("Doe");

		user2.setUsername("username2");
		user2.setPassword("password2");
	}

	@Test
	@DisplayName("Save correct user")
	void test_saveUser_returnSavedUserAndCreatedStatus() throws JsonProcessingException, Exception {
		given(mockUserServiceImp.saveUser(ArgumentMatchers.any(User.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));

		response.andExpect(status().isCreated())
				.andExpect(jsonPath("$.username", is("username")))
				.andExpect(jsonPath("$.password", is("password")));
		verify(mockUserServiceImp, times(1)).saveUser(ArgumentMatchers.any(User.class));
	}

	// @Test
	// @DisplayName("Save invalid user")
	// void test_saveUser_returnBadRequest_whenUserIsInvalid() throws Exception {
	// ResultActions response = mockMvc.perform(post("/api/v1/users")
	// .contentType(MediaType.APPLICATION_JSON)
	// .content(objectMapper.writeValueAsString(user3))
	// .param("bindingResult", "true"));
	//
	// response.andExpect(status().isBadRequest());
	//
	// verify(mockUserServiceImp,
	// never()).saveUser(ArgumentMatchers.any(User.class));
	// }

	@Test
	@DisplayName("Find all users")
	void test_getAllUsers_returnsListOfUsers_IfThereAreUsersInDatabase() throws Exception {
		Iterable<User> users = List.of(user, user2);
		when(mockUserServiceImp.findAllUsers()).thenReturn(users);

		ResultActions response = mockMvc.perform(get("/api/v1/users"));

		response.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].username").value("username"))
				.andExpect(jsonPath("$[0].password").value("password"))
				.andExpect(jsonPath("$[1].username").value("username2"))
				.andExpect(jsonPath("$[1].password").value("password2"));

		verify(mockUserServiceImp, times(1)).findAllUsers();
	}

	@Test
	@DisplayName("Find all users - no users")
	void test_getAllUsers_returnsUserNotFoundException_IfThereAreNoUsersInDatabase() throws Exception {
		when(mockUserServiceImp.findAllUsers()).thenReturn(null);
		ResultActions response = mockMvc.perform(get("/api/v1/users"));
		
		response.andExpect(status().isNotFound());
		verify(mockUserServiceImp, times(1)).findAllUsers();
	}

	@Test
	@DisplayName("Find user by ID - exisiting user")
	void test_fidnUserById_returnsExisitingUser() throws Exception {
		long id = 1;
		when(mockUserServiceImp.findUserById(id)).thenReturn(user);

		ResultActions response = mockMvc.perform(get("/api/v1/users/{id}", id));

		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.username", is("username")))
				.andExpect(jsonPath("$.password", is("password")));

		verify(mockUserServiceImp, times(1)).findUserById(1L);
	}

	@Test
	@DisplayName("Find user by ID - not exisiting user")
	void test_fidnUserById_returnsUserNotFoundException_whenNoSuchUserInDB() throws Exception {
		long id = 1;
		when(mockUserServiceImp.findUserById(id)).thenReturn(null);

		ResultActions response = mockMvc.perform(get("/api/v1/users/{id}", id));
		response.andExpect(status().isNotFound());

		verify(mockUserServiceImp, times(1)).findUserById(id);
	}

	@Test
	@DisplayName("Add fundrising to user - existing user")
	void test_addFundraisingToUser_ReturnsStatusOk_whenUserExists() throws Exception {
		when(mockUserServiceImp.findUserById(1l)).thenReturn(user);
		
		ResultActions response = mockMvc.perform(post("/api/v1/users/1/fundraisings")
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockFundraising)));
				
		
		response.andExpect(status().isOk());
		verify(mockUserServiceImp, times(1)).addFundraisingToUser(ArgumentMatchers.any(User.class), ArgumentMatchers.any(Fundraising.class));
	}

	@Test
	@DisplayName("Add fundrising to user - not existing user")
	void test_addFundraisingToUser_ReturnUserNotFoundException_whenUserNotExists() throws Exception {
		when(mockUserServiceImp.findUserById(1l)).thenReturn(null);
		
		ResultActions response = mockMvc.perform(post("/api/v1/users/1/fundraisings")
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockFundraising)));
		
		response.andExpect(status().isNotFound());
		verify(mockUserServiceImp, never()).addFundraisingToUser(ArgumentMatchers.any(User.class), ArgumentMatchers.any(Fundraising.class));
	}

	@Test
	@DisplayName("Add donation to user - existing user")
	void test_addDonationToUser_ReturnsStatusOk_whenUserExists() throws Exception {
		when(mockUserServiceImp.findUserById(1l)).thenReturn(user);
		
		ResultActions response = mockMvc.perform(post("/api/v1/users/1/donations")
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockDonation)));
				
		
		response.andExpect(status().isOk());
		verify(mockUserServiceImp, times(1)).addDonationToUser(ArgumentMatchers.any(User.class), ArgumentMatchers.any(Donation.class));
	}

	@Test
	@DisplayName("Add donation to user - not existing user")
	void test_addDonationToUser_ReturnUserNotFoundException_whenUserNotExists() throws Exception {
		when(mockUserServiceImp.findUserById(1l)).thenReturn(null);
		
		ResultActions response = mockMvc.perform(post("/api/v1/users/1/donations")
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockDonation)));
		
		response.andExpect(status().isNotFound());
		verify(mockUserServiceImp, never()).addDonationToUser(ArgumentMatchers.any(User.class), ArgumentMatchers.any(Donation.class));
	}

	@Test
	@DisplayName("Log in user - exisiting user")
	void test_loginUser_withCorrectLoginAndPassword_returnsUser() throws Exception {
		when(mockUserServiceImp.findUserByUsernameAndPassword("username", "password")).thenReturn(user);
	
		ResultActions response = mockMvc.perform(post("/api/v1/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
		
		response.andExpect(status().isOk())
        		.andExpect(jsonPath("$.username").value("username"));
	
		verify(mockUserServiceImp, times(1)).findUserByUsernameAndPassword("username", "password");
	}

	@Test
	@DisplayName("Log in user - not exisiting user")
	void test_loginUser_withIncorrectLoginAndPassword_returnsUserNotFoundException() throws Exception {
		when(mockUserServiceImp.findUserByUsernameAndPassword("username", "password")).thenReturn(null);
		
		ResultActions response = mockMvc.perform(post("/api/v1/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
		
		response.andExpect(status().isNotFound());
		verify(mockUserServiceImp, times(1)).findUserByUsernameAndPassword("username", "password");
	}

}
