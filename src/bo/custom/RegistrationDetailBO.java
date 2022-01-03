package bo.custom;

import bo.SuperBO;
import dto.RegistrationDetailDTO;

import java.io.IOException;
import java.util.List;

public interface RegistrationDetailBO extends SuperBO {
    boolean addRegistration(RegistrationDetailDTO registrationDetail) throws IOException;
    boolean updateRegistration(RegistrationDetailDTO registrationDetail) throws IOException;
    boolean deleteRegistration(String id);
    RegistrationDetailDTO searchRegistration(String id);
    List<RegistrationDetailDTO> allRegistration() throws IOException;
}
