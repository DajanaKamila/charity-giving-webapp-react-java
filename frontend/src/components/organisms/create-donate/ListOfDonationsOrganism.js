import axios from "axios";
import React, { useEffect, useState } from "react";
import DonationMolecule from "../../molecules/create-donate/DonationMolecule";
import DonationListIntroAtom from "../../atoms/create-donate/HeaderH5Atom"

const ListOfDonationsOrganism = () => {

    const [donations, setDonations] = useState([]);

    useEffect (() => {
        axios.get("http://localhost:8080/api/v1/donations")
             .then((result) => {
                const donations = result.data;
                setDonations(donations);
             })
             .catch((error) => {
                console.error(error);
             });
    }, []);

  return (
    <div>
        <DonationListIntroAtom text="The most recent donations"/>
        <React.Fragment>
            {donations.map((donation) => (
                <DonationMolecule 
                    key={donation.id}
                    donation={donation} 
                    username={donation.donor.username}
                    fundraisingName={donation.fundraising.name} />
            ))}
        </React.Fragment>
    </div>
    );
};
  
  export default ListOfDonationsOrganism;