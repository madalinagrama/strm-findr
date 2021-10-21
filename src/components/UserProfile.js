import { useAtom } from "jotai";
import { useState, useEffect } from "react";
import state from "../stateManager";
import { getUser } from "./user/service/UserService";
import profile from "../img/profile.jpg";

const UserProfile = () => {
    const profileProps = {
        src: profile,
        width: 120,
        height: 120,
    };

    const [currentUser, setCurrentUser] = useAtom(state.currentUserAtom);
    console.log(currentUser);
    const [user, setUser] = useState({
        username: null,
        role: null,
        email: null,
    });
    console.log(user);

    useEffect(() => {
        const setTheUser = async () => {
            return getUser(currentUser).then((res) => {
                setUser(res.data);
                setCurrentUser(res.data);
            });
        };

        setTheUser();
    }, []);

    return (
        <div className="card my-3">
            <img {...profileProps} alt={user.username}/>
            <div className="card-body">
                <h5 className="card-title">{user.username}</h5>
                <p className="card-text">
                    {user.email} {user.role}
                </p>
            </div>
        </div>
    );
};

export default UserProfile;
