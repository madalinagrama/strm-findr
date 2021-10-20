import React from "react";
import ImageNotFound from "../img/ImageNotFound.png";

const Card = ({ image, title, overview, id, service, countries }) => {
    const picture = image || ImageNotFound;

    return (
        <div className="col-sm-12 col-md-6 col-lg-4 mb-4">
            <div className="bg-dark border card text-white">
                <img src={picture} className="card-img-top" alt={title} />
                <div className="card-body">
                    <h5 className="card-title">{title}</h5>
                    <h6 className="card-subtitle mb-2 text-danger">
                        Available on {service.toUpperCase()} in {countries}
                    </h6>
                    <p className="card-text">{overview}</p>
                    <a
                        href={`https://www.imdb.com/title/${id}`}
                        className="btn btn-danger"
                    >
                        More details on IMDB
                    </a>
                </div>
            </div>
        </div>
    );
};

export default Card;
