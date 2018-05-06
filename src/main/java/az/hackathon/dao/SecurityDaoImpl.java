package az.hackathon.dao;

import az.hackathon.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SecurityDaoImpl implements SecurityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Action> getActionListByRoleId(int idRole) {



        String sql="select * from action a join role_action ra on a.id_action=ra.id_action \n" +
                "join role r on ra.id_role=r.id_role where r.id_role=?";
        try{

            List<Action> actions=jdbcTemplate.query(sql, new Object[]{idRole}, new RowMapper<Action>() {
                @Override
                public Action mapRow(ResultSet resultSet, int i) throws SQLException {
                    Action action=new Action();
                    action.setActionName(resultSet.getString("action_name"));


                    return action;
                }
            });

            return actions;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
