import { useRef, useState, useContext } from 'react';
import { useNavigate } from "react-router-dom";
import AuthContext from "../../context/AuthProvider";
import styles from '../../css/CreateSongPage.css'
import NavBar from '../../component/NavBarUser';
import axios from 'axios';

const CreatePlaylist = () =>{

  const { auth } = useContext(AuthContext);
  const { setAuth } = useContext(AuthContext);
  const role = auth.role;
  const id = auth.id;

  const navigate = useNavigate();
  const errRef = useRef();
  const [name, setName] = useState ('');
  const [description, setDescription] = useState('');
  const [errorMsg, setErrorMsg] = useState('');


  async function handleSubmit(e){
    e.preventDefault();

    try {
      const request = {
        name: name,
        description: description,
        userId: id
    }

    const response = await axios.post('http://localhost:8080/playlist/createPlaylist', request);
    console.log(response.data.name);
    console.log(response.data.playlistId)
    setAuth(prevState => ({ ...prevState, playlistId: response.data.playlistId }));

    navigate("/myPlaylist");
    } 
    catch (err) {
      if (role === "ADMIN") {
        setErrorMsg('Only User Can Create Playlist');
      }
       else {
        navigate("/home/user");
        console.log(err)
      }
    }
  }

  return(
    <div>
      <NavBar /> 
      <div className="songForm">
        
        <p ref={errRef} className={errorMsg ? "errorMsg" : "offscreen"} aria-live="assertive">{errorMsg}</p>

        <div className="createSong">Create Playlist</div>

        <form onSubmit={handleSubmit}>

          <label htmlFor="name"></label>
          <input className="createSongInput" type="text" id="name" autoComplete="off" onChange={(e) => setName(e.target.value)} value={name} maxLength="20" placeholder="Name"></input>

          <label htmlFor="description"></label>
          <input className="createSongInput" type="text" id="description" autoComplete="off" onChange={(e) => setDescription(e.target.value)} value={description} placeholder="Description"></input>

          <button className="createSongButton">Create Playlist</button>
        </form>

      </div>

    </div>
  )
}
export default CreatePlaylist;