import React, { useState, useEffect, useContext } from "react"
import styles from '../../css/SongInfo.css'
import { useParams, useNavigate } from 'react-router-dom';
import AuthContext from "../../context/AuthProvider";
import axios from 'axios'; 
import NavBar from '../../component/NavBarUser';

function SongInfo() {

const { auth } = useContext(AuthContext);    
const { id } = useParams();

const userId = auth.id;
const playlistId = auth.playlistId;
const accessToken = auth.accessToken;

const [name, setName] = useState('');
const [artist, setArtist] = useState('');
const [album_name, setAlbumName] = useState('');
const [year_released, setYearReleased] = useState('');
const [playlistPresent, setPlaylistPresent] = useState(false);
//const [playlistId, setPlaylistId] = useState('');
const [errorMsg, setErrorMsg] = useState('');
const navigate = useNavigate();

const [songPresent, setSongPresent] = useState(false);

useEffect(() => {
    getSong(id);
    //checkIfUserHasPlaylist();
    checkIfSongIsInPlaylist();
    checkIfUserHasPlaylist();
}, []);

function checkIfUserHasPlaylist(){
  if(playlistId === 0){
      setPlaylistPresent(false)
  }
  else
  setPlaylistPresent(true)
}

/*async function checkIfUserHasPlaylist(){
  try{
    const request = {
      userId: userId
     }
     const response = await axios.post('http://localhost:8080/playlist/checkIfUserHasPlaylist', request);
     setPlaylistId(response.data.playlistId)
     console.log(response.data)
     console.log(response.data.playlistId)

     checkIfSongIsInPlaylist();
  }catch (error) {
    console.error(error);
  }
}*/

async function checkIfSongIsInPlaylist(){

  console.log("playlist"+playlistId);
  console.log("song"+id);

  try{
    const request = {
      playlistId: playlistId,
      songId: id
     }
     const response = await axios.post('http://localhost:8080/playlist/checkIfSongIsInPlaylist', request);
     setSongPresent(response.data.present)
     console.log(response)
  }catch (error) {
    console.error(error);
  }
}

async function getSong(songId) {
    try {
        const request = {
          songId: songId
        }
      const response = await axios.post('http://localhost:8080/songs/getSong', request);
      setName(response.data.name);
      setArtist(response.data.artist);
      setAlbumName(response.data.album_name)
      setYearReleased(response.data.year_released)
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
}

const addSongToPlaylist = async () =>{

  console.log("playlist"+playlistId);
  console.log("song"+id);
  console.log("user"+userId);
    try{
      const request ={
        playlistId: playlistId,
        songId: id
      }
      const response = await axios.put('http://localhost:8080/playlist/addSongToPlaylist', request);
      console.log(response)
      navigate("/song")
    }
    catch (err) {
      if (!err?.response) {
          setErrorMsg('No Server Response');
      }  else if (err.response?.status === 401) {
          setErrorMsg('Unauthorized');
      } else {
          setErrorMsg('Add Song To Playlist Failed');
      }
  }
}

const deleteSongFromPlaylist = async () =>{
  try{
    const request ={
      playlistId: playlistId,
      songId: id,
      accessToken: accessToken
    }
    const response = await axios.put('http://localhost:8080/playlist/deleteSongFromPlaylist', request);
    console.log(response)
    navigate("/song")
  }
  catch (err) {
    if (!err?.response) {
        setErrorMsg('No Server Response');
    }  else if (err.response?.status === 401) {
        setErrorMsg('Unauthorized');
    } else {
        setErrorMsg('Delete Song From Playlist Failed');
    }
  }
}

const createPlaylist = () =>{
  navigate("/createPlaylist");
}


return(
  <div>
   <NavBar /> 

  <div className = "songContainer">
    <div className= "songBox">
      <h2 className="songItems">Song Info</h2>
        
      <p>Name: {name}</p>
      <p>Artist: {artist}</p>
      <p>Album: {album_name}</p>
      <p>Year Released: {year_released}</p>
      
      {playlistPresent ? (songPresent ?  <button className="AddSong" onClick={addSongToPlaylist}>Add song to my playlist</button>: <button className="DeleteSong" onClick={deleteSongFromPlaylist}>Delete song from my playlist</button>) : <button className="CreatePlaylist" onClick={createPlaylist}>Create playlist to add song to your playlist</button>}      

    </div>
  </div>
  </div>

)
}
export default SongInfo;