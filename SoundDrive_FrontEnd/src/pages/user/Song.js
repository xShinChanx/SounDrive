import React, { useState, useEffect, useContext } from "react"
import styles from '../../css/SongPage.css'
import Song from "../../component/SongComponent";
import useAxiosFetch from "../../api/useAxiosFetch"
import NavBar from '../../component/NavBarUser';

function SongPage() {
  const {data: song} = useAxiosFetch('http://localhost:8080/songs');
  //setApiData(data)
  console.log(song);
  return (
    <div>
        <NavBar /> 

      <div className="mainContent">
        <div className="header">All Songs</div>
        <div className="songContainer"></div>

          {song && song?.songs?.map((item) => {
          return(
            <Song key={item.id} id={item.id} name={item.name} artist={item.artist} album={item.album_name} />
        )
        })}

      </div>
    </div>
  )
}

export default SongPage;