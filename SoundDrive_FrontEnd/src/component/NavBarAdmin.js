import React from "react"
import { useContext } from 'react';
import { NavLink } from "react-router-dom"
import AuthContext from '../context/AuthProvider';
import styles from '../css/NavBarComponent.css'

function NavBar() {
  const links = [
    {
      id: 1,
      path: "/home/admin",
      text: "Home"
    },
    {
      id: 2,
      path: "/createSong",
      text: "Create Song"
    },
    {
      id: 3,
      path: "/deleteSong",
      text: "Delete Song"
    },
    {
      id: 4,
      path: "/",
      text: "Log Out"
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