import axios from "axios";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import LoginFormMolecule from "../../molecules/login-register/LoginFormMolecule";

const LoginOrganism  = ({handleLogin}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const onHandleChange = (e) => {
        const { name, value } = e.target;
        if (name === "username") setUsername(value);
        else if (name === "password") setPassword(value);      
      };

   const onHandleSubmit = async (e) => {
        e.preventDefault();

        const loginUser = {
            username: username,
            password: password,
        };

        try {
            const response = await axios.post("http://localhost:8080/api/v1/users/login", loginUser );
        
            if (response.status === 200) {
                handleLogin(response.data, response.data.id);
                navigate("/home/donate");
            }
        } catch (error) {
                if (error.response && error.response.data) {
                setError("Invalid username or password");
            }
        }
    }

    return (
           <LoginFormMolecule 
                onHandleChange={onHandleChange} 
                onHandleSubmit={onHandleSubmit}
                errors={error}
                username={username}
                password={password}>
            </LoginFormMolecule> 
    ); 
}

export default LoginOrganism;