import { useAtom } from "jotai";
import { useState, useEffect } from "react";
import state from "../stateManager";
import { getUser } from "./user/service/UserService";
import profile from "../img/profile.jpg";
import Index from "./Index";

const UserProfile = () => {
    const profileProps = {
        src: profile,
        width: 120,
        height: 120,
    };

    const [currentUser, setCurrentUser] = useAtom(state.currentUserAtom);

    const [user, setUser] = useState({
        username: null,
        email: null,
        joinedDate: null,
    });

    useEffect(() => {
        const setTheUser = async () => {
            return getUser(currentUser.username).then((res) => {
                setUser(res.data);
                setCurrentUser(res.data);
            });
        };

        setTheUser();
    }, []);

    return (
        <div className="card my-3">
            <div>
                <h5>Your Profile</h5>
            </div>
            <div>
                <img {...profileProps} alt={user.username} />
            </div>
            <div className="card-body">
                <h5 className="card-title">Name: {user.username}</h5>
                <p> </p>
                <p className="card-text">Email: {user.email}</p>
                <p className="card-text">Joined date: {user.joinedDate}</p>
            </div>
            <Index onlyFavorites={true} />
        </div>
    );
};

export default UserProfile;
