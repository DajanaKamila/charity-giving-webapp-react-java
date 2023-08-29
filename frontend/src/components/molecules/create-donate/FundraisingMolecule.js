import React, {useState} from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const FundraisingMolecule = ({ fundraising}) => {
    const [isShowing, setIsShowing] = useState(false);

    const showDetails = () => {
      setIsShowing(!isShowing);
    };

    const descriptionLines = fundraising.description.split('\n');

    return (
        <div className="card mb-1">
          <div
            className="card-header bg-light"
            onClick={showDetails}
            style={{ cursor: "pointer" }}
          >
            {fundraising.name}
          </div>
          {isShowing && (
            <div className="card-body">
              <ul className="list-group">
                <li className="list-group-item"><b>Goal:</b> {fundraising.goal}</li>
                <li className="list-group-item">
                  {descriptionLines.map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
                </li>
                <li className="nav-item btn-standard">
                    {/* <Link className="nav-link" to="/home/donate/info/${fundraising.id}">See more</Link> */}
                    <Link className="nav-link" to={`/home/donate/info/${fundraising.id}`}>See more</Link>
                </li>
              </ul>
            </div>
          )}
        </div>
      );

};

FundraisingMolecule.defaultProps = {
  fundraising: {
    name: "not defined",
    goal: "not defined",
    description: "not defined",
  },
};

FundraisingMolecule.propTypes = {
  fundraising: PropTypes.shape({
    name: PropTypes.string.isRequired,
    goal: PropTypes.number.isRequired,
    description: PropTypes.string.isRequired,
  }).isRequired,
};

export default FundraisingMolecule;



