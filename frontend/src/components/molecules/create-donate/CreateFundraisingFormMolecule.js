import React from "react";

const CreateFundraisingFormMolecule = ({onHandleChange, onHandleSubmit, name, goal, description, errors, agreedToTerms}) => {
    
    return (
        <form className="row g-3 mt-2" onSubmit={onHandleSubmit}>
            <h5>Create new fundraising</h5>
            <p>When setting up a new fundraiser, remember to accurately describe its purpose and cost. 
                Fundraisings with such data achieve their goals 3.2 times more often!</p>
            <div className="col-12">
                <label for="input-name" className="form-label">Name</label>
                <input type="text" className="form-control" id="input-name" name="name" value={name} onChange={onHandleChange}/>
                {errors.name && <div className="text-danger">{errors.name}</div>}
            </div>
            <div className="col-12">
                <label for="input-goal" className="form-label">Goal</label>
                <input type="number" className="form-control" id="input-goal" name="goal" value={goal} onChange={onHandleChange}/>
                {errors.goal && <div className="text-danger">{errors.goal}</div>}
            </div>
            <div className="col-12">
                <label for="input-description" className="form-label">Description</label>
                <input type="text" className="form-control" id="input-description" name="description" value={description} onChange={onHandleChange}/>
                {errors.description && <div className="text-danger">{errors.description}</div>}    
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="agreedToTermCheckbox" name="agreedToTerms" checked={agreedToTerms} onChange={onHandleChange}/>
                <label class="form-check-label" for="agreedToTermsCheckbox">Agree to terms and conditions</label>
                {errors.agreedToTerms && <div className="text-danger">{errors.agreedToTerms}</div>}   
            </div>
            <div className="col-12">
                <button type="submit" className="btn btn-outline-secondary btn-wide mt-4">Create fundraising</button>
            </div>

        </form>
      );

  };

 
  
  export default CreateFundraisingFormMolecule;


