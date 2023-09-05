import React from "react";

const DonateInfoMolecule = ({fundraising}) => {

    return (
      <div>
      {fundraising ? (
        <React.Fragment>
          <hr/>
          <h2 style={{ marginTop: "20px"}}>{fundraising.name}</h2>
          <p><b>Goal:</b> {fundraising.goal}</p>

          <div>
            {fundraising.description ? (
              fundraising.description.split('\n').map((line, index) => (
                <React.Fragment key={index}>
                  {line}
                  <br />
                </React.Fragment>
              ))
            ) : (
              <p>No description available.</p>
            )}
          </div>
        </React.Fragment>
      ) : (
        <p>Loading...</p>
      )}
      <hr />
    </div>
    );
};

export default DonateInfoMolecule;