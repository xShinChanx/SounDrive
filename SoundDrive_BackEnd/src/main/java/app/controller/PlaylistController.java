package app.controller;
import app.buisness.*;
import app.domain.Request.*;
import app.domain.Response.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {
    private final GetPlaylistUseCase getPlaylistUseCase;
    private final AddSongToPlaylistUseCase addSongToPlaylistUseCase;
    private final GetPlaylistSongsByIdUseCase getPlaylistSongsByIdUseCase;
    private final CreatePlaylistUseCase createPlaylistUseCase;
    private final RemoveSongFromPlaylistUseCase removeSongFromPlaylistUseCase;
    private final DeletePlaylistUseCase deletePlaylistUseCase;
    private final CheckIfUserHasPlaylistUseCase checkIfUserHasPlaylistUseCase;
    private final CheckIfSongIsInPlaylistUseCase checkIfSongIsInPlaylistUseCase;
    private final GetPlaylistBasedOnOwnerIdUseCase getPlaylistBasedOnOwnerIdUseCase;

    @GetMapping()
    public ResponseEntity<GetPlaylistResponse> getAllPlaylist() {
        GetPlaylistResponse response = getPlaylistUseCase.getPlaylist();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createPlaylist")
    public ResponseEntity<CreatePlaylistResponse> createPlaylist(@RequestBody @Valid CreatePlaylistRequest request){
        CreatePlaylistResponse response = createPlaylistUseCase.createPlaylist(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deletePlaylist")
    public ResponseEntity<DeletePlaylistResponse> deletePlaylist(@RequestBody @Valid DeletePlaylistRequest request){
        DeletePlaylistResponse response = deletePlaylistUseCase.deletePlaylist(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/addSongToPlaylist")
    public ResponseEntity<AddSongToPlaylistResponse> addSongToPlaylist(@RequestBody @Valid AddSongToPlaylistRequest request){
        AddSongToPlaylistResponse response = addSongToPlaylistUseCase.AddSongToPlaylist(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/deleteSongFromPlaylist")
    public ResponseEntity<RemoveSongFromPlaylistResponse> removeSongFromPlaylist(@RequestBody @Valid RemoveSongFromPlaylistRequest request){
        RemoveSongFromPlaylistResponse response = removeSongFromPlaylistUseCase.AddSongToPlaylist(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getPlaylistBasedOnID")
    public ResponseEntity<GetPlaylistSongsByIdResponse> getSongs(@RequestBody @Valid GetPlaylistSongsByIdRequest request){
        GetPlaylistSongsByIdResponse response = getPlaylistSongsByIdUseCase.getSongs(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkIfUserHasPlaylist")
    public ResponseEntity<CheckIfUserHasPlaylistResponse> checkIfUserHasPlaylist(@RequestBody @Valid CheckIfUserHasPlaylistRequest request){
        CheckIfUserHasPlaylistResponse response = checkIfUserHasPlaylistUseCase.checkIfUserHasPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/checkIfSongIsInPlaylist")
    public ResponseEntity<CheckIfSongIsInPlaylistResponse> checkIfUserHasPlaylist(@RequestBody @Valid CheckIfSongIsInPlaylistRequest request){
        CheckIfSongIsInPlaylistResponse response = checkIfSongIsInPlaylistUseCase.checkIfSongIsInPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/getPlaylistBasedOnOwnerId")
    public ResponseEntity<GetPlaylistResponse> getPlaylist(@RequestBody @Valid GetPlaylistBasedOnOwnerIdRequest request){
        GetPlaylistResponse response = getPlaylistBasedOnOwnerIdUseCase.getPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
