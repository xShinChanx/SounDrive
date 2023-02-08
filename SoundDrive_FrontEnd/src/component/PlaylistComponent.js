import React from "react";
import styles from '../css/PlaylistPage.css'
import { Link } from 'react-router-dom';

export default function Playlist(props){

    return(
        <div className="playlistContainer">

            <div className= "boxPlaylist">
    
                <h2 className="itemPlaylist">{props.name}</h2>
                <p>Description: {props.description}</p>
                <p>Public Playlist</p>
                
                <Link to={"/playlistInfo/"+props.id} >
                    <button className="selectButton">Select Playlist</button>
                </Link>
            </div>
            
            <hr className= "line"/>
    
        </div>
    )
}