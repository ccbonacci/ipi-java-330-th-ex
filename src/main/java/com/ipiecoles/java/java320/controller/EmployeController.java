package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.util.Optional;


@Controller
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private EmployeRepository employeRepository;


    // Récupérer un employe par id ----------------------------
    @GetMapping(value = "/{id}")
    public String getEmployeById(
            @PathVariable Long id,
            final ModelMap model)
    {
        Optional<Employe> employeOptional = employeRepository.findById(id);
        // Ici gerer l'erreur 404
        model.put("employe", employeOptional.get());
        return "detail";
    }

    // Récupérer un employe par matricule ----------------------------
    @GetMapping(value = "", params = "matricule")
    public String getEmployeByMatricule(
            @RequestParam String matricule,
            final ModelMap model)
    {
        Employe employe = employeRepository.findByMatricule(matricule);
        // Ici gerer l'erreur 404
        model.put("employe", employe);
        model.put("nombreEmployes", employeRepository.count());
        return "detail";
    }



     // Récupérer la liste des employés  ------------------------------

    @GetMapping(value = "")
    public String ListEmployes(
            final ModelMap model,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty",defaultValue = "matricule") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection)
    {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection),sortProperty);
        Page<Employe> pageEmploye = employeRepository.findAll(pageRequest);
        //pageEmploye.
        model.put("employes", pageEmploye);
        model.put("pageNumber", page + 1);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("start", page * size + 1);
        model.put("end", (page +1 ) * size + pageEmploye.getNumberOfElements() );
        model.put("end", (page) * size + pageEmploye.getNumberOfElements());

        return "listeEmployes";
    }


    // Créer un employé  ------------------------------

    @GetMapping(value = "/new/{typeEmploye}")
    public String newEmploye(@PathVariable String typeEmploye, final ModelMap model){
        switch (typeEmploye.toLowerCase()){
            case "technicien": model.put("employe", new Technicien()); break;
            case "commercial": model.put("employe", new Commercial()); break;
            case "manager": model.put("employe", new Manager()); break;
        }
        return "detail";
    }


    // Création d'un employé  ------------------------------
    @PostMapping(value= "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createEmploye(Employe employe, final ModelMap model){
        return "detail";
    }

    //1 Request Mapping pour chaque type d'employé pour gérer la sauvegarde
        ///employes/commercial/{id}
        ///employes/technicien/{id}
        ///employes/manager/{id}
        //Suppression => rediriger vers la liste des employés




    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public RedirectView deleteEmploye(){
        //Faire la suppression
        return new RedirectView("/employes");
    }




}
