// import React from 'react';
// import './App.css'; // Import CSS file

// const App = () => {
//   return (
//     <div className="container">
//       <div className="navbar">
//         <div className="user">
//           <img src="https://hmp.me/d4pv" alt="Profile" className="profile-picture" />
//           <span className="username">Suzan Ayesh</span>
//         </div>
//         <button className="edit-profile-button">Edit Profile</button>
//       </div>
//       <div className="product-container">
//         <div className="product">
//           <div className="product-image">
//             <img src="https://hmp.me/d4pv" alt="Product 1" />
//             <div className="overlay">
//               <div className="basket-icon"></div>
//               <div className="share-icon"></div>
//             </div>
//           </div>
//           <div className="product-info">
//             <div className="product-details">
//               <span className="product-name">Product 1</span>
//               <span className="product-price">$10</span>
//             </div>
//             <div className="product-actions">
//               <span className='basket-icon'>{'https://hmp.me/d4py'}</span>
//               <span className='share-icon'>{'https://hmp.me/d4pz'}</span>
//             </div>
//           </div>
//         </div>
//         {/* Repeat the above structure for other products */}
//       </div>
//     </div>
//   );
// };

// export default App;
import React from 'react';
import UserProfile from './UserProfile';
import './App.css';

const App = () => {
  return (
    <div className="app">
      <UserProfile />
    </div>
  );
};

export default App;
