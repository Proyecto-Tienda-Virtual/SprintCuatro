package VirtualStore.VSSprint3.Controller;

import VirtualStore.VSSprint3.Model.Employee;
import VirtualStore.VSSprint3.Services.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    IServiceEmployee iServiceEmployee;

    //Listar todos los usuarios
    @GetMapping("/employees")
    public String getAll(Model model){
        model.addAttribute("Employees", iServiceEmployee.getAll());
        return "Employee";
    }

    //Formulario de creacion de usuario
    @GetMapping("/newEmployee")
    private String FormEmployee(Employee employee){
        return "newEmployee";
    }

    //Metodo para guardar el usuario
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        iServiceEmployee.save(employee);
        return "redirect:/employees";
    }

    //Eliminar Usuario
    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") Long id){
        iServiceEmployee.delete(id);
        return "redirect:/employees";
    }

    //Editar usuario
    @RequestMapping("/edit/{id}")
    public ModelAndView FormEditEmployee(@PathVariable(name = "id") Long id){
        ModelAndView model = new ModelAndView("editEmployee");

        Employee employee = iServiceEmployee.get(id);
        model.addObject("employee", employee);
        return model;
    }




}
