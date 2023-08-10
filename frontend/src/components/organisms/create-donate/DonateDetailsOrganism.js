import React, {useState, useEffect} from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

const DonateDetailsOrganism = () => {
    const { fundraisingId } = useParams();
    const [fundraising, setFundraising] = useState(null);

    useEffect( () => {
        console.log("Fundraising ID:", fundraisingId);
        axios.get("http://localhost:8080/api/v1/fundraisings/"+ `${fundraisingId}`)
             .then((result) => {
                const fundraisingData = result.data;
                setFundraising(fundraisingData);
             })
             .catch((error) => {
                console.error(error);
             });
    }, [fundraisingId]);

    console.log("Fundraising state:", fundraising);

  return (
    <div>
    {fundraising ? (
      <React.Fragment>
        <h2>{fundraising.name}</h2>
        <p><b>Goal:</b> {fundraising.goal}</p>
        <p>{fundraising.description}</p>
      </React.Fragment>
    ) : (
      <p>Loading...</p>
    )}
  </div>
  );
};

export default DonateDetailsOrganism;