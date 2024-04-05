package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import juUnpa.API.Entities.Tournament;
import juUnpa.API.Services.MatchService;
import juUnpa.API.Services.ProgramService;
import juUnpa.API.Services.TeamService;
import juUnpa.API.Services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/tourment")
@CrossOrigin("*")
public class TournamentController {
    @Autowired
    TournamentService tournamentService;
    @Autowired
    TeamService teamService;
    @Autowired
    ProgramService programService;
    @Autowired
    MatchService gameService;


    @GetMapping("/list")
    public ResponseEntity<List<?>> listarTorneo(){
        return new ResponseEntity<>(tournamentService.listar(), HttpStatus.OK);
    }

    @GetMapping("/listOfSport/{id}")
    public ResponseEntity<?> listarTorneoPorDeporte(@PathVariable int id){
        Optional<Tournament> listOptional= tournamentService.listOfSport(id);
        if (!listOptional.isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Aun no se ha creado un torneo para esta Disciplina"));
        }

        return ResponseEntity.ok(listOptional);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> obtenerDisciplinaPorId(@PathVariable int id){
        Tournament tournament = tournamentService.listarPorId(id).get();

        if(tournament != null) {
            return new ResponseEntity<>(tournamentService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarDisciplina(@RequestBody Tournament tournament){
        return new ResponseEntity<>(tournamentService.guardar(tournament),HttpStatus.CREATED);
    }
    @PostMapping("/addTeams/{idTorneo}/{rondas}")
    public ResponseEntity<?> agregarEquiposAlTorneo(@RequestBody List<Team> teams, @PathVariable int idTorneo, @PathVariable int rondas){
       Optional<Tournament> tourmentOptional= tournamentService.listarPorId(idTorneo);
       if (!tourmentOptional.isPresent())
           return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "Torneo no encontrado"));

      teams.forEach(team->{
          Optional<Team> optionalTeam= teamService.listarPorId(team.getId());
          optionalTeam.get().setTournament(tourmentOptional.get());
          teamService.guardar(optionalTeam.get());
      });

         this.CrearFechas(idTorneo, rondas);


      return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> editarContenido(@RequestBody Tournament tournament, @PathVariable int id){

        Optional<Tournament> optionalTorneo = tournamentService.listarPorId(id);
        if(!optionalTorneo.isPresent()){


            return ResponseEntity.unprocessableEntity().build();
        }

        tournament.setId(optionalTorneo.get().getId());
        tournamentService.guardar(tournament);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/addProgram/{idTourment}")
    public ResponseEntity<?> agregarProgramacion(@PathVariable int idTourment){

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarTorneo(@PathVariable int id){

        Optional<Tournament> torneoOptional = tournamentService.listarPorId(id);

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

                   if(teamOptional.get().getName().equals("Libre")){
                       teamService.eliminar(teamOptional.get().getId());
                   }
                   else teamService.guardar(teamOptional.get());



                });

        if(!torneoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        tournamentService.elimnar(torneoOptional.get().getId());
        return ResponseEntity.ok().build();
    }



     void CrearFechas(int idTourment, int rondas)


    {
        Optional<Tournament> tourmentOptional= tournamentService.listarPorId(idTourment);
        List<Team> ListTeam=teamService.listarPorTorneo(tourmentOptional.get().getId());



        if (ListTeam.size() % 2 != 0) {

           Team teamEmpy=new Team();
            teamEmpy.setName("Libre");
            teamEmpy.setTournament(tourmentOptional.get());
            teamEmpy.setSport(ListTeam.get(0).getSport());
            teamEmpy.setUnidadAcademica(ListTeam.get(0).getUnidadAcademica());

            Team newTeam=teamService.guardar(teamEmpy);
            ListTeam.add(newTeam); // If odd number of teams add a dummy

        }

        int numDays = (ListTeam.size() - 1); // Days needed to complete tournament
        int halfSize = ListTeam.size()/2;

        List<Team> teams = new ArrayList<>();

        teams.addAll(ListTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();


        for (int day = 0; day < numDays; day++)
        {

            //Crear la entidad fecha y agregar al torneo
            Program newProgram=new Program();
            newProgram.setDescription("FECHA " + (day + 1));
            newProgram.setTournament(tourmentOptional.get());
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
               newProgram.setTournament(tourmentOptional.get());
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

               System.out.println(( teams.get(teamIdx).getName()+" "+ ListTeam.get(0).getName()));

               for (int idx = 1; idx < halfSize; idx++)
               {
                   int firstTeam = (day + idx) % teamsSize;
                   Team teamf= teams.get(firstTeam);
                   int secondTeam = (day  + teamsSize - idx) % teamsSize;
                   Team teamS= teams.get(secondTeam);

                   System.out.println((teams.get(firstTeam).getName()+" "+ teams.get(secondTeam).getName()));
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
