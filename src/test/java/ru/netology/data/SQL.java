package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.entities.CreditRequestEntity;
import ru.netology.entities.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQL {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");
    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    @SneakyThrows
    public static void dropDataBase() {
        var runner = new QueryRunner();
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";
        var creditRequest = "DELETE FROM credit_request_entity";

        var connection = getConnection();
        runner.update(connection, order);
        runner.update(connection, payment);
        runner.update(connection, creditRequest);
    }

    @SneakyThrows
    public static PaymentEntity getCardDataForPayment() {
        String dataQuery = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        var connection = getConnection();
        return runner.query(connection, dataQuery, new BeanHandler<>(PaymentEntity.class));
    }

    @SneakyThrows
    public static CreditRequestEntity getCardDataForCreditRequest() {
        String dataQuery = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        var connection = getConnection();
        return runner.query(connection, dataQuery, new BeanHandler<>(CreditRequestEntity.class));
    }
}

