package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.models.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    List<Chambre> findByNumchambreIn(List<Long> numchambre);
    List<Chambre> findByBloc_Foyer_Universite_nomUniverse(String nomUniverse);
    List<Chambre> findByBlocIdBloc(long idBloc);
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresByBlocAndType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);
    @Query("select c from Chambre c where c.bloc.foyer.universite.nomUniverse =:nomUniverse and c.typeC=:typeC and" +
            " c.idChambre not in ( SELECT r.chambre.idChambre FROM Reservation r   WHERE FUNCTION('YEAR', r.AU) = FUNCTION('YEAR', CURRENT_DATE))")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre( String nomUniverse,TypeChambre typeC) ;
    @Query("select c from Chambre c,Reservation r where c.bloc.foyer.universite.nomUniverse =:nomUniverse and c.typeC=:typeC and r.idRes not like cast(year(current_date )as string ) ")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre1( String nomUniverse,TypeChambre typeC) ;
}
