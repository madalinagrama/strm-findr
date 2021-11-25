import React from "react";
import { useAtom } from "jotai";

import state from "../stateManager";
import Card from "./Card";

const Index = ({ onlyFavorites = false }) => {
    const [cards] = useAtom(state.cardsAtom);
    const [favorites] = useAtom(state.favoritesAtom);

    return (
        <div className="row">
            {cards
                .filter((c) =>
                    onlyFavorites ? favorites.includes(c.id) : true
                )
                .map((card, i) => (
                    <Card key={i} {...card} />
                ))}
        </div>
    );
};

export default Index;
