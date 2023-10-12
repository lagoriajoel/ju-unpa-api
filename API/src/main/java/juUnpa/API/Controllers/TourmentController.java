package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import juUnpa.API.Entities.Tourment;
import juUnpa.API.Services.ProgramService;
import juUnpa.API.Services.TeamService;
import juUnpa.API.Services.TourmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/tourment")
@CrossOrigin("*")
public class TourmentController {
    @Autowired
    TourmentService tourmentService;
    @Autowired
    TeamService teamService;
    @Autowired
    ProgramService programService;


    @GetMapping("/list")
    public ResponseEntity<List<?>> listarTorneo(){
        return new ResponseEntity<>(tourmentService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> obtenerDisciplinaPorId(@PathVariable int id){
        Tourment tourment = tourmentService.listarPorId(id).get();

        if(tourment != null) {
            return new ResponseEntity<>(tourmentService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarDisciplina(@RequestBody Tourment tourment){
        return new ResponseEntity<>(tourmentService.guardar(tourment),HttpStatus.CREATED);
    }
    @PostMapping("/addTeams/{idTorneo}")
    public ResponseEntity<?> agregarEquiposAlTorneo(@RequestBody List<Team> teams, @PathVariable int idTorneo){
       Optional<Tourment> tourmentOptional=tourmentService.listarPorId(idTorneo);
       if (!tourmentOptional.isPresent())
           return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Torneo no encontrado"));

      teams.forEach(team->{
          Optional<Team> optionalTeam= teamService.listarPorId(team.getId());
          optionalTeam.get().setTourment(tourmentOptional.get());
          teamService.guardar(optionalTeam.get());
      });

      return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> editarContenido(@RequestBody Tourment tourment, @PathVariable int id){

        Optional<Tourment> optionalTorneo = tourmentService.listarPorId(id);
        if(!optionalTorneo.isPresent()){


            return ResponseEntity.unprocessableEntity().build();
        }

        tourment.setId(optionalTorneo.get().getId());
        tourmentService.guardar(tourment);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/addProgram/{idTourment}")
    public ResponseEntity<?> agregarProgramacion(@PathVariable int idTourment){
        //this.CrearFechas(idTourment);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable int id){

        Optional<Tourment> torneoOptional = tourmentService.listarPorId(id);

        if(!torneoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        tourmentService.elimnar(torneoOptional.get().getId());
        return ResponseEntity.ok().build();
    }



    /*void CrearFechas(int idTourment)


    {
        Optional<Tourment> tourmentOptional=tourmentService.listarPorId(idTourment);
        List<Team> ListTeam=teamService.listarPorTorneo(tourmentOptional.get().getId());

        if (ListTeam.size() % 2 != 0)
        {
            Team teamEmpy=new Team();
            teamEmpy.setNombre("Libre");
            Team newTeam=teamService.guardar(teamEmpy);
            ListTeam.add(newTeam); // If odd number of teams add a dummy
        }

        int numDays = (ListTeam.size() - 1); // Days needed to complete tournament
        int halfSize = ListTeam.size() / 2;

        List<Team> teams = new ArrayList<>();

        teams.addAll(ListTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++)
        {
            System.out.println(("FECHA " + (day + 1)));
            //Crear la entidad fecha y agregar al torneo
            Program newProgram=new Program();
            newProgram.setDescription("FECHA " + (day + 1));
            newProgram.setTourment(tourmentOptional.get());


            Program programSave=programService.guardar(newProgram);  //ok

            int teamIdx = day % teamsSize;
            //obtener el equipo
            Optional<Team> teamX= teamService.listarPorId(teams.get(teamIdx).getId());
            Optional<Team> team0= teamService.listarPorId(teams.get(0).getId());

           //agregar el primer cruce a la fecha
             Set<Game> listaDeJuegos=new HashSet<>() ;
             Game firstGame=new Game();

             firstGame.setTeam_1(teamX.get());
             firstGame.setTeam_2(team0.get());
             listaDeJuegos.add(firstGame);
            // programSave.setGames(listaDeJuegos);
             //programService.save(programSave);
              System.out.println(( teams.get(teamIdx).getNombre()+" "+ ListTeam.get(0).getNombre()));

            for (int idx = 1; idx < halfSize; idx++)
            {
                int firstTeam = (day + idx) % teamsSize;
                Optional<Team> teamf= teamService.listarPorId(teams.get(firstTeam).getId());
                int secondTeam = (day  + teamsSize - idx) % teamsSize;
                Optional<Team> teamS= teamService.listarPorId(teams.get(secondTeam).getId());

                System.out.println((teams.get(firstTeam).getNombre()+" "+ teams.get(secondTeam).getNombre()));
               //se agregar el resto de los cruces
                Game game=new Game();
                game.setTeam_1(teams.get(teamf.get().getId()));
                game.setTeam_2(teams.get(teamS.get().getId()));
                listaDeJuegos.add(game);

                System.out.println(listaDeJuegos);

            }
            programSave.setGames(listaDeJuegos);

            programService.guardar(programSave);

        }
    }*/


}
