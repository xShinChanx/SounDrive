import React, { useState, useEffect } from "react"
import axios from 'axios'
import { useContext } from 'react';
import {useNavigate} from 'react-router-dom';
import AuthContext from '../../context/AuthProvider';
import styles from '../../css/ProfilePage.css'
import NavBar from '../../component/NavBarUser';

function Profile(){

const { auth } = useContext(AuthContext);
const { setAuth } = useContext(AuthContext);
const accessToken = auth.accessToken;

const [id, setId] = useState('');
const [name, setName] = useState('');
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');
const navigate = useNavigate();


useEffect(() => {
    getUser();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const request = {
        userId: id,
        password: password,
        accessToken: accessToken
    }
      const response = await axios.put('http://localhost:8080/users/updateUser', request);
      navigate("/login")
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }

async function getUser() {
    try {
        const request = {
            accessToken: accessToken
        }
      const response = await axios.post('http://localhost:8080/users/getUser', request);
      setName(response.data.name);
      setEmail(response.data.email);
      setId(response.data.id)
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  const logOut = () =>{
      setAuth({});
    navigate("/");
  }

  return(
    <div>
      <NavBar /> 
    <div className = "profileContainer">
      <div className="profileBox">
        <h2 className="profileItems">Profile</h2>
          
        <p>Name: {name}</p>
        <p>Email: {email}</p>
    
        <form onSubmit={handleSubmit}>
          <div className="ChangePassContainer">
            <input className="newPassValue" onChange={(e) => setPassword(e.target.value)} value={password} type="password" placeholder="Password"></input>
            <button className="editButtonPass" type="submit">Request new password</button>
          </div>
        </form>
        
        <button className="logOutButton" onClick={logOut} >Log Out</button>
      </div>
    </div>
    </div>
)
}export default Profile;