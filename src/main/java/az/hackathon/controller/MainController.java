package az.hackathon.controller;


import az.hackathon.model.*;
import az.hackathon.service.RepairService;
import az.hackathon.service.SecurityService;
import az.hackathon.service.StaffService;
import az.hackathon.service.UserService;
import az.hackathon.util.Constants;
import az.hackathon.util.Crypto;
import az.hackathon.util.Filter;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    StaffService staffService;
    @Autowired
    RepairService repairService;
    @Autowired
    UserService userService;






    @RequestMapping("/")
    public String indexPageHandler(HttpSession session){
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
    public String userPageHandler(HttpSession session, Model model){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        return "user-track";
    }

    @RequestMapping("/user/track")
    public String userTrackPageHandler(@RequestParam("t")String trackingNumber, HttpSession session, Model model){
        System.out.println(trackingNumber);
        Repair repair = repairService.getRepairByTrackingNumber(trackingNumber);

        if (repair==null){
            session.setAttribute("message", Constants.ERROR_INVALID_TRACKING_NUMBER);
            return "redirect:/user/search";
        }
        model.addAttribute("repair", repair);
        return "user-search";
    }

    @RequestMapping("/staff/main")
    public String staffPageHandler(Model model, HttpSession session){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        Staff staff = (Staff) session.getAttribute("staff");
        List<Repair> repairList = repairService.getRepairListByStaffId(staff.getIdStaff());
        model.addAttribute("repairList", repairList);
        return "repairer-all";
    }

    @RequestMapping("/staff/history")
    public String staffHistoryPageHandler(Model model, HttpSession session){
        Staff staff = (Staff) session.getAttribute("staff");
        List<Repair> repairList = repairService.getArchiveRepairListByStaffId(staff.getIdStaff());
        model.addAttribute("repairList", repairList);
        return "repairer-history-all";
    }

    @RequestMapping("/staff/history/{id}")
    public String repairHistoryPageHandler(@PathVariable("id") int id, Model model, HttpSession session){
        if (session.getAttribute("message")!=null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        Repair repair = repairService.getArchiveRepairById(id);

        if (repair==null){
            return "redirect:/staff/history";
        }

        model.addAttribute("repair", repair);

        return "repairer-history-preview";
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


    @RequestMapping("/staffManager/main")
    public String managerPageHandler(Model model){
        List<Staff> staffList = staffService.getAllStaff();

        System.out.println("staffList = " + staffList);

        Set<Staff> staffSet = new HashSet<>(staffList);



        for (Staff s : staffSet){
            int active = 0;
            int passive = 0;
            for (Repair r : s.getRepairs()){
                if (r.getActive()==1){
                    active++;
                }

                if(r.getActive()==2){
                    passive++;
                }
            }

            s.setActiveTasks(active);
            s.setDeactiveTasks(passive);
        }


        model.addAttribute("staffList", staffSet);
        return "manager-repairers";
    }

    @RequestMapping("/staffManager/clients")
    public String managerClientsPageHandler(Model model){
        List<User> userList = userService.getAllUser();
        Set<User> userSet = new HashSet<>(userList);

        for (User s : userSet){
            int active = 0;
            int passive = 0;
            for (Repair r : s.getRepairs()){
                if (r.getActive()==1){
                    active++;
                }

                if(r.getActive()==2){
                    passive++;
                }
            }

            s.setActiveTasks(active);
            s.setDeactiveTasks(passive);
        }
        model.addAttribute("userList", userSet);
        return "manager-clients";
    }

    @RequestMapping("/staffManager/new-repairer")
    public String newRepairerPageHandler(Model model){
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        return "manager-create-repairer";
    }

    @RequestMapping("/staffManager/new-repair")
    public String newRepairPageHandler(Model model){
        List<Staff> staffList = staffService.getAllStaff();

        System.out.println("staffList = " + staffList);

        Set<Staff> staffSet = new HashSet<>(staffList);



        for (Staff s : staffSet){
            int active = 0;
            int passive = 0;
            for (Repair r : s.getRepairs()){
                if (r.getActive()==1){
                    active++;
                }

                if(r.getActive()==2){
                    passive++;
                }
            }

            s.setActiveTasks(active);
            s.setDeactiveTasks(passive);
        }


        model.addAttribute("staffList", staffSet);
        return "manager-create-repair";
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
        try {
            staff.setPassword(Crypto.pwdToHash(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
             return "redirect:/staffManager/main";
         }

    }


}
