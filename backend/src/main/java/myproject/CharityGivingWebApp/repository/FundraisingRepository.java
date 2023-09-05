package myproject.CharityGivingWebApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import myproject.CharityGivingWebApp.model.Donation;
import myproject.CharityGivingWebApp.model.Fundraising;

public interface FundraisingRepository extends CrudRepository<Fundraising, Long> {

    @Query("SELECT d FROM Donation d WHERE d.fundraising.id = :fundraisingId")
    Iterable<Donation> getDonationsOfFundraising(Long fundraisingId);

}
