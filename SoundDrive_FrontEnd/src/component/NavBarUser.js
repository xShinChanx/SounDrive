import React from "react"
import { useContext } from 'react';
import { NavLink } from "react-router-dom"
import AuthContext from '../context/AuthProvider';
import styles from '../css/NavBarComponent.css'

function NavBar() {

  const { auth } = useContext(AuthContext);

  const links = [
    {
      id: 1,
      path: "/home/user",
      text: "Home"
    },
    {
      id: 2,
      path: "/song",
      text: "Songs"
    },
    {
      id: 3,
      path: "/allPlaylist",
      text: "Playlist"
    },
    {
      id: 4,
      path: "/myPlaylist",
      text: "My Playlist"
    },
    {
      id: 5,
      path: "/profile",
      text: String(auth.name)
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