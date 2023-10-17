package juUnpa.API.Entities;

import java.util.ArrayList;
import java.util.List;

public class fixture {

   List<String> equipos=new ArrayList<>();

        public fixture(){
            equipos.add("ARGENTINA");
            equipos.add("CHILE");
            equipos.add("URUGUAY");
            equipos.add("paraguay");
            equipos.add("ecuador");
            this.ListMatches(equipos);
        }


    void ListMatches(List<String> ListTeam)
    {
        if (ListTeam.size() % 2 != 0)
        {
            ListTeam.add("Libre"); // If odd number of teams add a dummy
        }

        int numDays = (ListTeam.size() - 1); // Days needed to complete tournament
        int halfSize = ListTeam.size() / 2;

        List<String> teams = new ArrayList<>();

        teams.addAll(ListTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++)
        {
            System.out.println(("FECHA " + (day + 1)));
            //Crear la entidad fecha y agregar al torneo
            int teamIdx = day % teamsSize;

            System.out.println(( teams.get(teamIdx)+" "+ ListTeam.get(0)));

            for (int idx = 1; idx < halfSize; idx++)
            {

                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day  + teamsSize - idx) % teamsSize;
                System.out.println((teams.get(firstTeam)+" "+ teams.get(secondTeam)));
            }
        }
    }

}
