import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import CreateFundraisingFormMolecule from "../../molecules/create-donate/CreateFundraisingFormMolecule";

const CreateFundraisingOrganism = () => {

  const [name, setName] = useState("");
  const [goal, setGoal] = useState("");
  const [description, setDescription] = useState("");
  const [errors, setErrors] = useState({});
  const [agreedToTerms, setAgreedToTerms] = useState(false);
  const navigate = useNavigate();

  const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  const userId = JSON.parse(localStorage.getItem("userId"));

  const onHandleChange = (e) => {
      const { name, value, type, checked } = e.target;
      if (type === "checkbox") setAgreedToTerms(checked);
      else if (name === "name") setName(value);
      else if (name === "goal") setGoal(value);
      else if (name === "description") setDescription(value);
  };

  const onHandleSubmit = (e) => {
      e.preventDefault();
      const errors = {};
      
      if (name === "") {
        errors.name = "Name is a required field.";
      }
      
      if (goal === "") {
        errors.goal = "Goal is a required field.";
      }

      if (description === "") {
        errors.description = "Description is a required field.";
      }

      if (!agreedToTerms) {
        errors.agreedToTerms = "You must agree to continue."
      }
      
      if (Object.keys(errors).length > 0) {
        setErrors(errors);
        return;
      }

      const newFundraising = {
        name: name,
        goal: goal,
        description: description,
        founder: {loggedInUser},
        donations: null,
      };

      axios.post("http://localhost:8080/api/v1/users/" + userId + "/fundraisings", newFundraising)
        .then(() => {
          alert("Your fundraising has been added.")
          navigate("/home/donate");
        })
        .catch((error) => {
            console.error(error);
      });
  };

  return (
      <CreateFundraisingFormMolecule
        onHandleChange={onHandleChange}
        onHandleSubmit={onHandleSubmit}
        name={name}
        goal={goal}
        description={description}
        errors={errors}
        agreedToTerms={agreedToTerms}/>
    );
}

export default CreateFundraisingOrganism;