package db;

import entity.RegistrationDetail;
import entity.Student;
import entity.TrainingProgram;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {

        Configuration configure = new Configuration().addAnnotatedClass(Student.class).addAnnotatedClass(TrainingProgram.class).addAnnotatedClass(RegistrationDetail.class);
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        configure.setProperties(properties);
        sessionFactory = configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
