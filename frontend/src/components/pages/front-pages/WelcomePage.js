import React from "react";
import UnloggedHeaderMolecule from "../../molecules/headers/UnloggedHeaderMolecule";
import AboutUsMolecule from "../../molecules/AboutUsMolecule";

const WelcomePage = () => {
  return (
    <div>
      <UnloggedHeaderMolecule brand="Charity World"></UnloggedHeaderMolecule>
      <AboutUsMolecule></AboutUsMolecule>
    </div>
  ); 


};

export default WelcomePage;
