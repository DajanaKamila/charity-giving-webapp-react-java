import UnloggedHeaderMolecule from "../../molecules/UnloggedHeaderMolecule";
import LoginOrganism from "../../organisms/LoginOrganism";

const LoginPage = ({handleLogin}) => {
    return (
    <div>
      <UnloggedHeaderMolecule brand="Charity World"></UnloggedHeaderMolecule>
      <LoginOrganism handleLogin={handleLogin}></LoginOrganism>
    </div>
    );

  };
  
  export default LoginPage;