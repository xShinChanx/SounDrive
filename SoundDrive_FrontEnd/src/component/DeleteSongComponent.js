import { useRef, useState, useContext, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import styles from '../css/SongComponent.css'
import AuthContext from "../context/AuthProvider";
import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';


export default function DeleteSong(props){

    const { auth } = useContext(AuthContext);
    const navigate = useNavigate();
    const role = auth.role;

    const [errMsg, setErrMsg] = useState(''); 

    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
      const socket = SockJS("http://localhost:8080/ws");
      const stompClient = Stomp.over(socket);

      console.log(stompClient)
      setStompClient(stompClient);
    }, []);

    function sendMessage(data) {
      stompClient.send("/app/deleteSong", {}, JSON.stringify(data));
    };

  
    const config = {
        headers: {
          Authorization: `Bearer ${auth.accessToken}`
      }
    }

    async function handleSubmit(){    
        try {
          const request = {
            songId: props.id
        }
        sendMessage(props.name);
    
        const response = await axios.post('http://localhost:8080/songs/deleteSong', request, config);
    
        console.log(response.data.message);
        navigate("/home/admin");
        } 
        catch (err) {
          if (!err?.response) {
            setErrMsg('No Server Response');
          } else if (role === "USER") {
            setErrMsg('Only Admin Can Create Song');
          }else if (err.response?.status === 401) {
            setErrMsg('Unauthorized');
          } else {
            setErrMsg('Delete Song Failed');
          }
        }
    }
    
    console.log(props);

    return(
    <div className="boxSong">
        {errMsg}
        <h2>Name: {props.name}</h2>
        <p>Artist: {props.artist}</p>

       <button className="DeleteSong" onClick={handleSubmit}>DeleteSong</button>

    </div>
  )
}