package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.models.Etudiant;
import tn.esprit.tpfoyer.models.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    boolean existsByEtudiantsContains(Etudiant etudiant);

    Reservation findByEtudiantsContains(Etudiant etudiant);
    @Query("select r from Reservation r join Chambre c on r member of c.reservations where extract (year from r.AU )=: AU and c.bloc.foyer.universite.nomUniverse=: nomUniverse" )
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Integer AU, String nomUniverse) ;

}
