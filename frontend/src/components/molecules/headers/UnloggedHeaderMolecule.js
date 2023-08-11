import React from "react";
import { Link } from "react-router-dom";
import TogglerAtom from "../../atoms/TogglerAtom";

const UnloggedHeaderMolecule = (props) => {
  const { brand } = props;
  return (

<nav className="navbar navbar-expand-lg background-navbar">
  <div className="container-fluid">
    <Link className="navbar-brand fs-3" to="/">{brand}</Link>
    <TogglerAtom />

    <div className="collapse navbar-collapse" id="unloggedNavbar">
      <ul className="navbar-nav ms-auto mb-2 mb-lg-0 fs-6">
        <li className="nav-item">
          <Link className="nav-link" to="/login">Login</Link>
        </li>
        <li className="nav-item">
        <Link className="nav-link" to="/register">Register</Link>
        </li>
      </ul>
    </div>

  </div>
</nav>

  );
};

export default UnloggedHeaderMolecule;
