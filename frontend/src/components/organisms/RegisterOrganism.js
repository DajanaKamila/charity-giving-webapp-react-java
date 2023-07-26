import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import RegisterFormMolecule from "../molecules/login-register/RegisterFormMolecule"

const RegisterOrganism  = () => {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const onHandleChange = (e) => {
      const { name, value } = e.target;
      if (name === "username") setUsername(value);
      else if (name === "password") setPassword(value);
      else if (name === "confirmPassword") setConfirmPassword(value);
      else if (name === "firstName") setFirstName(value);
      else if (name === "lastName") setLastName(value);
      
  };

  const isPasswordValid = (password) => {
    const isValidLength = password.length >= 8 && password.length <= 20;
    const passwordRegex = /^(?=.*[!@#$%&*_?])(?=.*[A-Z])(?=.*\d)[A-Za-z\d!@#$%&*_?]+$/;
    const isValidFormat = passwordRegex.test(password);
    return isValidLength && isValidFormat;
  }

  const onHandleSubmit = (e) => {
      e.preventDefault();
      const errors = {};
      
      if (username === "") {
        errors.username = "Username is a required field.";
      }
      
      if (password === "") {
        errors.password = "Password is a required field.";
      }

      if (firstName === "") {
        errors.firstName = "First name is a required field.";
      }

      if (lastName === "") {
        errors.lastName = "Last name is a required field.";
      }

      if (password !== confirmPassword) {
        errors.confirmPassword = "Passwords do not match.";
      }

      if (!isPasswordValid(password)) {
        errors.password = "Password must be 8-20 characters long and contain at least one special character, one uppercase letter, and one digit.";
      }
      
      if (Object.keys(errors).length > 0) {
        setErrors(errors);
        return;
      }

      const newUser = {
        username: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
      };

      axios.post("http://localhost:8080/api/v1/users", newUser)
        .then(() => {
          alert("Your registration was successful. Now you can log in.")
          navigate("/login");
        })
        .catch((error) => {
          if (error.response && error.response.data) {
            setErrors({
              username: "Username already exists. Please choose a different one.",
            });
          }
      });
  };

  return (
      <RegisterFormMolecule
        onHandleChange={onHandleChange}
        onHandleSubmit={onHandleSubmit}
        firstName={firstName}
        lastName={lastName}
        username={username}
        password={password}
        confirmPassword={confirmPassword}
        errors={errors}>
      </RegisterFormMolecule>
    );
}

export default RegisterOrganism;