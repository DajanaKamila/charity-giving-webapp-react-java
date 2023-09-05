import React from "react";

const DonateHowToMolecule= () => {

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
    return (
        <div className="col-12">
            <div className="donate-info-center" style={{ textAlign: "center" }}>
                <h4 style={{ marginTop: "40px", backgroundColor: "#F8F8FF", padding: "10px", margin: "auto" }}>Interested in helping? Here you can submit your donation:</h4>
                <div className="user-data">
                    <p style={{ marginTop: "20px", fontSize: 18, color: "#A9A9A9"   }}>You donate as <b>{loggedInUser.username} </b></p>
                </div>    
            </div>
            <div className="donate-instructions">
                <p>Helping is a beautiful gift of heart. We're happy you want support one of our funraisings. To make it safely, please fill the form. Other users will see 
                    your comment and your donation. If you want to be anonimous, please choose this option in the form. </p>
            </div>
        </div>
    );

};

export default DonateHowToMolecule;