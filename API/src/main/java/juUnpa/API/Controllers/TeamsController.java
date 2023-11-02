package juUnpa.API.Controllers;

import juUnpa.API.Entities.Team;
import juUnpa.API.Entities.UnidadAcademica;
import juUnpa.API.Services.TeamService;
import juUnpa.API.Services.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/teams")
@CrossOrigin("*")
public class TeamsController {
    @Autowired
    TeamService Service;

    @GetMapping("/list")
    public ResponseEntity<List<?>> listarEquipos(){
        return new ResponseEntity<>(Service.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id){
        Team team = Service.listarPorId(id).get();

        if(team != null) {
            return new ResponseEntity<>(Service.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listOfSport/{id}")
    public ResponseEntity<?> teamsOfSport(@PathVariable int id){

          List<Team>teamsofSport= Service.listarPorDisciplina(id);
        if (teamsofSport.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos para esta disciplina"));
        }
        return ResponseEntity.ok(teamsofSport);

    }
    @GetMapping("listOfTourment/{id}")
    public ResponseEntity<?> teamsOfTourment(@PathVariable int id){

        List<Team>teamsofSport= Service.listarPorTorneo(id);
        if (teamsofSport.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos en el Campenato"));
        }
        return ResponseEntity.ok(teamsofSport);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Team team){


        Team teamOptional=Service.guardar(team);
       if (teamOptional==null) {
           return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Error al guardar Equipo"));

       }

        return new ResponseEntity<>(teamOptional,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody Team team){

        Optional<Team> optionalTeam= Service.listarPorId(id);

        optionalTeam.get().setGoalAgainst(optionalTeam.get().getGoalAgainst()+ team.getGoalAgainst());
        optionalTeam.get().setGoalFor(optionalTeam.get().getGoalFor()+ team.getGoalFor());
        optionalTeam.get().setMatchLost(optionalTeam.get().getMatchLost()+team.getMatchLost());
        optionalTeam.get().setMatchWon(optionalTeam.get().getMatchWon()+team.getMatchWon());
        optionalTeam.get().setMatchTied(optionalTeam.get().getMatchTied()+team.getMatchTied());

        optionalTeam.get().setPoint(optionalTeam.get().getPoint() + team.getPoint());
        optionalTeam.get().setGoalDifference(optionalTeam.get().getGoalFor()-optionalTeam.get().getGoalAgainst());


        return ResponseEntity.ok(Service.guardar(optionalTeam.get()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        Service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
