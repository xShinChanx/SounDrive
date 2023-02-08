import React from "react"
import styles from '../../css/HomePage.css'
import imageBackground from '../../images/Studio_Background.jpg'
import badmintonClipart from '../../images/Headphones.png'
import NavBar from '../../component/NavBarAdmin';

function HomePage() {
    return (
        
        <div>
            <NavBar /> 
            <img className="studioBackground" src={imageBackground} width="1875" height="500"/>
            <div className="welcomeText">
                <div className="firstLine">Welcome to </div>
                <div className="secondLine">Sound</div>
                <div className="thirdLine">Drive</div>
                <img className = "Clipart" src={badmintonClipart}/>
            </div>
        
            <div className ="description">
                <div className="Question">Welcome Admin</div>
                <hr className = "line"/>
            </div>
       </div>
    )
}

export default HomePage;