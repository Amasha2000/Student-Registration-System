package dao;

import dao.custom.impl.RegistrationDetailDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dao.custom.impl.TrainingProgramDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory==null? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        STUDENT,PROGRAM,REGISTRATIONDETAIL;
    }

    public SuperDAO getDAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:return new StudentDAOImpl();
            case PROGRAM:return new TrainingProgramDAOImpl();
            case REGISTRATIONDETAIL:return new RegistrationDetailDAOImpl();
            default:return null;
        }
    }
}
