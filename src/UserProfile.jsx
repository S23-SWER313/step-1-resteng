import React, { useState } from 'react';
import './UserProfile.css';

const UserProfile = () => {
  const [editing, setEditing] = useState(false);
  const [userId, setUserId] = useState('123');
  const [firstName, setFirstName] = useState('John');
  const [lastName, setLastName] = useState('Doe');
  const [email, setEmail] = useState('johndoe@example.com');
  const [password, setPassword] = useState('');
  const [country, setCountry] = useState('United States');
  const [state, setState] = useState('California');
  const [city, setCity] = useState('San Francisco');
  const [address1, setAddress1] = useState('123 Main St');
  const [address2, setAddress2] = useState('');
  const [phone, setPhone] = useState('1234567890');
  const [showPassword, setShowPassword] = useState(false);

  const handleEditProfile = () => {
    setEditing(true);
  };

  const handleSaveProfile = () => {
    // Perform save operation, e.g., API call
    setEditing(false);
    setShowPassword(false);
  };

  return (
    <div className="user-profile">
      <div className="navbar">
        <div className="profile-picture"></div>
        <button
          className="edit-profile-button"
          onClick={handleEditProfile}
          disabled={editing}
        >
          Edit Profile
        </button>
      </div>
      <div className="profile-details">
        <div className="profile-field">
          <span className="field-label">User ID:</span>
          <span className="field-value">{userId}</span>
        </div>
        <div className="profile-field">
          <span className="field-label">First Name:</span>
          {editing ? (
            <input
              type="text"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          ) : (
            <span className="field-value">{firstName}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Last Name:</span>
          {editing ? (
            <input
              type="text"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          ) : (
            <span className="field-value">{lastName}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Email Address:</span>
          {editing ? (
            <input
              type="text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          ) : (
            <span className="field-value">{email}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Password:</span>
          {editing ? (
            <input
              type={showPassword ? 'text' : 'password'}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          ) : (
            <span className="field-value">{password}</span>
          )}
          {editing && (
            <button
              className="password-toggle-button"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? 'Hide' : 'Show'}
            </button>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Country:</span>
          {editing ? (
            <input
              type="text"
              value={country}
              onChange={(e) => setCountry(e.target.value)}
            />
          ) : (
            <span className="field-value">{country}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">State:</span>
          {editing ? (
            <input
              type="text"
              value={state}
              onChange={(e) => setState(e.target.value)}
            />
          ) : (
            <span className="field-value">{state}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">City:</span>
          {editing ? (
            <input
              type="text"
              value={city}
              onChange={(e) => setCity(e.target.value)}
            />
          ) : (
            <span className="field-value">{city}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Address 1:</span>
          {editing ? (
            <input
              type="text"
              value={address1}
              onChange={(e) => setAddress1(e.target.value)}
            />
          ) : (
            <span className="field-value">{address1}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Address 2:</span>
          {editing ? (
            <input
              type="text"
              value={address2}
              onChange={(e) => setAddress2(e.target.value)}
            />
          ) : (
            <span className="field-value">{address2}</span>
          )}
        </div>
        <div className="profile-field">
          <span className="field-label">Phone:</span>
          {editing ? (
            <input
              type="text"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
            />
          ) : (
            <span className="field-value">{phone}</span>
          )}
        </div>
      </div>
      <div className="profile-actions">
        {editing && (
          <button className="save-profile-button" onClick={handleSaveProfile}>
            Save Profile
          </button>
        )}
      </div>
    </div>
  );
};

export default UserProfile;

// import React from 'react';
// import './UserProfile.css';

// const UserProfile = () => {
//   return (
//     <div className="profile">
//       <nav className="navbar">
//         <div className="user-info">
//           <div className="profile-pic"></div>
//           <h3>User Name</h3>
//         </div>
//         <button className="edit-profile-btn">Edit Profile</button>
//       </nav>
//       <div>
//       <h1> Products I shared:</h1>

//       </div>
//       <div className="products">
//         {Array(20).fill().map((_, index) => (
//           <div className="product-rectangular" key={index}>
//             <div className="product-square">
                
//               <div className="product-image"></div>
              
//               <h4>Product Name</h4>
//               <p>Price</p>
//               <div className="icons">
//                 <div className="basket-icon"></div>
//                 <div className="share-icon"></div>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default UserProfile;
