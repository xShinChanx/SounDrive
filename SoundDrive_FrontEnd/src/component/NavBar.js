import React from "react"
import { useContext } from 'react';
import { NavLink } from "react-router-dom"
import AuthContext from '../context/AuthProvider';
import styles from '../css/NavBarComponent.css'

function NavBar() {
  const links = [
    {
      id: 1,
      path: "/",
      text: "Home"
    },
    {
      id: 2,
      path: "/song",
      text: "Song"
    },
    {
      id: 3,
      path: "/Login",
      text: "Login"
    },
    {
      id: 4,
      path: "/allPlaylist",
      text: "Playlist"
    },
    {
      id: 5,
      path: "/createSong",
      text: "Create Song"
    },
    {
      id: 6,
      path: "/profile",
      text: "Profile"
    },
    {
      id: 7,
      path: "/createPlaylist",
      text: "Create Playlist"
    },
    {
      id: 8,
      path: "/deleteSong",
      text: "Delete Song"
    }
  ];
  
  return (
    <nav className="menubar">
      <ul>
        <div className="logo">SoundDrive</div>
        {links.map(link => {
          return (
            <li key={link.id}>
              <NavLink to={link.path}>{link.text}</NavLink>
            </li>
          );
        })}
      </ul>
    </nav>
    );
}
  
export default NavBar;