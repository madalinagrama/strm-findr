import { useState } from "react";
import { useAtom } from "jotai";
import { Redirect } from "react-router-dom";

import state from "../stateManager";
import AuthService from "./auth/components/services/auth.service";

import Input from "./Input";

const Login = () => {
    const [currentUser, setCurrentUser] = useAtom(state.currentUserAtom);

    const [username, setUsername] = useState(currentUser);
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");
    const [success, setSuccess] = useState(false);

    const handleLogin = (e) => {
        e.preventDefault();

        setLoading(true);
        setMessage("");

        AuthService.login(username, password).then(
            () => {
                setCurrentUser(username);
                setSuccess(true);
            },
            (error) => {
                const resMessage =
                    error?.response?.data?.message ||
                    error.message ||
                    error.toString();

                setMessage(resMessage);
                setLoading(false);
            }
        );
    };

    return (
        <section className="row">
            <div className="col-6 mx-auto p-3 rounded text-white red">
                <h2 className="text-center">Login on StrmFinder</h2>
                <img
                    src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                    alt="profile-img"
                    className="d-block rounded mx-auto mb-3"
                />
                <form onSubmit={handleLogin}>
                    <Input
                        id="username"
                        value={username}
                        label="Username"
                        callback={setUsername}
                    />

                    <Input
                        type="password"
                        id="password"
                        value={password}
                        label="Password"
                        callback={setPassword}
                    />

                    <button
                        type="submit"
                        className="btn btn-outline-light"
                        disabled={loading}
                    >
                        {loading && (
                            <span className="spinner-border spinner-border-sm mx-3"></span>
                        )}
                        <span>Login</span>
                    </button>
                </form>

                {!!message && (
                    <div className="mt-3 alert alert-danger">{message}</div>
                )}

                {!!success && <Redirect to={`/profile/${username}`} />}
            </div>
        </section>
    );
};

export default Login;
