package juUnpa.API.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String description;

    @OneToMany(mappedBy="program")
    private Set<Game> games;

    private int numRonda;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

}
