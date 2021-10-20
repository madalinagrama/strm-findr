import axios from "axios";
import React, { useState } from "react";

const UserPage = () => {
    const [users, setUsers] = useState([]);

    const fetchUser = async () => {
        const resp = await axios.get(process.env.REACT_APP_AUTH_URL);

        setUsers(resp.data);
    };

    console.log(users);

    return (
        <div>
            <button onClick={fetchUser}>SEARCH</button>
            {users.map((user, index) => (
                <p key={index}>
                    {user.username},{user.password}
                </p>
            ))}
        </div>
    );
};

export default UserPage;
