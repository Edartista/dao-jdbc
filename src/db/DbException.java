package db;
public class DbException extends RuntimeException{ //Tratando exceções em tempo real (RunTime)
 
    public DbException (String mensagem){
        super(mensagem);
    }
    
}
