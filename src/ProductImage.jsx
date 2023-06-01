import React from 'react';

const ProductImage = ({ src, alt }) => {
  return <img src={src} alt={alt} className="product-image" />;
};

export default ProductImage;
