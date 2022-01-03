package bo.custom;

import bo.SuperBO;
import dto.TrainingProgramDTO;

import java.io.IOException;
import java.util.List;

public interface TrainingProgramBO extends SuperBO {
    boolean addProgram(TrainingProgramDTO trainingProgramDTO) throws IOException;
    boolean updateProgram(TrainingProgramDTO trainingProgramDTO) throws IOException;
    boolean deleteProgram(String id) throws IOException;
    TrainingProgramDTO searchProgram(String id) throws IOException;
    List<TrainingProgramDTO> getAllPrograms() throws IOException;
    List<String> getAllProgramIds() throws IOException;
}
