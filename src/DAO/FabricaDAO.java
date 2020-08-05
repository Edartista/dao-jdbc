package DAO;
import DAO.Impl.VendedorDAOJDBC;
import db.DB;
public class FabricaDAO {
    
    public static VendedorDAO criarVendedorDAO(){
        return new VendedorDAOJDBC(DB.conectar());
    }
    
}
