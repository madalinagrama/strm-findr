import { atom } from "jotai";
import AuthService from "./components/auth/components/services/auth.service";

const genres = [
    { id: 28, name: "Action" },
    { id: 7, name: "Adult" },
    { id: 12, name: "Adventure" },
    { id: 16, name: "Animation" },
    { id: 1, name: "Biography" },
    { id: 35, name: "Comedy" },
    { id: 80, name: "Crime" },
    { id: 99, name: "Documentary" },
    { id: 18, name: "Drama" },
    { id: 10751, name: "Family" },
    { id: 14, name: "Fantasy" },
    { id: 2, name: "Film Noir" },
    { id: 3, name: "Game Show" },
    { id: 36, name: "History" },
    { id: 27, name: "Horror" },
    { id: 10402, name: "Music" },
    { id: 4, name: "Musical" },
    { id: 9648, name: "Mystery" },
    { id: 10763, name: "News" },
    { id: 10764, name: "Reality" },
    { id: 10749, name: "Romance" },
    { id: 878, name: "Science Fiction" },
    { id: 6, name: "Short" },
    { id: 5, name: "Sport" },
    { id: 10767, name: "Talk Show" },
    { id: 53, name: "Thriller" },
    { id: 10752, name: "War" },
    { id: 37, name: "Western" },
];

const countries = [
    { name: "AT", id: "at" },
    { name: "AU", id: "au" },
    { name: "BE", id: "be" },
    { name: "CA", id: "ca" },
    { name: "DE", id: "de" },
    { name: "ES", id: "es" },
    { name: "FR", id: "fr" },
    { name: "GB", id: "gb" },
    { name: "JP", id: "jp" },
    { name: "RO", id: "ro" },
    { name: "US", id: "us" },
];

const services = ["apple", "hbo", "hulu", "netflix", "prime"];

const state = {
    currentCountryAtom: atom("ro"),
    currentKeywordAtom: atom(null),
    currentGenreAtom: atom(null),
    countriesListAtom: atom(countries),
    genresListAtom: atom(genres),
    servicesAtom: atom(services),
    loadingAtom: atom(true),
    cardsAtom: atom([
        {
            image: null,
            title: null,
            overview: null,
            id: null,
            streamingInfo: "",
        },
    ]),
    currentUserAtom: atom(AuthService.getCurrentUser()),
};

export default state;
