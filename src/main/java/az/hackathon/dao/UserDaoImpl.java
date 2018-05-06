package az.hackathon.dao;

import az.hackathon.model.Repair;
import az.hackathon.model.Role;
import az.hackathon.model.Staff;
import az.hackathon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByFin(String fin) {
        String sql="select * from user u where u.fin=?";
        try{

            User user=jdbcTemplate.query(sql,new Object[]{fin}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User u=new User();
                    u.setIdUser(rs.getInt("id_user"));
                    u.setFullName(rs.getString("full_name"));
                    u.setEmail(rs.getString("email"));
                    u.setAddress(rs.getString("address"));
                    u.setContactNumber(rs.getString("contact_number"));
                    Role role=new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    u.setRole(role);
                    u.setFin(rs.getString("fin"));
                    return u;
                }
            }).get(0);

            return user;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public int createNewUser(User user) {
        String sql="insert into user(full_name,email,address,contact_number,id_role,fin) values(?,?,?,?,?,?)";


        try{


            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_user"});
                    ps.setString(1, user.getFullName());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getAddress());
                    ps.setString(4,user.getContactNumber());
                    ps.setInt(5,user.getRole().getIdRole());
                    ps.setString(6,user.getFin());
                    return ps;
                }
            }, holder);

            return (Integer)holder.getKey();

        }catch (Exception e){

        }

        return 0;
    }

    @Override
    public List<User> getAllUser() {

        String sql="select * from user u where u.fin=?";
        try{

            List<User> result=jdbcTemplate.query(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User u=new User();
                    u.setIdUser(rs.getInt("id_user"));
                    u.setFullName(rs.getString("full_name"));
                    u.setEmail(rs.getString("email"));
                    u.setAddress(rs.getString("address"));
                    u.setContactNumber(rs.getString("contact_number"));
                    Role role=new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    u.setRole(role);
                    u.setFin(rs.getString("fin"));
                    Repair repair=new Repair();
                    repair.setActive(rs.getInt("active"));
                    u.setRepair(repair);

                    return u;
                }
            });

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
