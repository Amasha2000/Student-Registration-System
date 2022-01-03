package dao.custom.impl;


import dao.custom.TrainingProgramDAO;
import db.FactoryConfiguration;
import entity.TrainingProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class TrainingProgramDAOImpl implements TrainingProgramDAO {
    @Override
    public boolean add(TrainingProgram trainingProgram) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(trainingProgram);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TrainingProgram trainingProgram) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(trainingProgram);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(TrainingProgram.class,id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public TrainingProgram search(String id) throws IOException {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        TrainingProgram load = session.get(TrainingProgram.class, id);
        transaction.commit();
        session.close();
        return load;
    }

    @Override
    public List<TrainingProgram> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "From TrainingProgram";
        Query query = session.createQuery(hql);
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
