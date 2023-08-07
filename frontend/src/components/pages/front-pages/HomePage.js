import LoggedHeaderMolecule from "../../molecules/headers/LoggedHeaderMolecule";
import LowerHeaderMolecule from "../../molecules/headers/LowerHeaderMolecule";
import { Outlet } from "react-router-dom";

const HomePage = ({handleLogout}) => {
    return (
    <div>
      <LoggedHeaderMolecule brand="Charity World" handleLogout={handleLogout}></LoggedHeaderMolecule>
      <LowerHeaderMolecule></LowerHeaderMolecule>
      <Outlet />
    </div>
    );

  };
  
  export default HomePage;