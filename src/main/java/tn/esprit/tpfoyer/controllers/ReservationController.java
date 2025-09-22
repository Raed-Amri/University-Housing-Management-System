package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Reservation;
import tn.esprit.tpfoyer.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    @Autowired
    IReservationService reservationService;
    @PostMapping("/add")
    public Reservation addReservation(@RequestBody Reservation reservation) {

        return reservationService.addReservation(reservation);
    }
    @GetMapping("/get")
    public List<Reservation> retrieveReservations(){
        return reservationService.retrieveReservations();
    }
    @GetMapping("/get/{id}")
    public Reservation retrieveReservation(@PathVariable String id ){
        return reservationService.retrieveReservation(id);
    }
    @PutMapping("/put/{id}")
    public Reservation updateReservation(@PathVariable String id,@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation,id);
    }
    @DeleteMapping("/delete/{id}")
    public void  removeReservation(@PathVariable String id){
        reservationService.removeReservation(id);
    }

    @PostMapping("/ajouterReservation/{idBloc}/{cin}")
    public Reservation ajouterReservation(@PathVariable long idBloc,@PathVariable long cin){
        return reservationService.ajouterReservation(idBloc,cin);
    }
    @PutMapping("/annulerReservation/{cin}")
    public Reservation annulerReservation(@PathVariable long cin){
        return reservationService.annulerReservation(cin);
    }
   /* @PostMapping("/ajouterReservation1/{idBloc}/{cin}")
    public Reservation ajouterReservation1(@PathVariable long idBloc,@PathVariable long cin){
        return reservationService.ajouterReservation1(idBloc,cin);
    }

    */
}
