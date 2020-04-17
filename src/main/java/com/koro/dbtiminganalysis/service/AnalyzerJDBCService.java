package com.koro.dbtiminganalysis.service;

import com.koro.dbtiminganalysis.annotation.RunTimer;
import com.koro.dbtiminganalysis.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class AnalyzerJDBCService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnalyzerJDBCService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RunTimer
    public void saveToDb(List<Employee> employeeList) {
        PreparedStatement statement = null;
        Connection con = null;

        try {
            System.out.println("JDBC: Saving to DB...");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String mysqlUrl = "jdbc:mysql://remotemysql.com:3306/0ju2RfYQKW";
            con = DriverManager.getConnection(mysqlUrl, "0ju2RfYQKW", "LksNGl6MZq");

            statement = con.prepareStatement(
                    "INSERT INTO employee" +
                            "( " +
                            "email, " +
                            "first_name, " +
                            "gender," +
                            "ip_address," +
                            "last_name) " +
                            "VALUES (?, ?, ?, ?, ?);");

            for(Employee item : employeeList){
                statement.setString(1, item.getEmail());
                statement.setString(2, item.getFirstName());
                statement.setString(3, item.getGender());
                statement.setString(4, item.getIpAddress());
                statement.setString(5, item.getLastName());
                statement.addBatch();
            }
            statement.executeBatch();
            System.out.println("JDBC: Saving to DB finished.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @RunTimer
    public List<Employee> readFromDb() {
        System.out.println("JDBC: Reading from DB...");
        String sql = "SELECT id, email, first_name, gender, ip_address, last_name FROM employee";
        List<Employee> employeeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Employee.class));
        System.out.println("JDBC: Reading from DB finished.");
        return employeeList;
    }
}
