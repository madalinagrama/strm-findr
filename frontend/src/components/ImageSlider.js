import React, { useState } from "react";
import sliderData from "./sliderData";
import { FaArrowAltCircleRight, FaArrowAltCircleLeft } from "react-icons/fa";

function ImageSlider() {
    const [current, SetCurrent] = useState(0);
    const length = sliderData.length;

    const nextSlide = () => {
        SetCurrent(current === length - 1 ? 0 : current + 1);
    };

    const prevSlide = () => {
        SetCurrent(current === 0 ? length - 1 : current - 1);
    };

    return (
        <section className="slider">
            <FaArrowAltCircleLeft
                className="arrow left-arrow"
                onClick={prevSlide}
            />
            <FaArrowAltCircleRight
                className="arrow right-arrow"
                onClick={nextSlide}
            />
            {sliderData.map((slide, index) => {
                return (
                    <div
                        className={index === current ? "slide active" : "slide"}
                        key={index}
                    >
                        {index === current && (
                            <img
                                src={slide}
                                alt="movie poster"
                                className="image"
                            />
                        )}
                    </div>
                );
            })}
        </section>
    );
}

export default ImageSlider;
