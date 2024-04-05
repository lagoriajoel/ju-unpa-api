package juUnpa.API.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="sport_id", nullable=false)
    private Sport sport;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name="unidad_id", nullable=false)
    private UnidadAcademica unidadAcademica;

    private int matchWon;
    private int matchLost;
    private int matchTied;
    private int goalFor;
    private int goalAgainst;
    private int goalDifference;
    private int point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="tournament_id")              //se cambia torneo_id por tournament_id
    private Tournament tournament;

   // @OneToMany(mappedBy = "team_1", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    //private Set<Game> games=new HashSet<>();


}
