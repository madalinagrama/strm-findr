import axios from "axios";
import authHeader from "../../auth/components/services/auth-header";

const API_URL = process.env.REACT_APP_AUTH_URL;

export const getUser = (username) => {
    return axios.get(`${API_URL}/profile/${username}`, {
        headers: authHeader()
    });
};
