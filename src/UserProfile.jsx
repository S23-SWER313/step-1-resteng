import React from 'react';
import './UserProfile.css';

const UserProfile = () => {
  return (
    <div className="profile">
      <nav className="navbar">
        <div className="user-info">
          <div className="profile-pic"></div>
          <h3>User Name</h3>
        </div>
        <button className="edit-profile-btn">Edit Profile</button>
      </nav>
      <div>
      <h1> Products I shared:</h1>

      </div>
      <div className="products">
        {Array(9).fill().map((_, index) => (
          <div className="product-rectangular" key={index}>
            <div className="product-square">
                
              <div className="product-image"></div>
              
              <h4>Product Name</h4>
              <p>Price</p>
              <div className="icons">
                <div className="basket-icon"></div>
                <div className="share-icon"></div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserProfile;
