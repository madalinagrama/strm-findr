import React, { useState, useEffect } from "react";
import ImageNotFound from "../img/ImageNotFound.png";
import { useAtom } from "jotai";
import state from "../stateManager";

import { AiOutlineHeart, AiFillHeart } from "react-icons/ai";
import axios from "axios";
import authHeader from "./auth/components/services/auth-header";

const Card = ({
    image = ImageNotFound,
    title = "",
    overview = "",
    id = "",
    service = "",
    countries = "",
    imdb = "",
    handleFavorites = false,
}) => {
    const [currentUser] = useAtom(state.currentUserAtom);
    const [favorites, setFavorites] = useState([]);

    useEffect(() => {
        axios
            .get(
                process.env.REACT_APP_BASE_URL +
                    "/user/" +
                    currentUser.id +
                    "/favorites",
                {
                    headers: authHeader(),
                }
            )
            .then((data) => {
                setFavorites(data.data.map((e) => e.id));
            })
            .catch((e) => {
                console.error(e);
            });
    }, []);

    // console.log({ image, title, overview, id, service, countries, imdb });
    // will get user's favorites
    const favoriteHandler = () => {
        axios
            .post(
                process.env.REACT_APP_BASE_URL +
                    "/user/" +
                    currentUser.id +
                    "/favorites/",
                {
                    headers: authHeader(),
                    user_id: currentUser.id,
                    movie_id: id,
                }
            )
            .then((data) => {
                setFavorites([...favorites, id]);
            })
            .catch((e) => console.error(e));
    };

    let classes = "btn";
    if (favorites.includes(id)) {
        classes += " btn-danger";
    } else {
        classes += " btn-light";
    }

    return (
        <div className="col-sm-12 col-md-6 col-lg-4 mb-4">
            <div className="bg-dark border card text-white">
                <img src={image} className="card-img-top" alt={title} />
                <div className="card-body">
                    <h5 className="card-title">
                        <div className="title">{title}</div>
                        {currentUser?.id && (
                            <button
                                data-movie={id}
                                className="btn btn-light mx-5"
                                onClick={(e) => favoriteHandler()}
                            >
                                {favorites.includes(id) ? (
                                    <AiFillHeart className="fav" />
                                ) : (
                                    <AiOutlineHeart className="fav" />
                                )}
                            </button>
                        )}
                    </h5>
                    <h6 className="card-subtitle mb-2 text-danger">
                        Available on {service.toUpperCase()} in {countries}
                    </h6>
                    <p className="card-text">{overview}</p>
                    <a
                        href={`https://www.imdb.com/title/${imdb}`}
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
