package az.hackathon.controller;


import az.hackathon.model.Repair;
import az.hackathon.model.Role;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/logout")
    public String logoutHandler(HttpSession session){
       session.invalidate();
       return "redirect:/login";
    }

    @RequestMapping("/user/search")
    public String userPageHandler(){
        return "user-search";
    }

    @RequestMapping("/staff/main")
    public String staffPageHandler(Model model, HttpSession session){
        Staff staff = (Staff) session.getAttribute("staff");
        List<Repair> repairList = repairService.getRepairListByStaffId(staff.getIdStaff());
        System.out.println(repairList);
        model.addAttribute("repairList", repairList);
        return "repairer-all";
    }

    @RequestMapping("/staff/repair/{id}")
    public String repairPageHandler(@PathVariable("id") int id, Model model, HttpSession session){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        Repair repair = repairService.getRepairById(id);

        if (repair==null){
            return "redirect:/staff/main";
        }

        model.addAttribute("repair", repair);

        return "repairer-preview";
    }


    @RequestMapping("/manager/main")
    public String managerPageHandler(){
        return "manager-main";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("pwd") String password,
                          @RequestParam("optradio") int idRole,
                          HttpSession session){

        //TODO: getter setter for Staff
        if (!Validator.validate(username, password, idRole)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect:/login";
        }

        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setPassword(password);
        Role role = new Role();
        role.setIdRole(idRole);
        staff.setRole(role);

        Staff authorizedStaff = staffService.getStaffByUsernameAndPassword(staff);

        if (authorizedStaff==null){
            session.setAttribute("message", Constants.ERROR_INVALID_CREDENTIALS);
            return "redirect:/login";
        }


        session.setAttribute("staff", authorizedStaff);

         if (authorizedStaff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){
             return "redirect:/staff/main";
         }else{
             return "redirect:/manager/main";
         }

    }


}
