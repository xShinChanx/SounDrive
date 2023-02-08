import { useRef, useState, useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import AuthContext from "../../context/AuthProvider";
import styles from '../../css/CreateSongPage.css'
import NavBar from '../../component/NavBarAdmin';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from 'axios';

const CreateSong = () =>{

  const { auth } = useContext(AuthContext);
  const role = auth.role
  const config = {
    headers: {
      Authorization: `Bearer ${auth.accessToken}`
    }
  }

  const navigate = useNavigate();
  const errRef = useRef();
  const [name, setName] = useState ('');
  const [artist, setArtist] = useState('');
  const [album_name, setAlbum_name] = useState('');
  const [year_released, setYear_released] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
      const socket = SockJS("http://localhost:8080/ws");
      const stompClient = Stomp.over(socket); // creates a new stomp client

      console.log(stompClient)
      setStompClient(stompClient);
    }, []);

    function sendMessage(data) {
      stompClient.send("/app/createSong", {}, JSON.stringify(data));
    };

  async function handleSubmit(e){
    e.preventDefault();

    try {
      const request = {
        name: name,
        artist: artist,
        album_name: album_name,
        year_released: year_released
    }

    sendMessage(name);
    const response = await axios.post('http://localhost:8080/songs/createSong', request, config);

    console.log(response.data.name);
    navigate("/deleteSong");
    } catch (err) {

      if (role === "USER") {
        setErrorMsg('Only Admin Can Create Song');
        navigate("/Login")
      }else if (!err?.response) {
        setErrorMsg('No Server Response');
      }else if (err.response?.status === 401) {
        setErrorMsg('Unauthorized');
      } else {
        setErrorMsg('Create Song Failed');
      }
    }
  }

  return(
    <div>
      <NavBar /> 
      <div className="songForm">
        <p ref={errRef} className={errorMsg ? "errorMsg" : "offscreen"} aria-live="assertive">{errorMsg}</p>

        <div className="createSong">Create Song</div>

        <form onSubmit={handleSubmit}>

          <label htmlFor="name"></label>
          <input className="createSongInput" type="text" autoComplete="off" id="name" onChange={(e) => setName(e.target.value)} value={name} maxLength="20" placeholder="Name"></input>

          <label htmlFor="artist"></label>
          <input className="createSongInput" type="text" autoComplete="off" id="artist" onChange={(e) => setArtist(e.target.value)} value={artist} maxLength="20" placeholder="Artist"></input>

          <label htmlFor="album_name"></label>
          <input className="createSongInput" type="text" autoComplete="off" id="album_name" onChange={(e) => setAlbum_name(e.target.value)} value={album_name} maxLength="20" placeholder="Album Name"></input>

          <label htmlFor="year_released"></label>
          <input className="createSongInput" type="text" id="year_released" onChange={(e) => setYear_released(e.target.value)} value={year_released} maxLength="20" placeholder="Year Released"></input>

          <button className="createSongButton">Create Song</button>
      
        </form>
     </div>
    </div>
  )
}

export default CreateSong;