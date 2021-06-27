package com.example.demo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class KafkaConsumer {
	
	private static final String SQL_INSERT = "INSERT INTO demo1 VALUES (?)";
	
	@KafkaListener(topics = "javainuse-topic",
            groupId = "g1")
    public void consume(String message) {
        System.out.printf("Message received -> %s%n", message);
        
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test1", "root", "password");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setString(1, message);
           

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row); // 1

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }

}
