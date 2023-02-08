package app.controller;

import app.buisness.*;
import app.configuration.security.isauthenticated.IsAuthenticated;
import app.domain.Request.*;
import app.domain.Response.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongsController {
    private final GetSongsUseCase getSongsUseCase;
    private final CreateSongUseCase createSongUseCase;
    private final DeleteSongUseCase deleteSongUseCase;
    private final GetSongBasedOnIdUseCase getSongBasedOnIdUseCase;
    private final GetSongsBasedOnArtistUseCase getSongsBasedOnArtistUseCase;
    @GetMapping()
    public ResponseEntity<GetAllSongsResponse> getAllSongs() {
        GetAllSongsResponse response = getSongsUseCase.getSongs();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSongBasedOnArtist")
    public ResponseEntity<GetSongBasedOnArtistResponse> getSongs(@RequestBody @Valid GetSongBasedOnArtistRequest request) {
        GetSongBasedOnArtistResponse response = getSongsBasedOnArtistUseCase.getSongs(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getSong")
    public ResponseEntity<GetSongBasedOnIdResponse> getSong(@RequestBody @Valid GetSongBasedOnIdRequest request){
        System.out.println(request.getSongId());
        GetSongBasedOnIdResponse response = getSongBasedOnIdUseCase.getSong(request);
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/createSong")
    public ResponseEntity<CreateSongResponse> createSong(@RequestBody @Valid CreateSongRequest request) {
        CreateSongResponse response = createSongUseCase.createSong(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/deleteSong")
    public ResponseEntity<DeleteSongResponse> deleteSong(@RequestBody @Valid DeleteSongRequest request) {
        DeleteSongResponse response = deleteSongUseCase.deleteSong(request);
        return ResponseEntity.ok(response);
    }
}
