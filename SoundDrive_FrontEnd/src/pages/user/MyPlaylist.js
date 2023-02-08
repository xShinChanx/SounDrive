import React, { useState, useEffect, useContext } from "react";
import {useNavigate} from 'react-router-dom';
import Playlist from "../../component/PlaylistComponent";
import useAxiosFetch from "../../api/useAxiosFetch"
import AuthContext from '../../context/AuthProvider';
import NavBar from '../../component/NavBarUser';
import styles from '../../css/MyPlaylistPage.css'
import axios from 'axios'; 


function MyPlaylistPage() {

    const [playlist, setPlaylist] = useState([]);
    const { auth } = useContext(AuthContext);
    const { setAuth } = useContext(AuthContext);
    const navigate = useNavigate();

    const [playlistPresent, setPlaylistPresent] = useState(false);

    const ownerId = auth.id;
    const playlistId = auth.playlistId;
    const accessToken = auth.accessToken

    useEffect(() => {
        getPlaylist(ownerId);
        checkIfUserHasPlaylist();
    }, []);
    
    async function getPlaylist(playlistOwnerId) {
        try {
            const request = {
                playlistOwnerId: playlistOwnerId
            }
          const response = await axios.post('http://localhost:8080/playlist/getPlaylistBasedOnOwnerId', request);
          setPlaylist(response.data.playlists)
          console.log(playlistId)
         } catch (error) {
          console.error(error);
        }
    }

    async function deletePlaylist() {
        try {
            const request = {
                playlistId: playlistId
            }
          const response = await axios.post('http://localhost:8080/playlist/deletePlaylist', request);
          setAuth(prevState => ({ ...prevState, playlistId: 0 }));

          console.log(response.data)

          navigate("/createPlaylist")
         } catch (error) {
          console.error(error);
        }
    }

    function checkIfUserHasPlaylist(){
        if(playlistId === 0){
            setPlaylistPresent(false)
        }
        else
        setPlaylistPresent(true)
    }

    const createPlaylist = () =>{
        navigate("/createPlaylist");
    }

    return(
        <div>
            <NavBar /> 
            {playlist && playlist?.map((item) => {
            return(
                <Playlist key={item.id} id={item.id} name={item.name} description={item.description}/>
            ) 
            })}
            
            <div className="boxSong">
                {playlistPresent ? <button className="deletePlaylist" onClick={deletePlaylist}>Delete Playlist</button>:<button className="createPlaylist" onClick={createPlaylist}>Create Playlist</button>}
            </div>
        </div>
    )
}
  
export default MyPlaylistPage;