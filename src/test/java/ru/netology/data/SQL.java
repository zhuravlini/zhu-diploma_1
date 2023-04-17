package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.entities.CreditRequestEntity;
import ru.netology.entities.OrderEntity;
import ru.netology.entities.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

// в PaymentEntity у вас должны быть те же поля, что и в таблице базы данных
// когда в тестах получили объект paymentEntity из него можете получить статус paymentEntity.getStatus() или сумму paymentEntity.getAmount() или любое значение другого поля
// не забудьте на все методы в sql повесить аннотацию SneakyThrows и убрать конструкции try-catch

//    public static String getCardStatusForPayment() {
//        String statusQuery = "SELECT status, transaction_Id, amount FROM payment_entity ORDER BY created DESC LIMIT 1";
//        var runner = new QueryRunner();
//        try (Connection connection = getConnection()) {
//            var cardStatus = runner.query(connection, statusQuery, new BeanHandler<>(PaymentEntity.class));
//            return cardStatus.getStatus();
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }

//    public static String getTransactionId() {
//        var runner = new QueryRunner();
//        String idTransactionQuery = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
//        try (Connection connection = getConnection()) {
//            var transactionId = runner.query(connection, idTransactionQuery, new BeanHandler<>(PaymentEntity.class));
//            return transactionId.getTransaction_id();
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }

//    public static String getAmountPayment() {
//        var runner = new QueryRunner();
//        String amountQuery = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
//        try (Connection connection = getConnection()) {
//            var amount = runner.query(connection, amountQuery, new BeanHandler<>(PaymentEntity.class));
//            return transactionId.getAmount();
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }


    public static String getCardStatusForCreditRequest() {
        String statusQuery = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            var cardStatus = runner.query(connection, statusQuery, new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getBankId() {
        String bankIdQuery = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            var bankId = runner.query(connection, bankIdQuery, new BeanHandler<>(CreditRequestEntity.class));
            return bankId.getBank_id();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getPaymentId() {
        var idQueryForCardPay = "SELECT id FROM order_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        try (var connection = getConnection()) {
            var paymentId = runner.query(connection, idQueryForCardPay, new BeanHandler<>(OrderEntity.class));
            return paymentId.getPayment_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}