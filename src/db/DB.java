package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB { //DataBase:
    
    private static Connection conn = null; //Instanciando um conector pro banco de dados:
    
    public static Connection conectar(){ //Método para conectar:
        if (conn == null){
            try{
            Properties props = carregarProperties(); // Realizar leitura das credenciais
            String url = props.getProperty("dburl"); // Receber o URL do Banco de dados
            conn = DriverManager.getConnection(url, props); // Realizar a conexão
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    private static Properties carregarProperties(){ //Método para ler as credenciais:
        try(FileInputStream fs = new FileInputStream("db.properties")){ // Instanciar um arquivo que será lido:
            Properties props = new Properties(); //Instanciar um obj do tipo Properties
            props.load(fs); //Ler o arquivo e guardar as informações 
            return props;
        }
        
        catch(IOException e){
            throw new DbException(e.getMessage());
        }
    }
    
    public static void desconectar(){ // Método para desconectar do banco de dados:
        try{
            if (conn != null){ //Se estiver conectado, desconectar
            conn.close();
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeStatement(Statement st){ // Método para fechar o Statement
        if (st != null){ // Se houver um aberto, fechar:
            try{   
            st.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }     
    }
    
    public static void closeResultSet(ResultSet rs){ // Método para fechar o ResultSet
        if (rs != null){// Se houver um aberto, fechar:
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }     
    }
    
}
