import React, {useState, useEffect} from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import DonateFormMolecule from "../../molecules/create-donate/DonateFormMolecule"

const DonateDetailsOrganism = () => {
    const { fundraisingId } = useParams();
    const [fundraising, setFundraising] = useState(null);
    const [name, setName] = useState("");
    const [isAnonymous, setIsAnonymous] = useState(false);

    const [amount, setAmount] = useState("");
    const [comment, setComment] = useState("");
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
    const userId = JSON.parse(localStorage.getItem("userId"));


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

    const onHandleChange = (e) => {
      const { name, value, type, checked } = e.target;
      if (type === "checkbox") setIsAnonymous(checked);
      else if (name === "name") setName(value);
      else if (name === "amount") setAmount(value);
      else if (name === "comment") setComment(value);
  };

  const onHandleSubmit = (e) => {
    e.preventDefault();
    const errors = {};
    
    if (amount === "") {
      errors.amount = "Amount is a required field.";
    }
    
    if (Object.keys(errors).length > 0) {
      setErrors(errors);
      return;
    }

    const newDonation = {
      name: name,
      amount: amount,
      comment: comment,
      isAnonymous: isAnonymous,
      founder: {loggedInUser},
      fundraising: {fundraising}
    };

    axios.post("http://localhost:8080/api/v1/users/" + userId + "/donations", newDonation)
      .then(() => {
        alert("Your donation has been added.")
        setAmount("");
        setComment("");
        setErrors("");
        setIsAnonymous(false)
      })
      .catch((error) => {
          console.error(error);
    });
};


    return (
      <div>
      {fundraising ? (
        <React.Fragment>
          <h2 style={{ marginTop: "20px"}}>{fundraising.name}</h2>
          <p><b>Goal:</b> {fundraising.goal}</p>

        <div>
          {fundraising.description.split('\n').map((line, index) => (
            <React.Fragment key={index}>
              {line}
              <br />
            </React.Fragment>
          ))}
        </div>
          <h4 style={{ marginTop: "40px", backgroundColor: "#F8F8FF", padding: "10px" }}>Interested in helping? Here you can submit your donation:</h4>
          <div className="user-data">
                <p style={{ marginTop: "20px", fontSize: 18, color: "#A9A9A9"   }}>You donate as <b>{loggedInUser.firstName} {loggedInUser.lastName}</b></p>
          </div>
          <DonateFormMolecule 
            onHandleChange={onHandleChange}
            onHandleSubmit={onHandleSubmit}
            errors={errors}
            amount={amount}
            comment={comment}
            isAnonymous={isAnonymous}
            loggedInUser={loggedInUser}
            />
        </React.Fragment>
      ) : (
        <p>Loading...</p>
      )}

    </div>
    );
};

export default DonateDetailsOrganism;