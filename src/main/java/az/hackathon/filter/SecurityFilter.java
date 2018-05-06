package az.hackathon.filter;

import az.hackathon.model.Action;
import az.hackathon.model.Staff;
import az.hackathon.service.SecurityService;
import az.hackathon.service.SecurityServiceImpl;
import az.hackathon.util.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String path=request.getServletPath();
        SecurityService securityService=new SecurityServiceImpl();

        HttpSession session=request.getSession();
        if(session.getAttribute("staff")==null){
            List<Action> actions=securityService.getActionListByRoleId(Constants.DEFAULT_ROLE_ID);
            boolean validateAction=false;
            for(Action action :actions){
                if(path.contains(action.getActionName())){
                    validateAction=true;
                    break;
                }
            }
            if(validateAction){
                chain.doFilter(req,resp);
            }else{
                response.sendRedirect("/login");

            }
        }else {
            Staff staff=(Staff)request.getSession().getAttribute("staff");
            int roleId=staff.getRole().getIdRole();
            List<Action> actions=securityService.getActionListByRoleId(roleId);

            boolean validateAction=true;

            for(Action action:actions){
                if(path.contains(action.getActionName())){
                    validateAction=true;
                }
            }

            if(validateAction){
                chain.doFilter(req,resp);
            }else{
                if(roleId==2){
                    response.sendRedirect("/staffManager/main");
                }

                if(roleId==1){
                    response.sendRedirect("/staff/main");
                }


            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
