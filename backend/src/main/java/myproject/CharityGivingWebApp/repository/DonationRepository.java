package myproject.CharityGivingWebApp.repository;

import org.springframework.data.repository.CrudRepository;

import myproject.CharityGivingWebApp.model.Donation;

public interface DonationRepository extends CrudRepository<Donation, Long>{

}
