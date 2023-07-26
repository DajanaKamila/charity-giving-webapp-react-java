import React from "react";

const ImageAtom = ({ image, height, width, altText }) => {
  return (
    <img
      src={image}
      alt={altText}
      width={width}
      height={height}
      className="d-inline-block align-text-center mx-5"
    />
  );
};

export default ImageAtom;
