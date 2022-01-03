package bo;

import bo.custom.impl.RegistrationDetailBOImpl;
import bo.custom.impl.StudentBOImpl;
import bo.custom.impl.TrainingProgramBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory=new BOFactory():boFactory;
    }

    public enum BOTypes{
        STUDENT,PROGRAM,REGISTRATIONDETAIL;
    }

    public SuperBO getBOTypes(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:return new StudentBOImpl();
            case PROGRAM:return new TrainingProgramBOImpl();
            case REGISTRATIONDETAIL:return new RegistrationDetailBOImpl();
            default:return null;
        }
    }
}
