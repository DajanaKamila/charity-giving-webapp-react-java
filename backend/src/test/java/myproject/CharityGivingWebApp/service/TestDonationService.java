package myproject.CharityGivingWebApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.repository.DonationRepository;

@SpringBootTest
class TestDonationService { 
	
	@InjectMocks
	DonationServiceImp donationService;
	
	@Mock
	DonationRepository mockDonationRepo;
	
	@Mock
	Donation mockDonation;

	
	@Test
	@DisplayName("Save donaton")
	void test_saveDonation_callSaveMathodFromDonationRepo_returnDonationToBeSaved() {
		mockDonation = new Donation();
		when(mockDonationRepo.save(mockDonation)).thenReturn(mockDonation);
		
		Donation actual = donationService.saveDonation(mockDonation);
		
		assertThat(actual).isEqualTo(mockDonation);
		verify(mockDonationRepo, times(1)).save(mockDonation);	
	}
	
	@Test
	@DisplayName("Find all donations")
	void test_findAllDonations_callsFindAllFromDonationRepo() {
		donationService.findAllDonations();
		verify(mockDonationRepo).findAll();
	}
	
	@Test 
	@DisplayName("Find donation by ID")
	void test_findById_callsFindByIdFromDonationRepo() {
		donationService.findDonationById(1l);
		verify(mockDonationRepo, times(1)).findById(1l);
	}
	
	
	

}
