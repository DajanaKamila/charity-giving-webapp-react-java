package myproject.CharityGivingWebApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;
import myproject.CharityGivingWebApp.repository.FundraisingRepository;

@SpringBootTest
class TestFundraisingService {

	@InjectMocks
	FundraisingServiceImp fundraisingService;

	@Mock
	FundraisingRepository mockFundraisingRepo;

	@Mock
	Fundraising mockFundraising;

	@Mock
	Donation mockDonation;

	@Mock
	List<Donation> mockDonationsList;

	@Test
	@DisplayName("Save fundraising")
	void test_saveFundraising_callSaveMathodFromFundraisingRepo_returnFundraisingToBeSaved() {
		mockFundraising = new Fundraising();
		when(mockFundraisingRepo.save(mockFundraising)).thenReturn(mockFundraising);

		Fundraising actual = fundraisingService.saveFundraising(mockFundraising);
		assertThat(actual).isEqualTo(mockFundraising);
		verify(mockFundraisingRepo, times(1)).save(mockFundraising);

	}

	@Test
	@DisplayName("Find all fundraisings")
	void test_findAllFundraisings_callsFindAllFromFundraisingRepo() {
		fundraisingService.findAllFundraisings();
		verify(mockFundraisingRepo).findAll();
	}

	// @Test
	// @DisplayName("Find fundraising by ID")
	// void test_findById_callsFindByIdFromFundraisingRepo() {
	// fundraisingService.findFundraisingById(1l);
	// verify(mockFundraisingRepo, times(1)).findById(1l);
	// }

	// @Test
	// @DisplayName("Add donation to fundraising")
	// void
	// test_addDonationToFundraising_setsFundraisingAndAddsDonationToFundraising() {
	// when(mockFundraising.getDonations()).thenReturn(mockDonationsList);
	// fundraisingService.addDonationToFundraising(mockFundraising, mockDonation);

	// verify(mockDonation).setFundraising(mockFundraising);
	// verify(mockFundraising.getDonations(),times(1)).add(mockDonation);
	// verify(mockFundraisingRepo, times(1)).save(mockFundraising);
	// }

}
