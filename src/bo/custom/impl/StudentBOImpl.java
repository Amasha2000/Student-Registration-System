package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.RegistrationDetailDAO;
import dao.custom.StudentDAO;
import dto.RegistrationDetailDTO;
import dto.StudentDTO;
import entity.RegistrationDetail;
import entity.Student;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.STUDENT);
    private final RegistrationDetailDAO registrationDetailDAO = (RegistrationDetailDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.REGISTRATIONDETAIL);
    @Override
    public boolean addStudent(StudentDTO dto) throws IOException {
                Student student = new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getNic(), dto.getGuardianName(), dto.getGender());
                 studentDAO.add(student);
                 for (RegistrationDetailDTO detail : dto.getRegistrationDetailList()) {
                    RegistrationDetail orderDetail = new RegistrationDetail(detail.getsId(), detail.getcId(),detail.getProgram(), detail.getDate(), detail.getTime());
                    registrationDetailDAO.add(orderDetail);
                }
                 return true;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws IOException {
         return studentDAO.update(new Student(studentDTO.getId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getPhoneNumber(),studentDTO.getNic(),studentDTO.getGuardianName(),studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
          studentDAO.delete(id);
          registrationDetailDAO.delete(id);
          return true;
    }

    @Override
    public StudentDTO searchStudent(String id) throws IOException {
        Student s = studentDAO.search(id);
        return new StudentDTO(s.getId(),s.getName(),s.getAddress(),s.getPhoneNumber(),s.getNic(),s.getGuardianName(),s.getGender());
    }

    @Override
    public List<StudentDTO> getAllStudents() throws IOException {
        List<StudentDTO> allStudents = new ArrayList<>();
        List<Student> all=studentDAO.getAll();
        for(Student student:all){
            allStudents.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getPhoneNumber(),student.getNic(),student.getGuardianName(),student.getGender()));
        }
        return allStudents;
    }

    @Override
    public List<String> getAllStudentIds() throws IOException {
        List<String> studentIds=new ArrayList<>();
        List<Student> studentDTO=studentDAO.getAll();
        for(Student s:studentDTO){
            studentIds.add(s.getId());
        }
        return studentIds;
    }
}
