package org.example;

import org.example.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;

import java.sql.*;

public class HibernateDemo {

    private final static int MAX_INSERTS = 1500;
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persons");
        EntityManager em = emf.createEntityManager();
        Configuration configuration = getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        EntityTransaction transaction = em.getTransaction();

        //  One single commit for all persisted objects
        hibernateSingleCommitDemo(em, transaction);
        // This is not a good approach for a massive insert, one commit for each insert
        hibernateCommitPerInsertDemo(em, transaction);
        // Using insert into in HQL through hibernate
        hibernateHqlDemo(em, transaction);
        // Using session. This might be a good approach using hibernate
        hibernateSessionDemo(sessionFactory);
        Session session;
        // Using session with batch processing. This might be a good approach using hibernate
        hibernateSessionBatchDemo(sessionFactory);
        // Using SQL directly to the database through the Postgres' jdbc driver
        Connection c;
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "admin", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sqlDemo(c);

    }


    private static void sqlDemo(Connection c) {
        truncateTable();
        try {
            c.setAutoCommit(false);
            for (int i = 0; i < MAX_INSERTS; i++) {
                PreparedStatement statement = c.prepareStatement("INSERT into Person (name, surname, id) values (?, ?, ?)");
                statement.setString(1, "name driver " + i);
                statement.setString(2, "surname driver " + i);
                statement.setInt(3, i);
                statement.executeUpdate();
            }
            c.commit();
        } catch (Exception e) {
            System.out.println("Exception thrown");
            System.out.println(e.getMessage());
        }
    }

    private static void hibernateSessionBatchDemo(SessionFactory sessionFactory) {
        Session session;
        EntityTransaction transaction;
        truncateTable();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
        transaction.begin();
        int batchSize = 500;
        for (int i = 0; i < MAX_INSERTS; i++) {
            Person p = new Person();
            p.setName("Name object " + i);
            p.setSurname("Surname as object" + i);
            p.setId(i);
            session.save(p);
            if ((i > 0) && ((i % batchSize) == 0)) {
                session.flush();
                session.clear();
            }
        }
        transaction.commit();
    }

    private static void hibernateSessionDemo(SessionFactory sessionFactory) {
        EntityTransaction transaction;
        truncateTable();
        Session session = sessionFactory.openSession();
        transaction = session.getTransaction();
        transaction.begin();
        for (int i = 0; i < MAX_INSERTS; i++) {
            Person p = new Person();
            p.setName("Name object " + i);
            p.setSurname("Surname as object" + i);
            session.save(p);
        }
        session.flush();
        session.clear();
        transaction.commit();
    }

    private static void hibernateHqlDemo(EntityManager em, EntityTransaction transaction) {
        truncateTable();
        transaction.begin();
        String hqlCommand = "INSERT into Person (name, surname, id) values (:name, :surname, nextval ('hibernate_sequence'))";
        for (int i = 0; i < MAX_INSERTS; i++) {
            Query q = em.createNativeQuery(hqlCommand);
            q.setParameter("name", "Name insert " + i);
            q.setParameter("surname", "Surname insert " + i);
            q.executeUpdate();
        }
        transaction.commit();
    }

    private static void hibernateCommitPerInsertDemo(EntityManager em, EntityTransaction transaction) {
        truncateTable();
        for (int i = 0; i < MAX_INSERTS; i++) {
            transaction.begin();
            Person p = new Person();
            p.setName("Name object " + i);
            p.setSurname("Surname as object" + i);
            em.persist(p);
            transaction.commit();
        }
    }

    private static void hibernateSingleCommitDemo(EntityManager em, EntityTransaction transaction) {
        truncateTable();
        transaction.begin();
        for (int i = 0; i < MAX_INSERTS; i++) {
            Person p = new Person();
            p.setName("Name object " + i);
            p.setSurname("Surname as object" + i);
            em.persist(p);
        }
        transaction.commit();
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/postgres");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.addAnnotatedClass(Person.class);
        return configuration;
    }

    private static void truncateTable() {
        Connection c;
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "admin", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement s = c.createStatement();
            s.execute("TRUNCATE TABLE Person RESTART IDENTITY");
        } catch (SQLException e) {
            System.out.println("Could not truncate table, does not exist");
        }

        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("Problems closing the connection");
        }
    }
}