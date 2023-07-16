package com.courseweb.web.Dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.courseweb.web.Model.Admin;
import com.courseweb.web.Model.Crops;
import com.courseweb.web.Model.User;
import com.courseweb.web.Rowmapper.AdminRowmapper;
import com.courseweb.web.Rowmapper.CropsRowmapper;
import com.courseweb.web.Rowmapper.UserRowmapper;

@Repository
public class Dao {
    @Autowired
    private JdbcTemplate jdbc;

    public User viewUser(User user){
        User currUser = null;
        try{
            String sql="select * from user where userEmail=?";
            currUser=this.jdbc.queryForObject(sql, new UserRowmapper(), user.getUserEmail());
        }catch(Exception e){e.printStackTrace();}
        return currUser;
    }
    
    public String addUser(User user){
        User check=null;
        check=viewUser(user);
        if(check!=null){
            return "Email already Exists";
        }
        String query ="insert into user (userEmail) values(?)";
        this.jdbc.update(query,user.getUserEmail());
        return "User Added Successfully";
    }

    public String viewAdmin(Admin admin){
        Admin check= null;
        try{
            String sql="select * from Admin where adminEmail=? && adminPassword=?";
            check=this.jdbc.queryForObject(sql, new AdminRowmapper(), admin.getAdminEmail(),admin.getAdminPassword());
        }catch(Exception e){e.printStackTrace();}
        if(check!=null){
            return "Successfully Logged In";
        }else{
            return "Incorrect username or Password";
        }
    }

    public String setCropsPrice(Crops crop){
       try{
            String sql="update crops set cropPrice=? where cropName=?";
            this.jdbc.update(sql, crop.getCropPrice(),crop.getCropName());
        }catch(Exception e){e.printStackTrace();};
        return "Successfully updated prices";
    }

    public List<Crops> viewCrops() {
        String sql="select * from Crops";
        List<Crops> list=this.jdbc.query(sql, new CropsRowmapper());

        return list;
    }

    public List<User> allUser(){
        String sql="select * from user";
        List<User> list=this.jdbc.query(sql, new UserRowmapper());

        return list;
    } 

}
