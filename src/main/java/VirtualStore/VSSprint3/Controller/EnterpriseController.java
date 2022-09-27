package VirtualStore.VSSprint3.Controller;


import VirtualStore.VSSprint3.Model.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class EnterpriseController {

    @Autowired
    VirtualStore.VSSprint3.Services.IServiceEnterprise IServiceEnterprise;

    //Trae la lista de las empresas del supabase
    @GetMapping("/enterprises")
    public String getAll(Model model){     //Llama un String y creamos la clase Model y el objeto model
       model.addAttribute("Enterprises",IServiceEnterprise.getAll()); //Enterprises guarda la lista de empresas
       return "Enterprise";  //retorna el archivo HTML
    }

    //Crea un nuevo registro en el formulario
    @GetMapping("/newEnterprise")
    private String FormEnterprise(Enterprise enterprise){
        return "newEnterprise";
    }

    //Crea la empresa realizando un POST
    @PostMapping("/enterprises")
    public String Create(Enterprise enterprise){
        IServiceEnterprise.Create(enterprise);
        return "redirect:/enterprises";
    }

    //Para editar el registro
    @GetMapping("/enterprise/edit/{id}")
    public String getbyId(@PathVariable("id") Long id, Model model){
        Enterprise enterprise = IServiceEnterprise.getbyId(id);
        model.addAttribute("enterprise", enterprise);
        return "updateEnterprise";
    }

    //Para actualizar un registro en el formulario
    @PostMapping("/enterprise/updateEnterprise/{id}")
    public String updateEnterprise (@PathVariable("id") Long id,Enterprise enterprise){
        IServiceEnterprise.updateEnterprise(enterprise);
        return "redirect:/enterprises";
    }

   //Borrar un registro por ID
    @GetMapping("/enterprise/delete/{id}")
    public String delete(@PathVariable ("id") Long id){
        IServiceEnterprise.delete(id);
        return  "redirect:/enterprises";

    }

}
