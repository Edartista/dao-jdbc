package db;
public class DbIntegrityException extends RuntimeException {
    
    public DbIntegrityException(String mensagem){
        super(mensagem);
    }
    
}
