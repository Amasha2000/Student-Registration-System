package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean addStudent(StudentDTO studentDTO) throws IOException;
    boolean updateStudent(StudentDTO studentDTO) throws IOException;
    boolean deleteStudent(String id) throws IOException;
    StudentDTO searchStudent(String id) throws IOException;
    List<StudentDTO> getAllStudents() throws IOException;
    List<String> getAllStudentIds() throws IOException;
}
