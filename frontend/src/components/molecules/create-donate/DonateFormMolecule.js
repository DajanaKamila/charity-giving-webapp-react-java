import React from "react";

const DonateFormMolecule = ({onHandleSubmit, onHandleChange, errors, amount, comment, isAnonymous}) => {
    return (
        <form className="row g-3 mt-2" onSubmit={onHandleSubmit}>
            <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="isAnonymousCheckbox" name="isAnonymous" checked={isAnonymous} onChange={onHandleChange}/>
                    <label className="form-check-label" htmlFor="isAnonymousCheckbox">I want to donate anonymously</label> 
            </div>
            <div className="col-md-6">
                <label htmlFor="input-amount" className="form-label">Amount</label>
                <input type="number" className="form-control" id="input-amount" name="amount" value={amount} onChange={onHandleChange}/>
                {errors.amount && <div className="text-danger">{errors.amount}</div>}
            </div>
            <div className="col-12">
                    <label htmlFor="comment">Comment</label>
                    <textarea className="form-control" id="comment" rows="3" name="comment" value={comment} onChange={onHandleChange}/>
            </div>
            <div className="col-12">
                <button type="submit" className="btn btn-outline-secondary btn-wide mt-4">Donate</button>
            </div>
        </form>
      );
};

export default DonateFormMolecule;