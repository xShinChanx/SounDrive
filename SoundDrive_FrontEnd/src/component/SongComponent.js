import React from "react";
import { useNavigate } from "react-router-dom";
import styles from '../css/SongComponent.css'
import { Link } from 'react-router-dom';

export default function Song(props){

  console.log(props);
  return(
    <div className="boxSong">
      <h2>Name: {props.name}</h2>
      <p>Artist: {props.artist}</p>

      <Link to={"/songInfo/"+props.id} >
        <button className="selectButton">View more information</button>
      </Link>
            
    </div>
  )
}
