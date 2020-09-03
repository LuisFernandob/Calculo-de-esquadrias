package dao;


import sistema.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VariaveisDAO {

	public double lerVariavel(String tipoVariavel){
				
		double r = 0;
		try {
      		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM prisma.variaveis WHERE referencia = 1");
                ResultSet rs = stmt.executeQuery();
                rs.next();
                r = rs.getDouble(tipoVariavel);
                Conexao.closeConnection(conexao, stmt, rs);
		} catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler "+tipoVariavel+": "+e);
                }
		return r;
	}
	
        public boolean procurarReferencia(){
            try{
            Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM prisma.variaveis WHERE referencia = 1");
            ResultSet rs = stmt.executeQuery();
            
            if(!rs.next()){
                Conexao.closeConnection(conexao, stmt, rs);
                return false;
            } else {
                    Conexao.closeConnection(conexao, stmt, rs);
                    return true;
                   }        
            
            } catch (Exception e){JOptionPane.showMessageDialog(null, "Erro ao procurar a referencia na tabela variaveis!");
                                  return true;
                                 }   
        }
        
        public void criarReferencia() {
            
            try{
            
                Connection conexao2 = Conexao.getConnection();
                PreparedStatement stmt2 = conexao2.prepareStatement("INSERT INTO prisma.variaveis (referencia)"
                        + " VALUES (?);");
                stmt2.setInt(1,1);
                stmt2.executeUpdate();
                Conexao.closeConnection(conexao2, stmt2);   
                
            } catch (Exception e){JOptionPane.showMessageDialog(null, "Erro ao criar referencia! "+e);}  
            
        }
        
        public void update(String tipoVariavel, double d) {

            try {
                Connection conexao = Conexao.getConnection();
                PreparedStatement stmt = conexao.prepareStatement("UPDATE prisma.variaveis SET "
                		+tipoVariavel+ " = ? WHERE referencia = 1;");    
                
                stmt.setDouble(1, d);   			
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                Conexao.closeConnection(conexao, stmt);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar "+tipoVariavel+": " + e);
            } 
        }
        
        public void atualizarVariavelLocal(){
            try{
            Connection conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM prisma.variaveis WHERE referencia = 1");
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            Variaveis.setPrecoAluminio(rs.getDouble("precoaluminio"));
            Variaveis.setPorcentagemMDO(rs.getInt("porcentagemmdo"));
            Variaveis.setPrecoContramarco(rs.getDouble("precocontramarco"));

           
            Conexao.closeConnection(conexao, stmt, rs);       
            
            } catch (Exception e){JOptionPane.showMessageDialog(null, "Erro ao atualizar variaveis locais!");
                                 }   
        }
  





}
