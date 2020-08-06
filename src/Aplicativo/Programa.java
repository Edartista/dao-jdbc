package Aplicativo;

import DAO.FabricaDAO;
import DAO.VendedorDAO;
import Entidades.Departamento;
import Entidades.Vendedor;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Programa {
    
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        
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
        
        /*/System.out.println("\n=== TEST 04: VENDEDOR INSERIR ====");
        Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento);
        vendedorDAO.inserir(novoVendedor);
        System.out.println("Inserido! Novo Id é " + novoVendedor.getId());
         /*/
        
        System.out.println("\n=== TEST 05 : VENDEDOR ATUALIZAR ====");
        vendedor = vendedorDAO.procurarPorId(1);
        vendedor.setNome("Martha Waine");
        vendedorDAO.atualizar(vendedor);
        System.out.println("Atualização completa!");
        
        System.out.println("\n=== TEST 06: VENDEDOR DELETE ====");
        System.out.println("Digite um Id para deletar:");
        int id = teclado.nextInt();
        
        vendedorDAO.deletarPorId(id);
        System.out.println("Deletado com sucesso!");
        
        teclado.close();
    }  

}