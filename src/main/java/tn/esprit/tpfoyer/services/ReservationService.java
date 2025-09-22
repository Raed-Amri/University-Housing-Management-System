package tn.esprit.tpfoyer.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.models.Etudiant;
import tn.esprit.tpfoyer.models.Reservation;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.EtudiantRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    BlocRepository blocRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> retrieveReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation, String idRes) {
        Reservation existingReservation = reservationRepository.findById(idRes)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idRes));
        existingReservation.setAU(reservation.getAU());
        existingReservation.setEstValide(reservation.isEstValide());
        return reservationRepository.save(existingReservation);
    }

    @Override
    public Reservation retrieveReservation(String idRes) {
        return reservationRepository.findById(idRes).orElse(null);
    }

    @Override
    public void removeReservation(String idRes) {
        Reservation existingReservation = reservationRepository.findById(idRes)
                .orElseThrow(() -> new RuntimeException("etudiant not found with id "+idRes));
        reservationRepository.delete(existingReservation);

    }
    @Transactional
    public Reservation ajouterReservation(long idBloc, long cin) {
        // Find the student
        Etudiant etudiant = etudiantRepository.findBycin(cin);
        if (etudiant == null) {
            throw new RuntimeException("Etudiant non trouvé avec le CIN " + cin);
        }
        // Check if the student already has a reservation
        if (reservationRepository.existsByEtudiantsContains(etudiant)) {
            throw new RuntimeException("L'étudiant avec le CIN " + cin + " a déjà une réservation active.");
        }
        // Find all available rooms (optimize this if necessary)
        List<Chambre> chambresDisponibles = chambreRepository.findByBlocIdBloc(idBloc);

        // Loop through available rooms and assign the student to the first available one
        for (Chambre availableChambre : chambresDisponibles) {
            if (availableChambre.getReservations().stream()
                    .mapToInt(reservation -> reservation.getEtudiants().size())
                    .sum() < availableChambre.getTypeC().getCapacite()) {
                // Create a new reservation and assign the student
                String numReservation = availableChambre.getNumchambre() + "-" + availableChambre.getBloc().getNomBloc() + "-" + Calendar.getInstance().get(Calendar.YEAR);

                Reservation reservation = reservationRepository.findById(numReservation).orElse(null);
                if (reservation == null) {
                    reservation = new Reservation();
                    reservation.setIdRes(numReservation);
                    reservation.setEstValide(true);
                    reservation.setChambre(availableChambre);
                    //reservation.setAU(Calendar.getInstance().get(Calendar.YEAR));
                    availableChambre.getReservations().add(reservation);
                }

                reservation.getEtudiants().add(etudiant);
                entityManager.flush();
                entityManager.clear();
                return reservationRepository.save(reservation);
            }
        }
        // If no room is available, throw an exception
        throw new RuntimeException("Aucune chambre disponible pour cet étudiant.");
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
            // Rechercher l'étudiant avec le CIN
            Etudiant etudiant = etudiantRepository.findBycin(cinEtudiant);
            if (etudiant == null) {
                throw new RuntimeException("Étudiant non trouvé avec le CIN " + cinEtudiant);
            }

            // Trouver la réservation active pour cet étudiant
            Reservation reservation = reservationRepository.findByEtudiantsContains(etudiant);
            if (reservation == null) {
                throw new RuntimeException("Aucune réservation active trouvée pour l'étudiant avec le CIN " + cinEtudiant);
            }

            // Désaffecter l'étudiant de la réservation
            reservation.getEtudiants().remove(etudiant);

            // Si la réservation ne contient plus d'étudiants, la marquer comme invalide
            if (reservation.getEtudiants().isEmpty()) {
                reservation.setEstValide(false);

                // Mettre à jour la capacité de la chambre associée
                Chambre chambre = reservation.getChambre();
                if (chambre != null) {
                    chambre.getReservations().remove(reservation);
                }
            }

            // Sauvegarder les modifications
            return reservationRepository.save(reservation);
        }

   /* @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        return
    }*/

}
/*
    @Override
    public Reservation ajouterReservation1(long idBloc, long cin) {
        // Rechercher l'étudiant par son CIN
        Etudiant etudiant = etudiantRepository.findBycin(cin);
        if (etudiant == null) {
            throw new RuntimeException("Étudiant non trouvé avec le CIN " + cin);
        }

        // Rechercher la chambre par son ID
        Chambre chambre = chambreRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable avec l'ID: " + idBloc));

        // Vérifier si la capacité maximale de la chambre est atteinte
        int capaciteMax = chambre.getTypeC().getCapacite();
        int reservationsExistantes = chambre.getReservations().stream()
                .mapToInt(reservation -> reservation.getEtudiants().size())
                .sum();

        // Si la capacité est atteinte, on lance une exception
        if (reservationsExistantes >= capaciteMax) {
            throw new RuntimeException("La capacité maximale de la chambre est atteinte");
        }

        // Générer le numéro de réservation
        String numReservation = chambre.getNumchambre() + "-" + chambre.getBloc().getNomBloc() + "-" + Calendar.getInstance().get(Calendar.YEAR);

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setIdRes(numReservation);
        reservation.setEstValide(true);
        reservation.setChambre(chambre);
        reservation.setAU(Calendar.getInstance().get(Calendar.YEAR));  // Date actuelle complète
        reservation.getEtudiants().add(etudiant);

        // Sauvegarder la réservation dans la base de données
        return reservationRepository.save(reservation);
    }*/


    /*
@Override
public Reservation ajouterReservation1(long idBloc, long cin) {
    // Rechercher l'étudiant par son CIN
    Etudiant etudiant = etudiantRepository.findBycin(cin);
    if (etudiant == null) {
        throw new RuntimeException("Étudiant non trouvé avec le CIN " + cin);
    }

    // Rechercher le bloc par son ID
    Bloc bloc = blocRepository.findById(idBloc)
            .orElseThrow(() -> new RuntimeException("Bloc introuvable avec l'ID: " + idBloc));

    // Accéder aux chambres du bloc
    List<Chambre> chambres = new ArrayList<>(bloc.getChambres());

    // Vérifier si des chambres disponibles
    Chambre chambreDisponible = null;
    for (Chambre chambre : chambres) {
        int capaciteMax = chambre.getTypeC().getCapacite();
        int reservationsExistantes = chambre.getReservations().stream()
                .mapToInt(reservation -> reservation.getEtudiants().size())
                .sum();

        // Vérifier si la capacité maximale de la chambre est atteinte
        if (reservationsExistantes < capaciteMax) {
            chambreDisponible = chambre;
            break;
        }
    }

    // Si aucune chambre disponible, on lance une exception
    if (chambreDisponible == null) {
        throw new RuntimeException("Aucune chambre disponible dans le bloc");
    }

    // Générer le numéro de réservation
    String numReservation = chambreDisponible.getNumchambre() + "-" + chambreDisponible.getBloc().getNomBloc() + "-" + Calendar.getInstance().get(Calendar.YEAR);

    // Créer la réservation
    Reservation reservation = new Reservation();
    reservation.setIdRes(numReservation);
    reservation.setEstValide(true);
    reservation.setChambre(chambreDisponible);
    reservation.setAU(Calendar.getInstance().get(Calendar.YEAR));  // Date actuelle complète
    reservation.getEtudiants().add(etudiant);

    // Sauvegarder la réservation dans la base de données
    return reservationRepository.save(reservation);
}

     */






