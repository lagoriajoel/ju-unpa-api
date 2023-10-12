package juUnpa.API.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Fecha;

    private String lugar;


    private int score_1;

    private int score_2;


    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JoinColumn(name="program_id", nullable=false)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="team1_id", nullable=false)
    private Team team_1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="team2_id", nullable=false)
    private Team team_2;


}
