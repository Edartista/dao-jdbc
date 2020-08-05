package Aplicativo;

import DAO.FabricaDAO;
import DAO.VendedorDAO;
import Entidades.Departamento;
import Entidades.Vendedor;
import java.util.List;

public class Programa {
    
    public static void main(String[] args) {
        
        
        VendedorDAO vendedorDAO = FabricaDAO.criarVendedorDAO();
        System.out.println("===TEST 01: VENDEDOR PROCURAR POR ID ====");
        Vendedor vendedor = vendedorDAO.procurarPorId(3);
        System.out.println(vendedor);
        
        System.out.println("\n=== TEST 02: VENDEDOR PROCURAR POR DEPARTAMENTO ====");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> listaDeVendedores = vendedorDAO.procurarPorDepartamento(departamento);
        
        listaDeVendedores.forEach(System.out::println);
        
        System.out.println("\n=== TEST 03: VENDEDOR PROCURAR POR TODOS ====");
        listaDeVendedores = vendedorDAO.procurarTodos();
        
        listaDeVendedores.forEach(System.out::println);
    }
    
    
}
