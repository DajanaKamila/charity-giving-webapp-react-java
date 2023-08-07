import React from "react";
import { Link } from "react-router-dom";
import TogglerAtom from "../../atoms/TogglerAtom";

const LoggedHeaderMolecule = ({brand, handleLogout}) => {

  return (

<nav className="navbar navbar-expand-lg background-navbar">
  <div className="container-fluid">
    <Link className="navbar-brand fs-3" to="/home/main">{brand}</Link>
    <TogglerAtom />

    <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav ms-auto mb-2 mb-lg-0 fs-6">
      <li className="nav-item btn-nav">
          <Link className="nav-link" to="/myaccount">My account</Link>
        </li>
        <li className="nav-item btn-nav">
          <Link className="nav-link" to="/" onClick={handleLogout}>Log out</Link>
        </li>
      </ul>
    </div>

  </div>
</nav>

  );
};

export default LoggedHeaderMolecule;
