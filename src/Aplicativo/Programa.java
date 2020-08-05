package Aplicativo;

import DAO.FabricaDAO;
import DAO.VendedorDAO;
import Entidades.Vendedor;

public class Programa {
    
    public static void main(String[] args) {
        
        
        VendedorDAO vendedorDAO = FabricaDAO.criarVendedorDAO();
        System.out.println("===TEST 01: VENDEDOR PROCURAR POR ID ====");
        Vendedor vendedor = vendedorDAO.procurarPorId(3);
        
        System.out.println(vendedor);
    }
    
    
}
