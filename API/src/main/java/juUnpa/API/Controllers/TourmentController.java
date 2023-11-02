package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import juUnpa.API.Entities.Tourment;
import juUnpa.API.Services.MatchService;
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
    @Autowired
    MatchService gameService;


    @GetMapping("/list")
    public ResponseEntity<List<?>> listarTorneo(){
        return new ResponseEntity<>(tourmentService.listar(), HttpStatus.OK);
    }

    @GetMapping("/listOfSport/{id}")
    public ResponseEntity<?> listarTorneoPorDeporte(@PathVariable int id){
        Optional<Tourment> listOptional=tourmentService.listOfSport(id);
        if (!listOptional.isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Aun no se ha creado un torneo para esta Disciplina"));
        }

        return ResponseEntity.ok(listOptional);
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
    @PostMapping("/addTeams/{idTorneo}/{rondas}")
    public ResponseEntity<?> agregarEquiposAlTorneo(@RequestBody List<Team> teams, @PathVariable int idTorneo, @PathVariable int rondas){
       Optional<Tourment> tourmentOptional=tourmentService.listarPorId(idTorneo);
       if (!tourmentOptional.isPresent())
           return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Torneo no encontrado"));

      teams.forEach(team->{
          Optional<Team> optionalTeam= teamService.listarPorId(team.getId());
          optionalTeam.get().setTourment(tourmentOptional.get());
          teamService.guardar(optionalTeam.get());
      });

         this.CrearFechas(idTorneo, rondas);


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

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarTorneo(@PathVariable int id){

        Optional<Tourment> torneoOptional = tourmentService.listarPorId(id);

                List<Team> teams=teamService.listarPorTorneo(id);

                teams.forEach(team -> {
                       Optional<Team> teamOptional= teamService.listarPorId(team.getId());
                       teamOptional.get().setGoalFor(0);
                       teamOptional.get().setGoalAgainst(0);
                       teamOptional.get().setMatchWon(0);
                        teamOptional.get().setMatchLost(0);
                        teamOptional.get().setMatchTied(0);
                        teamOptional.get().setPoint(0);
                        teamOptional.get().setGoalDifference(0);

                    teamService.guardar(teamOptional.get());



                });

        if(!torneoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        tourmentService.elimnar(torneoOptional.get().getId());
        return ResponseEntity.ok().build();
    }



     void CrearFechas(int idTourment, int rondas)


    {
        Optional<Tourment> tourmentOptional=tourmentService.listarPorId(idTourment);
        List<Team> ListTeam=teamService.listarPorTorneo(tourmentOptional.get().getId());



        if (ListTeam.size() % 2 != 0) {

           Team teamEmpy=new Team();
            teamEmpy.setNombre("Libre");
            teamEmpy.setTourment(tourmentOptional.get());
            teamEmpy.setSport(ListTeam.get(0).getSport());
            teamEmpy.setUnidadAcademica(ListTeam.get(0).getUnidadAcademica());

            Team newTeam=teamService.guardar(teamEmpy);
            ListTeam.add(newTeam); // If odd number of teams add a dummy

;



        }

        int numDays = (ListTeam.size() - 1); // Days needed to complete tournament
        int halfSize = ListTeam.size() / 2;

        List<Team> teams = new ArrayList<>();

        teams.addAll(ListTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();


        for (int day = 0; day < numDays; day++)
        {

            //Crear la entidad fecha y agregar al torneo
            Program newProgram=new Program();
            newProgram.setDescription("FECHA " + (day + 1));
            newProgram.setTourment(tourmentOptional.get());
            Program programSave=programService.guardar(newProgram);  //ok

            int teamIdx = day % teamsSize;


           //agregar el primer cruce a la fecha



               Game firstGame=new Game();
               firstGame.setTeam_1(teams.get(teamIdx));
               firstGame.setTeam_2(ListTeam.get(0));
               firstGame.setProgram(programSave);
               Game gameGuardar=gameService.guardar(firstGame);





            for (int idx = 1; idx < halfSize; idx++)
            {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day  + teamsSize - idx) % teamsSize;

               //se agregar el resto de los cruces

                   Game game=new Game();
                   game.setTeam_1(teams.get(firstTeam));
                   game.setTeam_2(teams.get(secondTeam));
                   game.setProgram(programSave);
                   Game gameGuard=gameService.guardar(game);




            }




        }


        //si rondas igual a 2
       if(rondas!=1){
           for (int day = numDays; day < numDays*rondas; day++)
           {
               System.out.println(("FECHA " + (day + 1)));
               //Crear la entidad fecha y agregar al torneo
               Program newProgram=new Program();
               newProgram.setDescription("FECHA " + (day + 1));
               newProgram.setTourment(tourmentOptional.get());
               Program programSave=programService.guardar(newProgram);  //ok

               int teamIdx = day % teamsSize;
               //obtener el equipo
               Team teamX = teams.get(teamIdx);
               Team team0= teams.get(0);

               //agregar el primer cruce a la fecha
               Set<Game> listaDeJuegos=new HashSet<>() ;
               Game firstGame=new Game();


               firstGame.setTeam_1(teams.get(teamIdx));
               firstGame.setTeam_2(ListTeam.get(0));
               firstGame.setProgram(programSave);
               Game gameGuardar=gameService.guardar(firstGame);
               listaDeJuegos.add(gameGuardar);

               System.out.println(( teams.get(teamIdx).getNombre()+" "+ ListTeam.get(0).getNombre()));

               for (int idx = 1; idx < halfSize; idx++)
               {
                   int firstTeam = (day + idx) % teamsSize;
                   Team teamf= teams.get(firstTeam);
                   int secondTeam = (day  + teamsSize - idx) % teamsSize;
                   Team teamS= teams.get(secondTeam);

                   System.out.println((teams.get(firstTeam).getNombre()+" "+ teams.get(secondTeam).getNombre()));
                   //se agregar el resto de los cruces
                   Game game=new Game();
                   game.setTeam_1(teams.get(firstTeam));
                   game.setTeam_2(teams.get(secondTeam));
                   game.setProgram(programSave);
                   Game gameGuard=gameService.guardar(game);
                   System.out.println(gameGuard);


               }




           }
       }
    }


}
