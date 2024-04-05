package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import juUnpa.API.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {
@Autowired
    MatchService gameService;


    @GetMapping("/list")
    public ResponseEntity<List<?>> listGames(){
        return new ResponseEntity<>(gameService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> gamesOfId(@PathVariable int id){
        Optional<Game> program = gameService.listarPorId(id);

        if(program != null) {
            return new ResponseEntity<>(gameService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listOfProgram/{id}")
    public ResponseEntity<?> gamesForPrograms(@PathVariable int id){

        List<Game> programList= gameService.listarPorPrograma(id);
        if (programList.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos para esta disciplina"));
        }
        return ResponseEntity.ok(programList);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Game game){
        return new ResponseEntity<>(gameService.guardar(game),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> uapdate(@PathVariable int id, @RequestBody Game game){

        Optional<Game> gameOptional=gameService.listarPorId(id);

        gameOptional.get().setDate(game.getDate());
        gameOptional.get().setPlace(game.getPlace());
        gameOptional.get().setSchedule(game.getSchedule());
        gameOptional.get().setScore_1(game.getScore_1());
        gameOptional.get().setScore_2(game.getScore_2());

        gameService.guardar(gameOptional.get());
        return  ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        gameService .eliminar(id);
        return ResponseEntity.ok().build();
    }
}