package DAO.Impl;

import DAO.VendedorDAO;
import Entidades.Departamento;
import Entidades.Vendedor;
import com.mysql.jdbc.Statement;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorDAOJDBC implements VendedorDAO{

    private Connection conn;

    public VendedorDAOJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void inserir(Vendedor vendedor) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    +"(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
                    +"VALUES "
                    +"(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new java.sql.Date(vendedor.getAniversário().getTime()));
            st.setDouble(4, vendedor.getSalárioBase());
            st.setInt(5, vendedor.getDepartamento().getId());
            
            int linhasAfetadas = st.executeUpdate();
            
            if (linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    vendedor.setId(id);                    
                } 
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
            }     
        } 
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        } 
    }

    @Override
    public void atualizar(Vendedor vendedor) {
PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ?");
            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new java.sql.Date(vendedor.getAniversário().getTime()));
            st.setDouble(4, vendedor.getSalárioBase());
            st.setInt(5, vendedor.getDepartamento().getId());
            st.setInt(6, vendedor.getId());
            
            st.executeUpdate();
            
        } 
        
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM seller WHERE Id =?");
            st.setInt(1, id);
            int linhas = st.executeUpdate();
            
            if (linhas == 0){
                throw new DbException("Id não encontrado!");
            }
        } 
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
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
    public List<Vendedor> procurarTodos() {
//Primeiras declarações:
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            //Comando SQL:
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");
            
            rs = st.executeQuery(); //Executar o comando
            
            //Declaração de Lista e Map para evitar duplicação:
            List<Vendedor> listaDeVendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();
            
            while (rs.next()){
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null){ //Se não tiver o Id do Departamento
                    dep = instanciarDepartamento(rs); //Instanciar o Departamento
                    map.put(rs.getInt("DepartmentId"), dep); //Incluir o Departamento criado no Map
                } //Fim do If
                Vendedor vendedor = instanciarVendedor(rs, dep); //Cria o objeto Vendedor
                listaDeVendedores.add(vendedor); // Inclui o Vendedor na listaDeVendedores
                
            }
            return listaDeVendedores; // Retorna a lista  
            
        } //Tratando Exceções: 
        
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        
        finally{ // Desconectando o Statement e o ResultSet:
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            
        }
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

    @Override
    public List<Vendedor> procurarPorDepartamento(Departamento departamento) {
        //Primeiras declarações:
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            //Comando SQL:
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            
            st.setInt(1, departamento.getId()); //Substituir o ? do comando
            rs = st.executeQuery(); //Executar o comando
            
            //Declaração de Lista e Map para evitar duplicação:
            List<Vendedor> listaDeVendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();
            
            while (rs.next()){
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null){ //Se não tiver o Id do Departamento
                    dep = instanciarDepartamento(rs); //Instanciar o Departamento
                    map.put(rs.getInt("DepartmentId"), dep); //Incluir o Departamento criado no Map
                } //Fim do If
                Vendedor vendedor = instanciarVendedor(rs, dep); //Cria o objeto Vendedor
                listaDeVendedores.add(vendedor); // Inclui o Vendedor na listaDeVendedores
                
            }
            return listaDeVendedores; // Retorna a lista           
        } //Tratando Exceções: 
        
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        
        finally{ // Desconectando o Statement e o ResultSet:
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            
        }
    }
    
}
