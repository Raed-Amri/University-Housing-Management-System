package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.models.Chambre;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {

}
