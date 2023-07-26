import React from "react";
import { Link } from "react-router-dom";
import TogglerAtom from "../atoms/TogglerAtom";

const LowerHeaderMolecule = ({brand}) => {

  return (

<nav className="navbar navbar-expand-lg custom-background-navbar">
  <div className="container-fluid">
    <div className="empty-space fs-3"></div>
    {/* <Link className="navbar-brand fs-3" to="/homepage">{brand}</Link> */}
    <TogglerAtom />

    <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav fs-6">
      <li className="nav-item">
          <Link className="nav-link" to="/homepage">All fundraisings</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/homepage">Create</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/homepage">How it works?</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/homepage">About us</Link>
        </li>
      </ul>
    </div>

  </div>
</nav>

  );
};

export default LowerHeaderMolecule;