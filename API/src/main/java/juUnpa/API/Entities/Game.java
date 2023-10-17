package juUnpa.API.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Fecha;

    private String lugar;

    private String horario;


    private int score_1;

    private int score_2;

    public Game() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JoinColumn(name="program_id", nullable=false)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name="team1_id", nullable=false)
    private Team team_1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name="team2_id", nullable=false)
    private Team team_2;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getScore_1() {
        return score_1;
    }

    public void setScore_1(int score_1) {
        this.score_1 = score_1;
    }

    public int getScore_2() {
        return score_2;
    }

    public void setScore_2(int score_2) {
        this.score_2 = score_2;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Team getTeam_1() {
        return team_1;
    }

    public void setTeam_1(Team team_1) {
        this.team_1 = team_1;
    }

    public Team getTeam_2() {
        return team_2;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setTeam_2(Team team_2) {
        this.team_2 = team_2;
    }




}
