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
public class Tourment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo;

    private String nombre;

    @OneToOne()
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport sport;

    private int numTeams;

    private int numRondas;

    private int edition;
    
    @OneToMany(mappedBy = "tourment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Program> programList;




}
