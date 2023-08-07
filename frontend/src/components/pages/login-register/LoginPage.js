import UnloggedHeaderMolecule from "../../molecules/headers/UnloggedHeaderMolecule";
import LoginOrganism from "../../organisms/login-register/LoginOrganism";

const LoginPage = ({handleLogin}) => {
    return (
    <div>
      <UnloggedHeaderMolecule brand="Charity World"></UnloggedHeaderMolecule>
      <LoginOrganism handleLogin={handleLogin}></LoginOrganism>
    </div>
    );

  };
  
  export default LoginPage;