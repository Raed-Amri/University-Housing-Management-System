package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.models.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c);
    Chambre updateChambre (Chambre c);
    Chambre retrieveChambre (long idChambre);
    List<Chambre> getChambresParNomUniversite( String nomUniversite) ;
    List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC) ;

}
