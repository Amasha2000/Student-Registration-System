package dao;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{
    boolean add(T t) throws IOException;
    boolean update(T t) throws IOException;
    boolean delete(ID id) throws IOException;
    T search(ID id) throws IOException;
    List<T> getAll() throws IOException;
}
