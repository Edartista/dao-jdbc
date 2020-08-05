package DAO;

import Entidades.Departamento;
import Entidades.Vendedor;
import java.util.List;

public interface VendedorDAO {
    
    void inserir (Vendedor obj);
    void atualizar (Vendedor obj);
    void deletarPorId(Integer id);
    Vendedor procurarPorId(Integer id);
    List<Vendedor> encontrarTodos();
    List<Vendedor> procurarPorDepartamento (Departamento departamento);
    
}
