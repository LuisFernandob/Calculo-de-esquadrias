package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class ComponenteDAO {
	Conexao mysql = new Conexao();

	public void criarComponente(Componente c) {

		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement(
					"INSERT INTO prisma.componente" + "(codigo_componente,tipo,descricao,comprimento_barra_m,unidade,largura_mm,"
					+ "largura_encaixe_mm,peso_por_metro) VALUES (?,?,?,?,?,?,?,?);");

			stmt.setString(1, c.getCodigo_componente());
			stmt.setString(2, c.getTipo());
			stmt.setString(3, c.getDescricao());
			stmt.setDouble(4, c.getComprimento_barra_m());
			stmt.setString(5, c.getUnidade());
			stmt.setInt(6, c.getLargura_mm());
			stmt.setInt(7, c.getLargura_encaixe_mm());
			stmt.setDouble(8, c.getPeso_por_metro());
		
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
		} finally {
			Conexao.closeConnection(conexao, stmt);
		}
	}

	public List<Componente> lerTabela(){
		
		List<Componente> componentes = new ArrayList<>();

		try {
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		
		stmt = conexao.prepareStatement("SELECT * FROM componente");
		rs = stmt.executeQuery();
		
			while(rs.next()) {
				Componente componente = new Componente();
			
				componente.setCodigo_componente(rs.getString("codigo_componente"));
				componente.setTipo(rs.getString("tipo"));
				componente.setDescricao(rs.getString("descricao"));
				componente.setComprimento_barra_m(rs.getInt("comprimento_barra_m"));
				componente.setUnidade(rs.getString("unidade"));
			    componente.setLargura_mm(rs.getInt("largura_mm"));
				componente.setLargura_encaixe_mm(rs.getInt("largura_encaixe_mm"));
				componente.setPeso_por_metro(rs.getDouble("peso_por_metro"));
		
				componentes.add(componente);
				
			}
			Conexao.closeConnection(conexao, stmt, rs);
		} catch (Exception e) 
		{JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'Componentes' "+e);}
		
		return componentes;
		
	}
	

    public List<Componente> lerParaNome(String descricao) {

        Connection conexao = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Componente> componentes = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM componente WHERE descricao LIKE ?");
            stmt.setString(1, "%"+descricao+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Componente componente = new Componente();

				componente.setCodigo_componente(rs.getString("codigo_componente"));
				componente.setTipo(rs.getString("tipo"));
				componente.setDescricao(rs.getString("descricao"));
				componente.setComprimento_barra_m(rs.getInt("comprimento_barra_m"));
				componente.setUnidade(rs.getString("unidade"));
			    componente.setLargura_mm(rs.getInt("largura_mm"));
				componente.setLargura_encaixe_mm(rs.getInt("largura_encaixe_mm"));
				componente.setPeso_por_metro(rs.getDouble("peso_por_metro"));
		
				componentes.add(componente);
				
            }

        } catch (Exception e) {        } 
        finally { Conexao.closeConnection(conexao, stmt, rs);}
        return componentes;
        }
    
    public Componente lerObjComponente(String codigoComponente) {

        Connection conexao = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Componente componente = new Componente();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM componente WHERE codigo_componente LIKE ?;");
            stmt.setString(1, codigoComponente);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {

                
            	componente.setCodigo_componente(rs.getString("codigo_componente"));
				componente.setTipo(rs.getString("tipo"));
				componente.setDescricao(rs.getString("descricao"));
				componente.setComprimento_barra_m(rs.getInt("comprimento_barra_m"));
				componente.setUnidade(rs.getString("unidade"));
			    componente.setLargura_mm(rs.getInt("largura_mm"));
				componente.setLargura_encaixe_mm(rs.getInt("largura_encaixe_mm"));
				componente.setPeso_por_metro(rs.getDouble("peso_por_metro"));
				
				
            } else {JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id do cliente selecionado na tabela!");}

        } catch (Exception e) { JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id do cliente selecionado na tabela!");       } 
        finally { Conexao.closeConnection(conexao, stmt, rs);}
        return componente;
        }
        
        

        public void delete(Componente c) {

            Connection conexao = Conexao.getConnection();
            
            PreparedStatement stmt = null;

            try {
                stmt = conexao.prepareStatement("DELETE FROM componente WHERE codigo_componente = ?");
                stmt.setString(1, c.getCodigo_componente());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }

        }
        
        public void update(Componente c) {
            Connection conexao = Conexao.getConnection();           
            PreparedStatement stmt = null;
            try {
                stmt = conexao.prepareStatement("UPDATE componente SET codigo_componente = ?, tipo = ?,descricao = ?,"
                		+ "comprimento_barra_m = ?,unidade = ?,largura_mm = ?,largura_encaixe_mm = ?,peso_por_metro = ?"
                		+ " WHERE codigo_componente = ?;");              
                stmt.setString(1, c.getCodigo_componente());
    			stmt.setString(2, c.getTipo());
    			stmt.setString(3, c.getDescricao());
    			stmt.setDouble(4, c.getComprimento_barra_m());
    			stmt.setString(5, c.getUnidade());
    			stmt.setInt(6, c.getLargura_mm());
    			stmt.setInt(7, c.getLargura_encaixe_mm());
    			stmt.setDouble(8, c.getPeso_por_metro());  
    			stmt.setString(9, c.getCodigo_componente());
                stmt.executeUpdate();
                
     			// atualizar a descricao do componente na tabela tipologias
    			PreparedStatement stmt2 = conexao.prepareStatement("UPDATE tipologia SET descricao_componente = ?"
    					+" WHERE codigo_componente = ?;");
    			stmt2.setString(1, c.getDescricao());
                        stmt2.setString(2, c.getCodigo_componente());
                        stmt2.executeUpdate();
    			Conexao.closeConnection(stmt2);
     			
    			// alterar coluna peso_por_metro da tabela tipologia
    			PreparedStatement stmt3 = conexao.prepareStatement("UPDATE tipologia SET peso_por_metro = ?"
    					+" WHERE codigo_componente = ?;");
    			stmt3.setDouble(1, c.getPeso_por_metro());
                        stmt3.setString(2, c.getCodigo_componente());
                        stmt3.executeUpdate();
    			Conexao.closeConnection(stmt3);
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }
        }
  




}
