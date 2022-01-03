package bo.custom.impl;

import bo.custom.TrainingProgramBO;
import dao.DAOFactory;
import dao.custom.TrainingProgramDAO;
import dto.TrainingProgramDTO;
import entity.TrainingProgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgramBOImpl implements TrainingProgramBO {

    private final TrainingProgramDAO trainingProgramDAO = (TrainingProgramDAO) DAOFactory.getDaoFactory().getDAOTypes(DAOFactory.DAOTypes.PROGRAM);
    @Override
    public boolean addProgram(TrainingProgramDTO trainingProgramDTO) throws IOException {
        return trainingProgramDAO.add(new TrainingProgram(trainingProgramDTO.getProgramID(),trainingProgramDTO.getProgramName(),trainingProgramDTO.getDuration(),trainingProgramDTO.getFee()));
    }

    @Override
    public boolean updateProgram(TrainingProgramDTO trainingProgramDTO) throws IOException {
        return trainingProgramDAO.update(new TrainingProgram(trainingProgramDTO.getProgramID(),trainingProgramDTO.getProgramName(),trainingProgramDTO.getDuration(),trainingProgramDTO.getFee()));
    }

    @Override
    public boolean deleteProgram(String id) throws IOException {
        return trainingProgramDAO.delete(id);
    }

    @Override
    public TrainingProgramDTO searchProgram(String id) throws IOException {
        TrainingProgram t = trainingProgramDAO.search(id);
        return new TrainingProgramDTO(t.getProgramID(),t.getProgramName(),t.getDuration(),t.getFee());
    }

    @Override
    public List<TrainingProgramDTO> getAllPrograms() throws IOException {
        List<TrainingProgramDTO> allPrograms = new ArrayList<>();
        List<TrainingProgram> all=trainingProgramDAO.getAll();
        for(TrainingProgram trainingProgram:all){
            allPrograms.add(new TrainingProgramDTO(trainingProgram.getProgramID(),trainingProgram.getProgramName(),trainingProgram.getDuration(),trainingProgram.getFee()));
        }
        return allPrograms;
    }

    @Override
    public List<String> getAllProgramIds() throws IOException {
        List<String> programIds=new ArrayList<>();
        List<TrainingProgram> programs=trainingProgramDAO.getAll();
        for(TrainingProgram t:programs){
            programIds.add(t.getProgramID());
        }
        return programIds;
    }
}
