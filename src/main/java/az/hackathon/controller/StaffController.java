package az.hackathon.controller;

import az.hackathon.model.Role;
import az.hackathon.model.Staff;
import az.hackathon.service.StaffService;
import az.hackathon.util.Constants;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping("/changePassword")
    public String changeStaffPassword(@RequestParam("oldPwd") String oldPwd,
                                      @RequestParam("newPwd") String newPwd,
                                      HttpSession session){

        Staff staff = (Staff) session.getAttribute("staff");

        if (!Validator.validate(oldPwd, newPwd)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            if (staff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){
                return "redirect:/staff/main";
            }else{
                return "redirect:/staffManager/main";
            }

        }


        String oldPassword = staff.getPassword();
        if (!oldPassword.equals(oldPwd)){
            session.setAttribute("message", Constants.ERROR_INVALID_CREDENTIALS);
            if (staff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){
                return "redirect:/staff/main";
            }else{
                return "redirect:/staffManager/main";
            }
        }

        boolean result = staffService.updateStaffPassword(newPwd, staff.getIdStaff());
        staff.setPassword(newPwd);
        session.setAttribute("staff",staff);

        if (staff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){
            return "redirect:/staff/main";
        }else{
            return "redirect:/staffManager/main";
        }
    }

    @RequestMapping("/add-repairer")
    public String addRepairer(@RequestParam("fullName") String fullName,
                              @RequestParam("contactNumber") String contactNumber,
                              @RequestParam("username") String username,
                              @RequestParam("pwd") String pwd,
                              HttpSession session){

        if (!Validator.validate(fullName, contactNumber, username, pwd)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect:/staffManager/new-repairer";
        }

        Staff staff = new Staff();
        staff.setFullName(fullName);
        staff.setContactNumber(contactNumber);
        staff.setUsername(username);
        staff.setPassword(pwd);
        Role role = new Role();
        role.setIdRole(1);
        staff.setRole(role);

        boolean result = staffService.addNewRepairer(staff);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
            return "redirect:/staffManager/new-repairer";
        }

        return "redirect:/staffManager/main";


    }
}
