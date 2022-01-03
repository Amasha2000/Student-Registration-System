package bo.custom.impl;

import bo.custom.RegistrationDetailBO;
import dao.DAOFactory;
import dao.custom.RegistrationDetailDAO;
import dto.RegistrationDetailDTO;
import entity.RegistrationDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDetailBOImpl implements RegistrationDetailBO {

    private final RegistrationDetailDAO registrationDetailDAO = (RegistrationDetailDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.REGISTRATIONDETAIL);

    @Override
    public boolean addRegistration(RegistrationDetailDTO detail) throws IOException {
        RegistrationDetail registrationDetail = new RegistrationDetail( detail.getsId(), detail.getcId(), detail.getProgram(), detail.getDate(), detail.getTime());
        return registrationDetailDAO.add(registrationDetail);
    }

    @Override
    public boolean updateRegistration(RegistrationDetailDTO detail) throws IOException {
        RegistrationDetail registrationDetail = new RegistrationDetail( detail.getsId(), detail.getcId(), detail.getProgram(), detail.getDate(), detail.getTime());
        return registrationDetailDAO.update(registrationDetail);
    }

    @Override
    public boolean deleteRegistration(String id) {
        return false;
    }

    @Override
    public RegistrationDetailDTO searchRegistration(String id) {
        return null;
    }

    @Override
    public List<RegistrationDetailDTO> allRegistration() throws IOException {
        List<RegistrationDetailDTO> allRegistrations = new ArrayList<>();
        List<RegistrationDetail> all = registrationDetailDAO.getAll();
        for (RegistrationDetail registerDetail : all) {
            allRegistrations.add(new RegistrationDetailDTO(registerDetail.getStudentId(),registerDetail.getCourseId(),registerDetail.getProgram(),registerDetail.getDate(),registerDetail.getTime()));
        }

        return allRegistrations;
    }
}
