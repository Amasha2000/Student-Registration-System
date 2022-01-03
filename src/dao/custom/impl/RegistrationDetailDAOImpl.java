package dao.custom.impl;

import dao.custom.RegistrationDetailDAO;
import db.FactoryConfiguration;
import entity.RegistrationDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class RegistrationDetailDAOImpl implements RegistrationDetailDAO {
    @Override
    public boolean add(RegistrationDetail registrationDetail) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(registrationDetail);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(RegistrationDetail registrationDetail) throws IOException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
           session.update(registrationDetail);
           transaction.commit();
            session.close();

            return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM RegistrationDetail WHERE studentId = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public RegistrationDetail search(String s) {
        return null;
    }

    @Override
    public List<RegistrationDetail> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<RegistrationDetail> list=session.createNativeQuery("SELECT * FROM RegistrationDetail",RegistrationDetail.class).list();
        transaction.commit();
        session.close();
        return list;
    }
}
