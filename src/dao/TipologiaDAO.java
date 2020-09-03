package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class TipologiaDAO {
	Conexao mysql = new Conexao();

     
	public void criarTipologia(Tipologia t) {

		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;


		try {
			stmt = conexao.prepareStatement("INSERT INTO tipologia " 
		    + "(id_produto,codigo_componente,descricao_componente,multiplicadorlargura,offsetlargura_mm,multiplicadoraltura,offsetaltura_mm,"
			+ " peso_por_metro) VALUES (?,?,?,?,?,?,?,?);");

			stmt.setInt(1, t.getIdProduto());
			stmt.setString(2,t.getCodigo_componente().toString());
			
			// query para procurar a descricao do componente e incluir no banco de dados
			PreparedStatement stmt2 = conexao.prepareStatement("SELECT descricao FROM componente WHERE "
					+ "codigo_componente = '" + t.getCodigo_componente()+"';");
			ResultSet rs = stmt2.executeQuery();

			if(rs.next()) {
					
				stmt.setString(3,rs.getString("descricao"));
				
			} else {JOptionPane.showMessageDialog(null, "Nao foi possivel localizar o componente "
					+ "no banco de dados, componente ira ficar sem o campo 'descrição' na tabela 'tipologias'"
					+ " (Não irá causar nenhum problema)!");
					stmt.setString(3,"");	
			  }
			
			stmt.setDouble(4, t.getMultiplicadorLargura());
			stmt.setDouble(5, t.getOffsetLargura());

			stmt.setDouble(6, t.getMultiplicadorAltura());
			stmt.setDouble(7, t.getOffsetAltura());

			Conexao.closeConnection(stmt2);
			Conexao.closeConnection(rs);

			// query para procurar o peso por metro do componente e incluir no banco de dados
			PreparedStatement stmt3 = conexao.prepareStatement("SELECT peso_por_metro FROM componente WHERE "
					+ "codigo_componente = '" + t.getCodigo_componente().toString()+"';");
			ResultSet rs2 = stmt3.executeQuery();

			if(rs2.next()) {
				
			stmt.setDouble(8, rs2.getDouble("peso_por_metro"));
			
			} else {JOptionPane.showMessageDialog(null, "Nao foi possivel localizar o componente "
					+ "no banco de dados, definindo o peso por metro para 0.0 (nao irá calcular "
					+ "este componente no orçamento)!");
					stmt.setDouble(6,0);
			}
			
			stmt.executeUpdate();
			
			Conexao.closeConnection(stmt3);
			Conexao.closeConnection(rs2);

			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
			
		} finally {
			
			Conexao.closeConnection(conexao, stmt);
		}
	}

	  public Tipologia lerObjTipologia(int idTipologia) {

	        Connection conexao = Conexao.getConnection();
	        
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        Tipologia tipologia = new Tipologia();

	        try {
	            stmt = conexao.prepareStatement("SELECT * FROM tipologia WHERE idtipologia LIKE ?;");
	            stmt.setInt(1, idTipologia);
	            
	            rs = stmt.executeQuery();
	            
	            if (rs.next()) {

	                tipologia.setIdTipologia(rs.getInt("idtipologia"));
	            	tipologia.setIdProduto(rs.getInt("id_produto"));
	            	tipologia.setCodigo_componente(rs.getString("codigo_componente"));
	            	tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
	            	tipologia.setMultiplicadorLargura(rs.getDouble("multiplicadorlargura"));
	            	tipologia.setOffsetLargura(rs.getDouble("offsetlargura_mm"));
	            	tipologia.setMultiplicadorAltura(rs.getDouble("multiplicadoraltura"));
	            	tipologia.setOffsetAltura(rs.getDouble("offsetaltura_mm"));
	            	tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));
					
					
	            } else {JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id da tipologia selecionada na tabela!");}

	        } catch (Exception e) { JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id da tipologia selecionada na tabela!");       } 
	        finally { Conexao.closeConnection(conexao, stmt, rs);}
	        return tipologia;
	        }
	
	public List<Tipologia> lerTabela(){
		
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Tipologia> Tipologias = new ArrayList<>();
		
		try {
	//pegar descricao do produto e componente
	    stmt = conexao.prepareStatement("SELECT * FROM tipologia");

		rs = stmt.executeQuery();
		
			while(rs.next()) {
				
				Tipologia Tipologia = new Tipologia();	
				Tipologia.setIdTipologia(rs.getInt("idtipologia"));
				Tipologia.setIdProduto(rs.getInt("id_produto"));
				Tipologia.setCodigo_componente(rs.getString("codigo_componente"));
				Tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
				Tipologia.setMultiplicadorLargura(rs.getDouble("multiplicadorlargura"));
            	Tipologia.setOffsetLargura(rs.getDouble("offsetlargura_mm"));
			    Tipologia.setMultiplicadorAltura(rs.getDouble("multiplicadoraltura"));
            	Tipologia.setOffsetAltura(rs.getDouble("offsetaltura_mm"));
				Tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));
				Tipologias.add(Tipologia);
				
			}
		} catch (Exception e) 
		{JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'tipologia' "+e);}
		finally { Conexao.closeConnection(conexao, stmt, rs);}
		return Tipologias;
	}
	

	// funcao para localizar um componente na tabela
    public List<Tipologia> lerParaProduto(Produto produto) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Tipologia> tipologias = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM tipologia WHERE id_produto = ?");
            stmt.setInt(1, produto.getIdProduto());           
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tipologia tipologia = new Tipologia();
				tipologia.setIdTipologia(rs.getInt("idtipologia"));
				tipologia.setIdProduto(rs.getInt("id_produto"));
				tipologia.setCodigo_componente(rs.getString("codigo_componente"));
				tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
				tipologia.setMultiplicadorLargura(rs.getDouble("multiplicadorlargura"));
            	tipologia.setOffsetLargura(rs.getDouble("offsetlargura_mm"));
			    tipologia.setMultiplicadorAltura(rs.getDouble("multiplicadoraltura"));	
            	tipologia.setOffsetAltura(rs.getDouble("offsetaltura_mm"));
			    tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));	
				tipologias.add(tipologia);				
            }
        } catch (Exception e) {        } 
        finally { Conexao.closeConnection(conexao, stmt, rs);}
        return tipologias;
        }        
        public void delete(Tipologia c) {
            Connection conexao = Conexao.getConnection();    
            PreparedStatement stmt = null;
            try {
                stmt = conexao.prepareStatement("DELETE FROM tipologia WHERE idtipologia = ?");
                stmt.setInt(1, c.getIdTipologia());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }

        }
        
        public void update(Tipologia c) {
            Connection conexao = Conexao.getConnection();           
            PreparedStatement stmt = null;
            try {
                stmt = conexao.prepareStatement("UPDATE tipologia SET "
                		+ "codigo_componente = ?,"
                		+ "multiplicadorLargura = ?, offsetlargura_mm = ?, multiplicadorAltura = ?, offsetaltura_mm = ? WHERE idtipologia = ?;");              
        		
    			stmt.setString(1, c.getCodigo_componente());
    			stmt.setDouble(2, c.getMultiplicadorLargura());
    			stmt.setDouble(3, c.getOffsetLargura());
    			stmt.setDouble(4, c.getMultiplicadorAltura());		
    			stmt.setDouble(5, c.getOffsetAltura());
    			stmt.setInt(6, (c.getIdTipologia()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }
        }
  
}
