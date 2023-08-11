import React from "react";

const TogglerAtom = ({ targetId }) => {
  return (
    <button className="navbar-toggler" 
            type="button" 
            data-bs-toggle="collapse" 
            data-bs-target={`#${targetId}`}
            aria-controls="navbarSupportedContent" 
            aria-expanded="false" 
            aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
  );
};

export default TogglerAtom;