package az.hackathon.dao;


import az.hackathon.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class RepairDaoImpl implements RepairDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Repair getRepairById(int id) {
        String sql="select r.id_repair,m.model,d.brand,p.percent,r.price,r.start_date,u.full_name,u.fin,r.tracking_number,u.email,\n" +
                "r.title,p.comment,p.date,\n" +
                "u.address,u.contact_number\n" +
                " from repair r join device d on r.id_device=d.id_device join model m on d.id_model=m.id_model\n" +
                " join progress p on r.id_repair=p.id_repair\n" +
                "join user u on r.id_user=u.id_user where r.id_repair=? and r.active=1";


        try{

            List<Repair> result= jdbcTemplate.query(sql,new Object[]{id}, new RowMapper<Repair>() {
                @Override
                public Repair mapRow(ResultSet rs, int i) throws SQLException {
                    Repair r=new Repair();
                    r.setIdRepair(rs.getInt("id_repair"));
                    Model m=new Model();
                    m.setModel(rs.getString("model"));

                    Device device=new Device();
                    device.setModel(m);
                    device.setBrand(rs.getString("brand"));
                    r.setDevice(device);
                    Progress progress=new Progress();
                    progress.setPercent(rs.getInt("percent"));
                    progress.setComment(rs.getString("comment"));
                    progress.setDate(rs.getDate("date"));
                    r.setTitle(rs.getString("title"));
                    r.setProgress(progress);
                    r.setPrice(rs.getInt("price"));
                    r.setStartDate(rs.getDate("start_date"));
                    User u=new User();
                    u.setFullName(rs.getString("full_name"));
                    u.setFin(rs.getString("fin"));
                    u.setEmail(rs.getString("email"));
                    u.setContactNumber(rs.getString("contact_number"));
                    u.setAddress(rs.getString("address"));
                    r.setUser(u);
                    r.setTrackingNumber(rs.getString("tracking_number"));

                    return r;
                }
            });

            List<Progress> progressList=new ArrayList<>();
            for(Repair repair:result){
                progressList.add(repair.getProgress());
            }
            Repair repair=result.get(0);
            repair.setProgresses(progressList);
            return repair;

        }catch (Exception e){
            e.printStackTrace();
        }


       return null;
    }

    @Override
    public boolean updateRepairStatusById(int id, Progress progress) {
        String sql="insert into progress(percent,comment,date,id_repair) values(?,?,?,?)";
        try{
            jdbcTemplate.update(sql,new Object[]{progress.getPercent(),progress.getComment(),
                    progress.getDate(),id});
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return false;


    }

    @Override
    public List<Repair> getRepairListByStaffId(int id) {
        String sql="select  r.id_repair,m.model,d.brand,p.percent,r.price,r.title,r.start_date,u.full_name,u.fin,r.tracking_number\n" +
                " from repair r join device d on r.id_device=d.id_device join model m on d.id_model=m.id_model\n" +
                " join progress p on r.id_repair=p.id_repair\n" +
                "join user u on r.id_user=u.id_user where r.id_staff=? and r.active=1 and \n" +
                "p.percent=(select max(progress.percent) from progress group by(progress.id_repair))\n" +
                " group by (r.id_repair)";

        try{
            List<Repair> result= jdbcTemplate.query(sql,new Object[]{id}, new RowMapper<Repair>() {
                @Override
                public Repair mapRow(ResultSet rs, int i) throws SQLException {
                    Repair r=new Repair();
                    r.setIdRepair(rs.getInt("id_repair"));
                    Model m=new Model();
                    m.setModel(rs.getString("model"));

                    Device device=new Device();
                    device.setModel(m);
                    device.setBrand(rs.getString("brand"));
                    r.setDevice(device);
                    Progress progress=new Progress();
                    progress.setPercent(rs.getInt("percent"));
                    r.setTitle(rs.getString("title"));
                    r.setProgresses(Arrays.asList(progress));
                    r.setPrice(rs.getInt("price"));
                    r.setStartDate(rs.getDate("start_date"));
                    User u=new User();
                    u.setFullName(rs.getString("full_name"));
                    u.setFin(rs.getString("fin"));
                    r.setUser(u);
                    r.setTrackingNumber(rs.getString("tracking_number"));

                    return r;
                }
            });

            return result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateRepairActiveById(int id, int active, Date endDate) {
        return false;
    }
}
