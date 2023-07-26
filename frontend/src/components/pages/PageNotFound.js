import React from "react";

const PageNotFound = () => {
  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="alert text-center">
        <h1 className="display-4">404 Page Not Found</h1>
        <p className="lead">Sorry, page does not exist.</p>
      </div>
    </div>
  );
};

export default PageNotFound;