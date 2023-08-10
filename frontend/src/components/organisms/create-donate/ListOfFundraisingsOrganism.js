import React, {useState, useEffect} from "react";
import axios from "axios";
import Fundraising from "../../molecules/create-donate/FundraisingMolecule"

const ListOfFundraisingsOrganism = () => {

    const [fundraisings, setFundraisings] = useState([]);

    useEffect (() => {
        axios.get("http://localhost:8080/api/v1/fundraisings")
             .then((result) => {
                const fundraisings = result.data;
                setFundraisings(fundraisings);
             })
             .catch((error) => {
                console.error(error);
             });
    }, []);

  return (
    <div>
        <p></p>
        <React.Fragment>
            {fundraisings.map((fundraising) => (
                <Fundraising 
                    key={fundraising.id}
                    fundraising={fundraising} />
            ))}
        </React.Fragment>
    </div>
    );
};
  
  export default ListOfFundraisingsOrganism;