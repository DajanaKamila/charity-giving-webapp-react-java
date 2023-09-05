import React, {useState, useEffect} from "react";
import DonateInfoMolecule from "../../molecules/create-donate/DonateInfoMolecule";
import DonateMoneyOrganism from "../../organisms/create-donate/DonateMoneyOrganism";
import ListOfDonationsOrganism from "../../organisms/create-donate/ListOfDonationsOrganism";
import axios from "axios";
import { useParams } from "react-router-dom";

const DonateDetailsPage = () => {

  const { fundraisingId } = useParams();
  const [fundraising, setFundraising] = useState(null);

  useEffect( () => {
    // console.log("Fundraising ID:", fundraisingId);
    axios.get("http://localhost:8080/api/v1/fundraisings/"+ `${fundraisingId}`)
         .then((result) => {
            const fundraisingData = result.data;
            setFundraising(fundraisingData);
         })
         .catch((error) => {
            console.error(error);
         });
}, [fundraisingId]);

  return (
    <div>
        <DonateInfoMolecule fundraising={fundraising}/>
        <DonateMoneyOrganism fundraising={fundraising}/>
        {/* <ListOfDonationsOrganism /> */}
    </div>
    );
};
  
  export default DonateDetailsPage;