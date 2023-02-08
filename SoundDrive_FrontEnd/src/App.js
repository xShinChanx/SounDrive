import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import Login from './pages/Login';
import HomeCommon from './pages/HomeCommon';
import Profile from "./pages/user/Profile";
import CreateSong from "./pages/admin/CreateSong";
import Song from "./pages/user/Song";
import CreatePlaylist from "./pages/user/CreatePlaylist";
import RegisterPage from "./pages/user/Register";
import SongInfo from "./pages/user/SongInfo";
import Playlist from "./pages/user/Playlist";
import PlaylistInfo from "./pages/user/PlaylistInfo";
import DeleteSong from "./pages/admin/DeleteSong";
import HomeAdmin from "./pages/admin/Home"
import HomeUser from "./pages/user/Home"
import MyPlaylist from "./pages/user/MyPlaylist"
import ErrorPage from "./pages/ErrorPage";


function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<HomeCommon/>} />
          <Route path="/login" element={<Login/>}></Route>
          <Route path="/profile" element={<Profile/>}></Route>
          <Route path="/createSong" element={<CreateSong/>}></Route>
          <Route path="/song" element={<Song/>}></Route>
          <Route path="/createPlaylist" element={<CreatePlaylist/>}></Route>
          <Route path="/register" element={<RegisterPage/>}></Route>
          <Route path="/songInfo/:id" element={<SongInfo/>}></Route>
          <Route path="/allPlaylist" element={<Playlist/>}></Route>
          <Route path="/playlistInfo/:id" element={<PlaylistInfo/>}></Route>
          <Route path="/deleteSong" element={<DeleteSong/>}></Route>
          <Route path="/home/admin" element={<HomeAdmin/>}></Route>
          <Route path="/home/user" element={<HomeUser/>}></Route>
          <Route path="/myPlaylist" element={<MyPlaylist/>}></Route>
          <Route path="*" element={<ErrorPage/>}></Route>
      </Routes>
    </Router>
    </>
  );
}

export default App;
