package tn.esprit.tpfoyer.services;


import tn.esprit.tpfoyer.models.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveReservations();
    Reservation addReservation (Reservation reservation);
    Reservation updateReservation (Reservation reservation,String idRes);
    Reservation retrieveReservation (String idRes);
    void removeReservation(String idRes);
    Reservation ajouterReservation (long idBloc, long cin) ;
    public Reservation annulerReservation (long cinEtudiant) ;
    //Reservation ajouterReservation1 (long idBloc, long cin) ;
    //public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) ;
}
