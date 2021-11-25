import React, { useEffect } from "react";
import { useParams } from "react-router";
import { useAtom } from "jotai";

import state from "../stateManager";
import Index from "./Index";

const Country = () => {
    const { genre } = useParams();
    const [, setGenre] = useAtom(state.currentGenreAtom);

    useEffect(() => {
        setGenre(genre);
    });

    return <Index />;
};

export default Country;
