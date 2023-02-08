import React from "react"
import { useContext } from 'react';
import { NavLink } from "react-router-dom"
import AuthContext from '../context/AuthProvider';
import styles from '../css/NavBarComponent.css'

function NavBar() {
  const links = [
    {
      id: 1,
      path: "/Login",
      text: "Login"
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