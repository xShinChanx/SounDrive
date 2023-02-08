import { useRef, useState, useEffect, useContext } from 'react';
import {useNavigate} from 'react-router-dom';
import AuthContext from "../context/AuthProvider";
import jwt_decode from "jwt-decode";
import styles from '../css/LoginPage.css'
import axios from 'axios';

const Login = () => {
    
    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMsg, setErrorMsg] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrorMsg('');
    }, [email, password])

    const navigatoToRegister = () =>{
        navigate("/register");
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const request = {
                email: email,
                password: password
            }

            const response = await axios.post('http://localhost:8080/login', request);

            const accessToken = response.data.accessToken;
            const playlistId = response.data.playlistId;
            const name = response.data.name;

            const decodedAccessToken = jwt_decode(accessToken)
            const role = decodedAccessToken.roles;
            const id = decodedAccessToken.userID;

            setAuth({id, role, accessToken, playlistId, name});
            
            console.log(accessToken);
            console.log(role);
            console.log(id);
            console.log(playlistId);

            setEmail('');
            setPassword('');
            setErrorMsg('');
   
            navigate("/")

            if(role.includes("ADMIN")){
                navigate("/home/admin")
            }
            else if (role.includes("USER")){
                navigate("/home/user")
            }

        } catch (err) {
            if (!err?.response) {
                setErrorMsg('No Server Response');
            } else if (err.response?.status === 400) {
                setErrorMsg('Invalid Credential');
            } else if (err.response?.status === 401) {
                setErrorMsg('Unauthorized');
            } else {
                setErrorMsg('Login Failed');
            }
        }
    }


    return ( 
        <div className="loginForm">
            <p ref={errRef} className={errorMsg ? "errorMsg" : "offscreen"} aria-live="assertive">{errorMsg}</p>

            <div className="LoginToYourAccount">Login to your account</div>
            
            <form onSubmit={handleSubmit}>
                <label htmlFor="email"></label>
                <input className="loginInput" type="text" id="email" autoComplete="off" ref={userRef} onChange={(e) => setEmail(e.target.value)} value={email} required placeholder="Email"/>

                <label htmlFor="password"></label>
                <input className="loginInput" type="password" id="password" onChange={(e) => setPassword(e.target.value)} value={password} required placeholder="Password"/>
            
                <button className="loginButton" id="ButtonLogin">LogIn</button>
            </form>

            <div className="loginFormLinks">
                <a className="loginFormLink">Forgot your password?</a>
            </div>

            <a> 
                <button className="loginButton" onClick={navigatoToRegister}>Register</button>
            </a>
            
        </div>
    )
}

export default Login