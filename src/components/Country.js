import React, { useEffect } from "react";
import { useParams } from "react-router";
import { useAtom } from "jotai";

import state from "../stateManager";
import Index from "./Index";

const Country = () => {
    const { country } = useParams();
    const [, setCountry] = useAtom(state.currentCountryAtom);

    useEffect(() => {
        setCountry(country);
    });

    return <Index />;
};

export default Country;
