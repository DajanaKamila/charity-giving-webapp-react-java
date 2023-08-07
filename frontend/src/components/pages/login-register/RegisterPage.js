import UnloggedHeaderMolecule from "../../molecules/headers/UnloggedHeaderMolecule";
import RegisterOrganism from "../../organisms/login-register/RegisterOrganism";

const RegisterPage = () => {
    return (
    <div>
      <UnloggedHeaderMolecule brand="Charity World"></UnloggedHeaderMolecule>
      <RegisterOrganism></RegisterOrganism>
    </div>
    );

  };
  
  export default RegisterPage;