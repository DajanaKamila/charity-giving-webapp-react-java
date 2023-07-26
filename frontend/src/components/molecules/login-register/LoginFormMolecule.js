import React from "react";

const LoginFormMolecule = ({ onHandleSubmit, onHandleChange, errors, username, password }) => {
  return (
    <div>
        <form className="container g-3 mt-2" onSubmit={onHandleSubmit}>
        <h3>Please type your username and password</h3>
        {errors && <div className="alert alert-danger">{errors}</div>}
        <div className="row mt-3">
            <div className="col-md-4">
                <label for="input-username" className="form-label">Username</label>
                <input type="text" className="form-control" id="input-username" name="username" value={username} onChange={onHandleChange}/>
                {/* {errors.username && <div className="text-danger">{errors.username}</div>}     */}
            </div>
        </div>
        <div className="row">
            <div className="col-md-4">
                <label for="input-password" className="form-label">Password</label>
                <input type="password" className="form-control" id="input-password" name="password" value={password} onChange={onHandleChange}/>
                {/* {errors.password && <div className="text-danger">{errors.password}</div>} */}
        </div>
        </div>
        <div className="col-12">
            <button type="submit" className="btn btn-outline-secondary btn-wide mt-4">Sign in</button>
        </div>
        </form>
    </div>
  );
};

export default LoginFormMolecule;