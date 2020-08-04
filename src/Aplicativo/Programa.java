package Aplicativo;

import Entidades.Departamento;
import Entidades.Vendedor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Programa {
    
    public static void main(String[] args) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa");
        Departamento obj = new Departamento(1, "Livros");
        
        Vendedor vendedor = new Vendedor(21, "Vinícius", "bob@gmail.com", new Date(), 3000.0, obj);
        
        System.out.println(obj);
        System.out.println(vendedor);
        
       
    }
    
    
}
