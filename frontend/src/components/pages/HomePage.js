import LoggedHeaderMolecule from "../molecules/LoggedHeaderMolecule";
import LowerHeaderMolecule from "../molecules/LowerHeaderMolecule";

const HomePage = ({handleLogout}) => {
    return (
    <div>
    <LoggedHeaderMolecule brand="Charity World" handleLogout={handleLogout}></LoggedHeaderMolecule>
    <LowerHeaderMolecule></LowerHeaderMolecule>
    <h1>This will be a home page</h1>
    </div>
    );

  };
  
  export default HomePage;