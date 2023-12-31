import React, {useState} from "react";
import DonateFormMolecule from "../../molecules/create-donate/DonateFormMolecule"
import DonateHowToMolecule from "../../molecules/create-donate/DonateHowToMolecule"
import axios from "axios";

const DonateMoneyOrganism = ({fundraising}) => {

    const [isAnonymous, setIsAnonymous] = useState(false);
    const [amount, setAmount] = useState("");
    const [comment, setComment] = useState("");
    const [errors, setErrors] = useState({});

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
    const userId = JSON.parse(localStorage.getItem("userId"));

    const onHandleChange = (e) => {
        const { name, value, type, checked } = e.target;
        if (type === "checkbox") setIsAnonymous(checked);
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
        amount: amount,
        comment: comment,
        isAnonymous: isAnonymous,
        donor: {loggedInUser},
      };

      console.log(fundraising);

      axios.post(`http://localhost:8080/api/v1/users/${userId}/${fundraising.id}/donations`, newDonation)
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

  return (<div>
        <DonateHowToMolecule />
        <DonateFormMolecule 
          onHandleChange={onHandleChange}
          onHandleSubmit={onHandleSubmit}
          errors={errors}
          amount={amount}
          comment={comment}
          isAnonymous={isAnonymous}
          loggedInUser={loggedInUser}
          />
  </div>);


};

export default DonateMoneyOrganism;