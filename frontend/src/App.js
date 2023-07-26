import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React, { useState } from "react";

import WelcomePage from "./components/pages/WelcomePage";
import RegisterPage from "./components/pages/login-register/RegisterPage";
import LoginPage from "./components/pages/login-register/LoginPage";
import HomePage from "./components/pages/HomePage";
import PageNotFound from "./components/pages/PageNotFound";
import MyAccountPage from "./components/pages/MyAccountPage";
import PageToDo from "./components/pages/PageToDo";

function App() {
  const [user, setUser] = useState(null);
  const [userId, setUserId] = useState(null);
  const userIdString = String(userId);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = (loggedInUser, id) => {
    setUser(loggedInUser);
    setUserId(id);
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUser(null);
    setUserId(null);
  }

  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage handleLogin={handleLogin}/>} />
          <Route path="/homepage" element={userId && <HomePage  handleLogout={handleLogout}/>} />
          <Route path="/*" element={<PageNotFound />} />
          <Route path="/myaccount" element={<MyAccountPage />} />
          <Route path="/todo" element={<PageToDo/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
