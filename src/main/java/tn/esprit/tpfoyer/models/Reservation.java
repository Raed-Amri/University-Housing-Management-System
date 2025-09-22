package tn.esprit.tpfoyer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    String idRes;

    LocalDate AU;
    boolean estValide;
    @ManyToOne
    private Chambre chambre;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants = new ArrayList<>();

}
