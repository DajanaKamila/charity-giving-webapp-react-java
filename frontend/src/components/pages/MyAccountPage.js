import LoggedHeaderMolecule from "../molecules/LoggedHeaderMolecule";

const MyAccountPage = ({handleLogout}) => {
    return (
    <div>
        <LoggedHeaderMolecule brand="Charity World" handleLogout={handleLogout}></LoggedHeaderMolecule>
        <h1>This will be my account page</h1>
    </div>
    );

  };
  
  export default MyAccountPage;