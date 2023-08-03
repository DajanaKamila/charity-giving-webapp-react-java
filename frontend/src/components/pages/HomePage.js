import LoggedHeaderMolecule from "../molecules/LoggedHeaderMolecule";
import LowerHeaderMolecule from "../molecules/LowerHeaderMolecule";
import { Outlet } from "react-router-dom";

const HomePage = ({handleLogout}) => {
    return (
    <div>
      <LoggedHeaderMolecule brand="Charity World" handleLogout={handleLogout}></LoggedHeaderMolecule>
      <LowerHeaderMolecule></LowerHeaderMolecule>
      <h1>This will be a home page</h1>
      <Outlet />
    </div>
    );

  };
  
  export default HomePage;