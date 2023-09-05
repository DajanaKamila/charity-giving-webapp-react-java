import React from "react";
import ListOfFundraisingsOrganism from "../../organisms/create-donate/ListOfFundraisingsOrganism";
import ListOfDonationsOrganism from "../../organisms/create-donate/ListOfDonationsOrganism";
import DonationListIntroAtom from "../../atoms/create-donate/HeaderH5Atom";

const DonatePage = () => {
  return (
    <div>
      <ListOfFundraisingsOrganism />
      {/* <ListOfDonationsOrganism /> */}
    </div>
    );
};
  
  export default DonatePage;