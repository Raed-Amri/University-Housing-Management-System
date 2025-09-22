package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.models.Bloc;
import tn.esprit.tpfoyer.services.IBlocService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("blocs")
public class BlocController {
@Autowired
IBlocService blocService;

    @PostMapping("/add")
    public Bloc addBloc(@RequestBody Bloc bloc) {

        return blocService.addBloc(bloc);
    }
    @GetMapping("/get")
    public List<Bloc>  retrieveBlocs(){
        return blocService.retrieveBlocs();
    }
    @GetMapping("/get/{id}")
    public Bloc retrieveBloc(@PathVariable long id ){
        return blocService.retrieveBloc(id);
    }
    @PutMapping("/put")
    public Bloc updateBloc(@RequestBody Bloc bloc){
        return blocService.updateBloc(bloc);
    }
    @DeleteMapping("/delete/{id}")
    public void  removeBloc(@PathVariable long id){
         blocService.removeBloc(id);
    }
    @PutMapping("/AffecterChambresABloc/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numchambre, @PathVariable long idBloc) {
        return blocService.affecterChambresABloc(numchambre, idBloc);
    }


}
