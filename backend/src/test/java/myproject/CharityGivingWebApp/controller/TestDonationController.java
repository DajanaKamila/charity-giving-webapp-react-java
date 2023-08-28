package myproject.CharityGivingWebApp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import myproject.CharityGivingWebApp.service.DonationServiceImp;

@SpringBootTest
@AutoConfigureMockMvc
class TestDonationController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private DonationServiceImp mockDonationService;
	
	private Donation donation, donation2, donation3;
	
	@BeforeEach
	public void setUp() {
		donation = new Donation();
		donation.setAmount(new BigDecimal(100));
		donation.setComment("Comment");
		
		donation2 = new Donation();
		donation2.setAmount(new BigDecimal(150));
		
		donation3= new Donation();
	}

	@Test
	@DisplayName("Save correct donation")
	void test_saveDonation_returnSavedDonation() throws JsonProcessingException, Exception {
		given(mockDonationService.saveDonation(ArgumentMatchers.any(Donation.class)))
			.willAnswer((invocation) -> invocation.getArgument(0));
		
		ResultActions response = mockMvc.perform(post("/api/v1/donations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(donation)));
		
		response.andExpect(status().isCreated())
				.andExpect(jsonPath("$.amount", is(100)))
				.andExpect(jsonPath("$.comment", is("Comment")));
		
		verify(mockDonationService, times(1)).saveDonation(ArgumentMatchers.any(Donation.class));
	}
	
//	@Test
//	@DisplayName("Save invalid donation")
//	void test_saveDonation_returnBadRequest_whenDonationIsInvalid() throws JsonProcessingException, Exception {
//        ResultActions response = mockMvc.perform(post("/api/v1/donations")
//        		.contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(donation3))
//                .param("bindingResult", "true")
//                );
//        
//        response.andExpect(status().isBadRequest());
//        verify(mockDonationService, never()).saveDonation(ArgumentMatchers.any(Donation.class));
//	}
//	
	@Test
	@DisplayName("Find all donations")
	void test_getAllDonations_returnListOfDonations_IfThereAreDonationsInDB() throws Exception {
		Iterable<Donation> donations = List.of(donation, donation2);
		when(mockDonationService.findAllDonations()).thenReturn(donations);
		
		ResultActions response = mockMvc.perform(get("/api/v1/donations"));
		
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].amount").value(100))
				.andExpect(jsonPath("$[1].amount").value(150));
		
		verify(mockDonationService, times(1)).findAllDonations();
	}
	
	@Test
	@DisplayName("Find all donations - no donations")
	void test_getAllDonations_returnDonationNotFoundException_IfThereAreNoDonationsInDB() throws Exception {
		when(mockDonationService.findAllDonations()).thenReturn(null);
		ResultActions response = mockMvc.perform(get("/api/v1/donations"));
		
		response.andExpect(status().isNotFound());
		verify(mockDonationService, times(1)).findAllDonations();
	}
	
	@Test
	@DisplayName("Find donation by ID - existing donation")
	void test_findDonationById_returnExisitingDonation() throws Exception {
		long id = 1;
		when(mockDonationService.findDonationById(id)).thenReturn(donation);
		
		ResultActions response = mockMvc.perform(get("/api/v1/donations/{id}", id));
		
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount", is(100)))
				.andExpect(jsonPath("$.comment", is("Comment")));

		verify(mockDonationService, times(1)).findDonationById(id);
	}
	
	@Test
	@DisplayName("Find donation by ID - not existing donation")
	void test_findDonationById_returnDonationNotFoundException_whenDonationNotFound() throws Exception {
		long id = 1;
		when(mockDonationService.findDonationById(id)).thenReturn(null);
		
		ResultActions response = mockMvc.perform(get("/api/v1/donations/{id}", id));
		
		response.andExpect(status().isNotFound());
		verify(mockDonationService, times(1)).findDonationById(id);
	}


}
