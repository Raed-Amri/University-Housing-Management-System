package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Chambre;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.ChambreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlocService implements IBlocService{
    @Autowired

    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> retrieveBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {

        Bloc existingBloc = blocRepository.findById(bloc.getIdBloc())
                .orElseThrow(() -> new RuntimeException("Bloc not found with id: " + bloc.getIdBloc()));


        existingBloc.setNomBloc(bloc.getNomBloc());
        existingBloc.setCapaBloc(bloc.getCapaBloc());
        existingBloc.setFoyer(bloc.getFoyer());
        existingBloc.setChambres(bloc.getChambres());

        return blocRepository.save(existingBloc);
    }

    @Override
    public void removeBloc(long idBloc) {

        Bloc existingBloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc not found with id: " + idBloc));


        blocRepository.delete(existingBloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numchambre, long idBloc) {
             Bloc  bloc =blocRepository.findById(idBloc).orElseThrow(() -> new RuntimeException("bloc non trouvé avec l'ID " + idBloc));
            List<Chambre> chambres = chambreRepository.findByNumchambreIn(numchambre);
            for (Chambre chambre : chambres) {
                chambre.setBloc(bloc);
                blocRepository.save(bloc); // Sauvegarder le bloc avec la chambre associée
            }
            //bloc.getChambers().addAll(chambres);
            //chambres.addAll(chambres);
        return bloc;

    }


}
