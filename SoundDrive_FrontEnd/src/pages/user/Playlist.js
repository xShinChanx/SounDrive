import React, { useState, useEffect } from "react";
import Playlist from "../../component/PlaylistComponent";
import useAxiosFetch from "../../api/useAxiosFetch"
import NavBar from '../../component/NavBarUser';


function PlaylistPage() {

    const {data: playlist} = useAxiosFetch('http://localhost:8080/playlist');

  return(
    <div>
        <NavBar /> 
        {playlist && playlist?.playlists?.map((item) => {
          return(
            <Playlist key={item.id} id={item.id} name={item.name} description={item.description}/>
          ) 
        })}
   </div>
  )
  }
  
  export default PlaylistPage;