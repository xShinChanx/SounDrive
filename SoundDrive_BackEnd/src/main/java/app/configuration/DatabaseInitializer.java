package app.configuration;

import app.buisness.CreateUserUseCase;
import app.domain.Request.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import app.persistence.PlaylistRepository;
import app.persistence.SongRepository;
import app.persistence.UserRepository;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;

@Component
@AllArgsConstructor
public class DatabaseInitializer {

    private CreateUserUseCase createUserUseCase;
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void userDummyDatabaseInitail(){
        if(userRepository.count() == 0){
            createUserUseCase.createUser(CreateUserRequest.builder().name("Basheer").email("basheer@gmail.com").password("yeet").build());
            createUserUseCase.createUser(CreateUserRequest.builder().name("Nida").email("nida@gmail.com").password("yeet").build());
            createUserUseCase.createUser(CreateUserRequest.builder().name("Eduard").email("eduard@gmail.com").password("yeet").build());
            createUserUseCase.createUser(CreateUserRequest.builder().name("Dennis").email("dennis@gmail.com").password("yeet").build());
            createUserUseCase.createUser(CreateUserRequest.builder().name("Adel").email("adel@gmail.com").password("yeet").build());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void playlistDummyDatabaseInitial(){
        if(playlistRepository.count() == 0){
            playlistRepository.save(PlaylistEntity.builder().name("Gym").description("Love to listen to this song in Gym").build());
            playlistRepository.save(PlaylistEntity.builder().name("Sleep").description("Song while sleeping").build());
            playlistRepository.save(PlaylistEntity.builder().name("Walk").description("Take walk while listening to this playlist").build());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void songDummyDatabaseInitail(){
        if(songRepository.count() == 0){
            songRepository.save(SongEntity.builder().name("Haunt U").album_name("Haunt U").artist("Lil Peep").year_released("2018 Jan").build());
            songRepository.save(SongEntity.builder().name("Cobain").album_name("Cobain").artist("Lil Peep").year_released("2019 Jan").build());
            songRepository.save(SongEntity.builder().name("Prove my love").album_name("Prove my love").artist("Lil Peep").year_released("2020 Jan").build());
        }
    }

}
