package juUnpa.API.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String name;

    @OneToOne()
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    private int numTeams;

    private int numRondas;

    private int edition;
    
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Program> programList;




}
