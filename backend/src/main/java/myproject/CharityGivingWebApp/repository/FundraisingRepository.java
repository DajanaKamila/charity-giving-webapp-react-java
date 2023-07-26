package myproject.CharityGivingWebApp.repository;

import org.springframework.data.repository.CrudRepository;

import myproject.CharityGivingWebApp.model.Fundraising;

public interface FundraisingRepository extends CrudRepository<Fundraising, Long> {

}
