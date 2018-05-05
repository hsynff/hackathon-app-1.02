package az.hackathon.controller;


import az.hackathon.model.Repair;
import az.hackathon.model.Staff;
import az.hackathon.service.RepairService;
import az.hackathon.service.StaffService;
import az.hackathon.util.Constants;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    StaffService staffService;
    @Autowired
    RepairService repairService;


    @RequestMapping("/")
    public String indexPageHandler(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginPageHandler(HttpSession session, Model model){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }

        return "login";
    }

    @RequestMapping("/user/search")
    public String userPageHandler(){
        return "user-search";
    }

    @RequestMapping("/staff/main")
    public String staffPageHandler(Model model, HttpSession session){
        return "staff-main";
    }

    @RequestMapping("/staff/repair/{id}")
    public String repairPageHandler(@PathVariable("id") int id, Model model, HttpSession session){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        Repair repair = repairService.getRepairById(id);

        if (repair==null){
            return "redirect: /staff/main";
        }

        model.addAttribute("repair", repair);

        return "staff-repair";
    }


    @RequestMapping("/manager/main")
    public String managerPageHandler(){
        return "manager-main";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute("staff")Staff staff, HttpSession session){
        //TODO: getter setter for Staff
        if (!Validator.validate(staff.getUsername(), staff.getPassword(), staff.getRole().getIdRole())){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect: /login";
        }

        Staff authorizedStaff = staffService.getStaffByUsernameAndPassword(staff);

        if (authorizedStaff==null){
            session.setAttribute("message", Constants.ERROR_INVALID_CREDENTIALS);
            return "redirect: /login";
        }


        session.setAttribute("staff", staff);

         if (staff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){
             return "redirect: /staff/main";
         }else{
             return "redirect: /manager/main";
         }

    }


}
