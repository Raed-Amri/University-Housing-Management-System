package tn.esprit.tpfoyer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;
    String nomEl;
    String prenomEt;
    long cin;
    String ecole;
    Date DateNaissance;
    @ManyToMany(mappedBy = "etudiants",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
