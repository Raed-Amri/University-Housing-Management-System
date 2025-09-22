package tn.esprit.tpfoyer.services;


import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.repositories.BlocRepository;

import java.util.List;

public interface IBlocService {
    Bloc addBloc(Bloc bloc);
    List<Bloc> retrieveBlocs();
    Bloc retrieveBloc (long idBloc);
    Bloc updateBloc (Bloc bloc);
    void removeBloc (long idBloc);
    public Bloc affecterChambresABloc(List<Long> numchambre, long idBloc) ;


}
