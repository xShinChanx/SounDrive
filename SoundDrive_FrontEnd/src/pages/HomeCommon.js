import React from "react"
import styles from '../css/HomePage.css'
import imageBackground from '../images/Studio_Background.jpg'
import badmintonClipart from '../images/Headphones.png'
import NavBar from '../component/NavBarCommon';

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
                <div className="Question">What is SounDrive?</div>
                <hr className = "line"/>

                <div className="container">

                    <div className="box">
                        <h2 className="items">First Step</h2>
                        <p className="itemsInfo">
                            Create an account and Login.
                        </p>
                    </div>

                    <div className="box">
                        <h2 className="items">Second Step</h2>
                        <p className="itemsInfo">
                            Create a playlist and add songs to it.
                        </p>
                    </div>

                    <div className="box">
                        <h2 className="items">Third Step</h2>
                        <p className="itemsInfo">
                            Explore other playlist and share your playlist with others.
                        </p>
                    </div>
                </div>
            </div>
       </div>
    )
}

export default HomePage;