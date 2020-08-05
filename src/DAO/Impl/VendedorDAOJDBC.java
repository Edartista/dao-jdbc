package DAO.Impl;

import DAO.VendedorDAO;
import Entidades.Departamento;
import Entidades.Vendedor;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorDAOJDBC implements VendedorDAO{

    private Connection conn;

    public VendedorDAOJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void inserir(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public  Vendedor procurarPorId(Integer id) {
        //Primeiras declarações:
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            //Comando SQL:
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " 
                    + "FROM seller INNER JOIN department " 
                    + "ON seller.DepartmentId = department.Id " 
                    + "WHERE seller.Id = ?");
            
            st.setInt(1, id); //Substituir o ? do comando
            rs = st.executeQuery(); //Executar o comando
            
            if (rs.next()){
                //Criar um objeto do tipo Departamento:
                Departamento dep = instanciarDepartamento(rs);
                
                //Criar um objeto do tipo Vendedor (associando Departamento):
                Vendedor vendedor = instanciarVendedor(rs, dep);
                return vendedor;
            }
            return null;
           
        } //Tratando Exceções: 
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{ // Desconectando o Statement e o ResultSet:
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            
        }
        
    }

    @Override
    public List<Vendedor> encontrarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        //Criando um objeto do tipo Departamento:
        Departamento dep = new Departamento();
        dep.setId(rs .getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getInt("Id"));
        vendedor.setNome(rs.getString("Name"));
        vendedor.setEmail(rs.getString("Email"));
        vendedor.setSalárioBase(rs.getDouble("BaseSalary"));
        vendedor.setAniversário(rs.getDate("BirthDate"));
        vendedor.setDepartamento(dep);
        return vendedor;    
    }
    
    
    
}