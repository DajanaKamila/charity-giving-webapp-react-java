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

import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.service.FundraisingServiceImp;

@SpringBootTest
@AutoConfigureMockMvc
class TestFundraisingController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FundraisingServiceImp mockFundraisingService;

	private Fundraising fundraising, fundraising2, fundraising3;

	@BeforeEach
	public void setUp() {
		fundraising = new Fundraising();
		fundraising.setName("New fundraising");
		fundraising.setGoal(new BigDecimal(20000));
		fundraising.setDescription("Description");

		fundraising2 = new Fundraising();
		fundraising2.setName("New fundraising 2");

		fundraising3 = new Fundraising();
	}

	@Test
	@DisplayName("Save correct fundraising")
	void test_saveFundraising_returnSavedFundraising() throws JsonProcessingException, Exception {
		given(mockFundraisingService.saveFundraising(ArgumentMatchers.any(Fundraising.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/api/v1/fundraisings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(fundraising)));

		response.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("New fundraising")));

		verify(mockFundraisingService, times(1)).saveFundraising(ArgumentMatchers.any(Fundraising.class));
	}

	@Test
	@DisplayName("Save invalid fundraising")
	void test_saveFundraising_returnBadRequest_whenFundraisingIsInvalid() throws JsonProcessingException, Exception {
		ResultActions response = mockMvc.perform(post("/api/v1/fundraisings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(fundraising3)));

		response.andExpect(status().isBadRequest());
		verify(mockFundraisingService, never()).saveFundraising(ArgumentMatchers.any(Fundraising.class));
	}

	@Test
	@DisplayName("Find all fundraisings")
	void test_getAllFundraisings_returnListOfFundraisings_IfThereAreFundraisingsInDB() throws Exception {
		Iterable<Fundraising> fundraisings = List.of(fundraising, fundraising2);
		when(mockFundraisingService.findAllFundraisings()).thenReturn(fundraisings);

		ResultActions response = mockMvc.perform(get("/api/v1/fundraisings"));

		response.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("New fundraising"))
				.andExpect(jsonPath("$[1].name").value("New fundraising 2"));

		verify(mockFundraisingService, times(1)).findAllFundraisings();
	}

	@Test
	@DisplayName("Find all fundraisings - no fundraisings")
	void test_getAllFundraisings_returnFundraisingNotFoundException_IfThereAreNoFundraisingsInDB() throws Exception {
		when(mockFundraisingService.findAllFundraisings()).thenReturn(null);
		ResultActions response = mockMvc.perform(get("/api/v1/fundraisings"));
		
		response.andExpect(status().isNotFound());
		verify(mockFundraisingService, times(1)).findAllFundraisings();
	}

	// @Test
	// @DisplayName("Find fundraising by ID - existing fundraising")
	// void test_findFundraisingById_returnExisitingFundraising() throws Exception {
	// long id = 1;
	// when(mockFundraisingService.findFundraisingById(id)).thenReturn(fundraising);

	// ResultActions response = mockMvc.perform(get("/api/v1/fundraisings/{id}",
	// id));

	// response.andExpect(status().isOk())
	// .andExpect(jsonPath("$.name", is("New fundraising")))
	// .andExpect(jsonPath("$.goal", is(20000)))
	// .andExpect(jsonPath("$.description", is("Description")));

	// verify(mockFundraisingService, times(1)).findFundraisingById(id);
	// }

	// @Test
	// @DisplayName("Find fundraising by ID - not existing fundraising")
	// void
	// test_findFundraisingById_returnFundrasingNotFoundException_whenFundraisingNotFound()
	// throws Exception {
	// long id = 1;
	// when(mockFundraisingService.findFundraisingById(id)).thenReturn(null);

	// ResultActions response = mockMvc.perform(get("/api/v1/fundraisings/{id}",
	// id));

	// response.andExpect(status().isNotFound());
	// verify(mockFundraisingService, times(1)).findFundraisingById(id);
	// }

}
