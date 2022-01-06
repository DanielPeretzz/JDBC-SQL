package com.homework.daniel.dal;

import com.homework.daniel.model.Cat;
import com.homework.daniel.util.JDBCUtil;
import com.homework.daniel.model.Cat;
import com.homework.daniel.util.JDBCUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatDAL implements com.homework.daniel.dal.CrudDAL<Long, Cat> {
    static final Scanner SCANNER = new Scanner(System.in);
    public static final CatDAL instance = new CatDAL();

    private CatDAL() {
        try {
            connection = JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish connection with database");
        }
    }

    private final Connection connection;

    @Override
    public Long create(Cat cat) throws Exception {
        try {
            String sqlStatement = "INSERT INTO cats (name, age) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cat.getName());
           preparedStatement.setInt(2, cat.getAge());
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();

            if (!result.next()) {
                throw new Exception("Failed to retrieve id");
            }

            return result.getLong(1);
        } catch (SQLException e) {
            throw new Exception("Failed to create cat");
        }
    }

    @Override
    public Cat read(Long aLong) throws Exception {
            Cat cat = new Cat();
        try {
            String sqlStatement = "SELECT * FROM cats_example.cats where idcats = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,aLong);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cat.setId(resultSet.getLong("idcats"));
                cat.setName(resultSet.getString("name"));
                cat.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cat;
    }


    @Override
    public void update(Cat cat) {
        try {
        String sqlStatement =
                "UPDATE cats_example.cats\n" +
                "SET name = ?\n" +
                "WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
        System.out.println("enter the name you want to set : ");
        preparedStatement.setString(1, SCANNER.nextLine());
        preparedStatement.setString(2,cat.getName());
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Long aLong)  {
        try {
        String sqlStatement = "DELETE FROM cats_example.cats WHERE idcats = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, aLong);
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Cat> readAll() {
        List<Cat> catList = new ArrayList<>();
        try {
        String sqlStatement = "SELECT * FROM cats_example.cats;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement ,Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Cat cat = new Cat();
            cat.setId(resultSet.getLong("idcats"));
            cat.setName(resultSet.getString("name"));
            cat.setAge(resultSet.getInt("age"));
            catList.add(cat);
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return catList;
    }
}
