import React from "react";
import image from "../../icons/charity.png";

const AboutUsMolecule = () => {
  return (
    <div>
<div className="row">
        <div className="col-md-3 my-4"
          style={{
            background: `url(${image})`,
            backgroundSize: "contain",
            backgroundRepeat: "no-repeat",
            backgroundPosition: "center",
            minHeight: "150px",
          }}>
        </div>

        <div className="col-md-9 my-4">
          <h4>Welcome to the <b>Charity World</b>! Make yourself at home!</h4>
          <h5 className="welcome-header mt-3">Who are we? </h5>
            <p className="welcome-text">Charity World is a non-profit website associating people in need and people willing to 
            share good. We create a world full of selfless help, warmth and support. We believe that together we can change a lot.</p>
            <h5 className="welcome-header mt-3">How does Charity World work?</h5>
            <p className="welcome-text"> Each registered user has the ability to make donations to fundraisings, as well as to set up 
            their own fundraisings. Everything is completely <b>free</b>, and the profiles of people creating fundraisings are verified, 
            ensuring 100% security. Don't hesitate and sign up today!</p>
        </div>
      </div>
    </div>
  );
};

export default AboutUsMolecule;