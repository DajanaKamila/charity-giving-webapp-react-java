import React from "react";
import { Link } from "react-router-dom";
import TogglerAtom from "../atoms/TogglerAtom";

const LowerHeaderMolecule = () => {

  return (
    <nav className="navbar navbar-expand-lg background-navbar-lower">
      <div className="container-fluid">
        <div className="empty-space fs-3"></div>
        <TogglerAtom />

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav fs-6">
          <li className="nav-item btn-nav">
              <Link className="nav-link" to="/home/main">Donate</Link>
            </li>
            <li className="nav-item btn-nav">
              <Link className="nav-link" to="/home/main">Create new</Link>
            </li>
            <li className="nav-item btn-nav">
              <Link className="nav-link" to="/home/main">How it works?</Link>
            </li>
            <li className="nav-item btn-nav">
              <Link className="nav-link" to="/homepage">About us</Link>
            </li>
            <li className="nav-item btn-nav">
              <Link className="nav-link" to="/homepage">Sth</Link>
            </li>
          </ul>
        </div>

      </div>
    </nav>

  );
};

export default LowerHeaderMolecule;