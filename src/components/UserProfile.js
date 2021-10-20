import { useAtom } from "jotai";
import { useState, useEffect } from "react";
import state from "../stateManager";
import { getUser } from "./user/service/UserService";

const UserProfile = () => {
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
            {/*<img*/}
            {/*    src="https://cdn.forums.klei.com/monthly_2019_10/d2ab7d4323482214f463da3a5c12460944ccad0d_full.jpg.4d4cb95b2dbdd5dd9c6befca6e043515.jpg"*/}
            {/*    className="card-img-top"*/}
            {/*    alt={user.username}*/}
            {/*/>*/}
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
