import React, { useState, useEffect, useContext } from "react"
import { useParams, useNavigate } from 'react-router-dom';
import Song from "../../component/SongComponent";
import axios from 'axios'; 
import NavBar from '../../component/NavBarUser';

function PlaylistInfo() {
  const [song, setSong] = useState([]);
  const [playlistOnwer, setPlaylistOwner] = useState();
  const [playlistName, setPlaylistName] = useState();
  const [description, setPlaylistDescription] = useState();
  const { id } = useParams();

  useEffect(() => {
    getSongs(id);
  }, []);
    

  async function getSongs(playlistId) {
    try {
      const request = {
        playlistId: playlistId
      }
      const response = await axios.post('http://localhost:8080/playlist/getPlaylistBasedOnID', request);
      setSong(response.data.listOfSongs)
      setPlaylistOwner(response.data.playlistOwner)
      setPlaylistName(response.data.playlistName)
      setPlaylistDescription(response.data.description)

    } 
    catch (error) {
      console.error(error);
    }
  }

  return (
    <div>
        <NavBar /> 
        <div className="mainContent">
          <div className="header">Playlist Name: {playlistName}</div>
          <div className="header">Playlist Onwer: {playlistOnwer}</div>
          <div className="header">Description: {description}</div>
          <div className="header">Songs:</div>
          <div className="songContainer"></div>
    
          {song && song?.map((item) => {
            return(
              <Song key={item.id} id={item.id} name={item.name} artist={item.artist} album={item.album_name} />
            )
          })}
    
        </div>
      </div>
  )
}
export default PlaylistInfo;