import axios from "axios";
import authHeader from "../../auth/components/services/auth-header";

const API_URL = process.env.REACT_APP_BASE_URL + "/user";

export const getUser = (username) => {
    return axios.get(`${API_URL}/${username}`, {
        headers: authHeader(),
    });
};
