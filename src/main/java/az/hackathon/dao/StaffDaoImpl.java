package az.hackathon.dao;

import az.hackathon.model.Repair;
import az.hackathon.model.Role;
import az.hackathon.model.Staff;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StaffDaoImpl implements StaffDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Staff getStaffByUsernameAndPassword(Staff staff) {
        String sql="select * from staff s where s.username=? and s.password=? and s.id_role=?";
        try{
            Staff result= jdbcTemplate.query(sql,new Object[]{staff.getUsername(),staff.getPassword()
                    ,staff.getRole().getIdRole()}, new RowMapper<Staff>() {
                @Override
                public Staff mapRow(ResultSet rs, int i) throws SQLException {
                    Staff s1=new Staff();
                    s1.setIdStaff(rs.getInt("id_staff"));
                    s1.setFullName(rs.getString("full_name"));
                    s1.setContactNumber(rs.getString("contact_number"));
                    s1.setUsername(rs.getString("username"));
                    s1.setPassword(rs.getString("password"));
                    Role role=new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    s1.setRole(role);
                    return s1;
                }
            }).get(0);

            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean updateStaffPassword(String password,int staffId) {
        String sql="update staff set password=? where id_staff=? ";

        try{
            jdbcTemplate.update(sql,new Object[]{password,staffId});
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public List<Staff> getAllStaff() {
        String sql="select * from staff s left join repair r on s.id_staff=r.id_staff where s.id_role=1";

        try{
         List<Staff> result= jdbcTemplate.query(sql, new RowMapper<Staff>() {
                @Override
                public Staff mapRow(ResultSet rs, int i) throws SQLException {
                    Staff s1=new Staff();
                    s1.setIdStaff(rs.getInt("id_staff"));
                    s1.setFullName(rs.getString("full_name"));
                    s1.setContactNumber(rs.getString("contact_number"));
                    s1.setUsername(rs.getString("username"));
                    s1.setPassword(rs.getString("password"));
                    Role role=new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    s1.setRole(role);
                    Repair repair=new Repair();
                    repair.setActive(rs.getInt("active"));
                    s1.setRepair(repair);

                    return s1;
                }
            });

         return  result;


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addNewRepairer(Staff staff) {
        String sql="insert into staff(full_name,contact_number,username,password,id_role)"+
                "  values(?,?,?,?,?)";

        try{
            jdbcTemplate.update(sql,new Object[]{staff.getFullName(),staff.getContactNumber(),staff.getUsername(),staff.getPassword(),staff.getRole().getIdRole()});
            

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }
}
