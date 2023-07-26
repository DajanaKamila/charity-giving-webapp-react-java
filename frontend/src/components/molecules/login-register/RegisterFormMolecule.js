import React from "react";

const RegisterFormMolecule = ({ onHandleSubmit, onHandleChange, firstName, lastName, username, password, confirmPassword, errors }) => {
  return (
    <form className="row g-3 mt-2" onSubmit={onHandleSubmit}>
    <h3>Welcome to the Charity World! Sign up if you want to be a part of our team! </h3>
    <div className="col-md-6">
        <label for="input-first-name" className="form-label">First name</label>
        <input type="text" className="form-control" id="input-first-name" name="firstName" value={firstName} onChange={onHandleChange}/>
        {errors.firstName && <div className="text-danger">{errors.firstName}</div>}
    </div>
    <div className="col-md-6">
        <label for="input-last-name" className="form-label">Last name</label>
        <input type="text" className="form-control" id="input-last-name" name="lastName" value={lastName} onChange={onHandleChange}/>
        {errors.lastName && <div className="text-danger">{errors.lastName}</div>}
    </div>
    <div className="col-12">
        <label for="input-username" className="form-label">Username</label>
        <input type="text" className="form-control" id="input-username" name="username" value={username} onChange={onHandleChange}/>
        {errors.username && <div className="text-danger">{errors.username}</div>}    
    </div>
    <div className="col-md-6">
        <label for="inputPassword" className="form-label">Password</label>
        <input type="password" className="form-control" id="input-password" name="password" value={password} onChange={onHandleChange}/>
        {errors.password && <div className="text-danger">{errors.password}</div>}
        <p className="password-info">
          Password must be 8-20 characters long and contain at least one special character, one uppercase letter, and one digit.
        </p>
    </div>
    <div className="col-md-6">
        <label for="inputPassword" className="form-label">Confirm password</label>
        <input type="password" className="form-control" id="input-confirm-password" name="confirmPassword" value={confirmPassword} onChange={onHandleChange}/>
        {errors.confirmPassword && <div className="text-danger">{errors.confirmPassword}</div>}
    </div>
    <div className="col-12">
        <button type="submit" className="btn btn-outline-secondary btn-wide mt-4">Register</button>
    </div>
    </form>
  );
};

export default RegisterFormMolecule;