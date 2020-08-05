package DAO;

import Entidades.Departamento;
import java.util.List;

public interface DepartamentoDAO {
    
    void inserir (Departamento obj);
    void atualizar (Departamento obj);
    void deletarPorId(Integer id);
    Departamento procurarPorId(Integer id);
    List<Departamento> encontrarTodos();
    
}
