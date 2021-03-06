package az.hackathon.dao;


import az.hackathon.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
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
//fixme
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
                "p.percent=(select max(progress.percent) from progress where progress.id_repair=r.id_repair group by(progress.id_repair))\n" +
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
        String sql="update repair set active=?,end_date=? where id_repair=?";

        try{
            jdbcTemplate.update(sql,new Object[]{active,endDate,id});

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public int getTrackingNumberCount(String trackingNumber) {
        String sql="select count(*) as count from repair where tracking_number=? and active=1";

        try{
            Integer result= jdbcTemplate.query(sql,new Object[]{trackingNumber}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int i) throws SQLException {
                    Integer c=rs.getInt("count");

                    return c;
                }
            }).get(0);

            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Repair> getArchiveRepairListByStaffId(int id) {
        String sql="select  r.id_repair,m.model,d.brand,p.percent,r.price,r.title,r.start_date,r.end_date,u.full_name,u.fin,r.tracking_number\n" +
                " from repair r join device d on r.id_device=d.id_device join model m on d.id_model=m.id_model\n" +
                " join progress p on r.id_repair=p.id_repair\n" +
                "join user u on r.id_user=u.id_user where r.id_staff=? and r.active=2 and \n" +
                "p.percent=(select max(progress.percent) from progress where progress.id_repair=r.id_repair group by(progress.id_repair))\n" +
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
                    r.setEndDate(rs.getDate("end_date"));
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
    public Repair getArchiveRepairById(int id) {
        String sql="select r.id_repair,m.model,d.brand,p.percent,r.price,r.start_date,r.end_date,u.full_name,u.fin,r.tracking_number,u.email,\n" +
                "r.title,p.comment,p.date,\n" +
                "u.address,u.contact_number\n" +
                " from repair r join device d on r.id_device=d.id_device join model m on d.id_model=m.id_model\n" +
                " join progress p on r.id_repair=p.id_repair\n" +
                "join user u on r.id_user=u.id_user where r.id_repair=? and r.active=2";


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
                    r.setEndDate(rs.getDate("end_date"));
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
    public List<Device> getDeviceByModelId(int modelId) {
        String sql="select * from device d join model m on d.id_model=m.id_model where d.id_model=?";
        try{
            List<Device> result=jdbcTemplate.query(sql, new Object[]{modelId}, new RowMapper<Device>() {
                @Override
                public Device mapRow(ResultSet rs, int i) throws SQLException {
                    Device device=new Device();
                    device.setIdDevice(rs.getInt("id_device"));
                    device.setBrand(rs.getString("brand"));
                    Model model=new Model();
                    model.setIdModel(rs.getInt("id_model"));
                    model.setModel(rs.getString("model"));
                    device.setModel(model);
                    return device;
                }
            });

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createNewRepair(Repair repair) {
       final String sql="insert into repair(id_user,id_device,id_staff,price,title,start_date,active,tracking_number)"+
                " values(?,?,?,?,?,?,?,?)";
        try{



            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_repair"});
                    ps.setInt(1, repair.getUser().getIdUser());
                    ps.setInt(2, repair.getDevice().getIdDevice());
                    ps.setInt(3, repair.getStaff().getIdStaff());
                    ps.setInt(4,repair.getPrice());
                    ps.setString(5,repair.getTitle());
                    ps.setDate(6,new java.sql.Date(repair.getStartDate().getTime()));
                    ps.setInt(7,repair.getActive());
                    ps.setString(8,repair.getTrackingNumber());
                    return ps;
                }
            }, holder);

            int index= holder.getKey().intValue();

            String q="insert into progress(percent,id_repair) values(?,?)";

             jdbcTemplate.update(q,new Object[]{0,index});

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public List<Model> getAllModel() {
        String sql="select * from model";

        try{

            List<Model> result=jdbcTemplate.query(sql, new RowMapper<Model>() {
                @Override
                public Model mapRow(ResultSet resultSet, int i) throws SQLException {
                    Model model=new Model();
                    model.setIdModel(resultSet.getInt("id_model"));
                    model.setModel(resultSet.getString("model"));
                    return model;
                }
            });

            return result;

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Repair getRepairByTrackingNumber(String trackingNumber) {
        String sql="select r.id_repair,m.model,d.brand,p.percent,r.price,r.start_date,u.full_name,u.contact_number,\n" +
                "r.tracking_number,\n" +
                "r.title,p.comment,p.date  from repair r join device d on r.id_device=d.id_device \n" +
                "join model m on d.id_model=m.id_model join progress p on r.id_repair=p.id_repair join staff u on r.id_staff=u.id_staff \n" +
                "where r.tracking_number=? and r.active=1";
        try{
           List<Repair> result= jdbcTemplate.query(sql, new Object[]{trackingNumber}, new RowMapper<Repair>() {
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
                   Staff staff=new Staff();
                   staff.setFullName(rs.getString("full_name"));


                   staff.setContactNumber(rs.getString("contact_number"));

                   r.setStaff(staff);
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
}
