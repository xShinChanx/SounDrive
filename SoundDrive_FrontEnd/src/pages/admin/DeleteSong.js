import styles from '../../css/SongComponent.css'
import Song from "../../component/DeleteSongComponent";
import useAxiosFetch from "../../api/useAxiosFetch"
import NavBar from '../../component/NavBarAdmin';

function SongPage() {
  const {data: song} = useAxiosFetch('http://localhost:8080/songs');

  console.log(song);
  return (
    <div>
        <NavBar /> 
        <div className="mainContent">
            <div className="header">Welcome Admin</div>
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