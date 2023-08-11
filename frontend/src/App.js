import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React, { useState, useEffect } from "react";

import WelcomePage from "./components/pages/front-pages/WelcomePage";
import RegisterPage from "./components/pages/login-register/RegisterPage";
import LoginPage from "./components/pages/login-register/LoginPage";
import HomePage from "./components/pages/front-pages/HomePage";
import PageNotFound from "./components/pages/info-pages/PageNotFound";
import MyAccountPage from "./components/pages/account/MyAccountPage";
import PageToDo from "./components/pages/PageToDo";
import TestPage from "./components/pages/TestPage";
import CreateFundraisingPage from "./components/pages/create-donate/CreateFundraisingPage";
import DonatePage from "./components/pages/create-donate/DonatePage";
import DonateDetailsOrganism from "./components/organisms/create-donate/DonateDetailsOrganism"

function App() {
  const [user, setUser] = useState(null);
  const [userId, setUserId] = useState(null);

  const handleLogin = (loggedInUser, id) => {
    setUser(loggedInUser);
    setUserId(id);

    localStorage.setItem("loggedInUser", JSON.stringify(loggedInUser));
    localStorage.setItem("userId", id);
  };

  const handleLogout = () => {
    setUser(null);
    setUserId(null);

    localStorage.setItem("loggedInUser", JSON.stringify(null));
    localStorage.setItem("userId", null);
  }

  const checkUserLogin = () => {
    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
    const userId = localStorage.getItem("userId");
  
    if (loggedInUser && userId) {
      setUser(loggedInUser);
      setUserId(userId);
    }
  };

  useEffect(() => {
    checkUserLogin();
  }, []);

  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage handleLogin={handleLogin}/>} />
          <Route path="/home" element={userId && <HomePage  handleLogout={handleLogout}/>} >
            <Route path="main" element={<TestPage />} /> 
            <Route path="create" element={<CreateFundraisingPage />} />
            <Route path="donate" element={<DonatePage />} />
            <Route path="donate/info/:fundraisingId" element={<DonateDetailsOrganism />} />
          </Route>
          
          <Route path="/*" element={<PageNotFound />} />
          <Route path="/myaccount" element={userId && <MyAccountPage />} />
          <Route path="/todo" element={userId && <PageToDo/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
