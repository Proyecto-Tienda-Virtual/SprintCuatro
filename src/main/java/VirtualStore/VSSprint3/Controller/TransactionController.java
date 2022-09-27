package VirtualStore.VSSprint3.Controller;


import VirtualStore.VSSprint3.Model.Transaction;
import VirtualStore.VSSprint3.Services.IServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class TransactionController {
    @Autowired
    IServiceTransaction TransactionBDX;
    //Este es el index
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/movements")
    public String getAll(Model model){
        model.addAttribute("TransactionsList", TransactionBDX.getAll());
        return "transaction-list";
    }


    @GetMapping("/new-transaction")
    private String FormTransaction(Transaction transaction){
        return "new-transaction";
    }

    //Crea la empresa realizando un POST
    @PostMapping("/movements")
    public String Create(Transaction transaction){
        TransactionBDX.Create(transaction);
        return "redirect:/movements";
    }


    //Eliminar Transaccion
    @GetMapping("/delete-transaction/{id}")
    public String deleteTransaction(@PathVariable(name = "id") Long id){
        TransactionBDX.delete(id);
        return "redirect:/movements";
    }

    @GetMapping("/edit-transaction/{id}")
    public String getbyId(@PathVariable("id") Long id, Model model){
        Transaction transaction = TransactionBDX.getbyId(id);
        model.addAttribute("transaction", transaction);
        return "update-transaction";}

    //Editar Transaccion
    @PostMapping("/update-transaction/{id}")
    public String updateTransaction (@PathVariable("id") Long id,Transaction transaction){
        TransactionBDX.updateTransaction(transaction);
        return "redirect:/movements";
    }


}