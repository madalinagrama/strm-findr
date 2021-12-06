import React, { useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { atom, useAtom } from "jotai";
import axios from "axios";
import state from "./stateManager";

import Header from "./components/Header";
import Index from "./components";
import Country from "./components/Country";
import Genre from "./components/Genre";
import About from "./components/About";
import Spinner from "./components/Spinner";
import Register from "./components/Register";
import Login from "./components/Login";
import UserProfile from "./components/UserProfile";
import authHeader from "./components/auth/components/services/auth-header";

import "./App.css";
// import UserProfile from "./components/user/UserProfile";

export const userAtom = atom(true);
export const tokenAtom = atom(false);

const App = () => {
    const [keyword] = useAtom(state.currentKeywordAtom);
    const [, setCards] = useAtom(state.cardsAtom);
    const [loading, setLoading] = useAtom(state.loadingAtom);
    const [currentUser] = useAtom(state.currentUserAtom);
    const [, setFavorites] = useAtom(state.favoritesAtom);

    useEffect(() => {
        const cardsLoader = () => {
            let cards = [];

            axios
                .get(process.env.REACT_APP_BASE_URL + "/movies")
                .then((data) => {
                    data.data.forEach((r) => {
                        cards.push({
                            image: r.posterURL,
                            title: r.originalTitle,
                            overview: r.overview.slice(0, 150) + "...",
                            imdb: r.imdbID,
                            service: r.streamingInfo,
                            countries: r.countries,
                            id: r.id,
                        });
                    });

                    if (cards.length) {
                        if (keyword) {
                            cards = cards.filter((c) =>
                                c.title.toLowerCase().includes(keyword)
                            );
                        }
                        setCards(cards);
                        setLoading(false);
                    }
                })
                .catch((e) => console.error(e));

            if (!!currentUser) {
                axios
                    .get(
                        process.env.REACT_APP_BASE_URL +
                            "/user/" +
                            currentUser.id +
                            "/favorites",
                        { headers: authHeader() }
                    )
                    .then((data) => {
                        setFavorites(data.data);
                    })
                    .catch((e) => console.error(e));
            }
        };

        cardsLoader();
    }, [keyword]);

    return (
        <Router>
            <Header />

            {loading && <Spinner />}

            {!loading && (
                <div className="container mt-3">
                    <Switch>
                        <Route path="/country/:country">
                            <Country />
                        </Route>

                        <Route path="/genre/:genre">
                            <Genre />
                        </Route>

                        <Route path="/about">
                            <About />
                        </Route>

                        <Route path="/register">
                            <Register />
                        </Route>

                        <Route path="/login">
                            <Login />
                        </Route>

                        <Route exact path="/profile/:username">
                            <UserProfile />
                        </Route>

                        <Route exact path="/">
                            <Index />
                        </Route>
                    </Switch>
                </div>
            )}
        </Router>
    );
};

export default App;
