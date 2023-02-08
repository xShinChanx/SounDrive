import styles from '../../css/LoginPage.css'
import {useState} from "react"
import { useNavigate } from "react-router-dom";
import axios from 'axios';

   
function RegisterPage() {
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [errMsg, setErrMsg] = useState(''); 
  const [password, setPassword] = useState('');

  const emailAndPasswordValidation = () =>{
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    const pwdRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

    if(!emailRegex.test(email)){
      setErrMsg("Email is not valid");
    }
    else if(!pwdRegex.test(password)){
      setErrMsg("Password is not valid");
    }
    else{
      setErrMsg("");
    }
  }

  const navigatoToLogin = () =>{
    navigate("/login");
  }


  async function handleSubmit(e){
    e.preventDefault();
    
    try {
      const request = {
        name: name,
        email: email,
        password: password
      }

      const response = await axios.post('http://localhost:8080/users/createUser', request);

      console.log(response.data.id);
      navigate("/");
   } 
    catch (err) {
      if (!err?.response) {
        setErrMsg('No Server Response');
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorized');
      } else if (err.response?.status === 400) {
        setErrMsg('Email already in use');
      } else {
        setErrMsg('Register Failed');
      }
    }
  }

  return(
    <div>

    <div className="loginForm">

      <div className="LoginToYourAccount">Register your account</div>

      {errMsg}
      <form onSubmit={handleSubmit}>

        <label htmlFor="name"></label>
        <input className="loginInput" type="text" id="name" autoComplete="off" onChange={(e) => setName(e.target.value)} value={name} required placeholder="Name"/>

        <label htmlFor="email"></label>
        <input className="loginInput" type="text" id="email" autoComplete="off" onChange={(e) => setEmail(e.target.value)} value={email} required placeholder="Email"/>

        <label htmlFor="password"></label>
        <input className="loginInput" type="password" id="password" onChange={(e) => setPassword(e.target.value)} value={password} required placeholder="Password"/>
  

        <button className="loginButton" id="ButtonRegister" onClick={emailAndPasswordValidation}>LogIn</button>

      </form>

      <div className="loginFormLinks">
        <a className="loginFormLink" onClick={navigatoToLogin}>Already have an account?</a>
      </div>

    </div>
    </div>

  )
}

export default RegisterPage;