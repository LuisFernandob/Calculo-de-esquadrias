package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class OrcamentoDAO {
	Conexao mysql = new Conexao();

	public void criarOrcamento(Orcamento o) {

		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement(
					"INSERT INTO prisma.orcamento" + "(idcliente,descricao,pesototal,valortotal,datahora"
					+ ") VALUES (?,?,?,?,?);");

			stmt.setInt(1, o.getIdCliente());
			stmt.setString(2, o.getDescricao());
			stmt.setDouble(3, 0);
			stmt.setDouble(4, 0);
                        
                        
			o.setDataHora();//set actual date
			stmt.setString(5, o.getDataHora());
			
			
			
			stmt.executeUpdate();

			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
		} finally {
			Conexao.closeConnection(conexao, stmt);
	
		}
	}
	
    public Orcamento lerObjOrcamento(int idOrcamento) {

        Connection conexao = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Orcamento orcamento= new Orcamento(false);

        try {
            stmt = conexao.prepareStatement("SELECT * FROM orcamento WHERE idorcamento LIKE ?;");
            stmt.setInt(1, idOrcamento);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {

                
            	orcamento.setIdOrcamento(rs.getInt("idorcamento"));
            	orcamento.setIdCliente(rs.getInt("idcliente"));
            	orcamento.setDescricao(rs.getString("descricao"));
            	orcamento.setPesoTotal(rs.getDouble("pesototal"));
            	orcamento.setValorTotal(rs.getDouble("valortotal"));
            	orcamento.setDataHora(rs.getString("datahora"));
			    
				
				
            } else {JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id do orcamento selecionado na tabela!");}

        } catch (Exception e) { JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id do orcamento selecionado na tabela!");       } 
        finally { Conexao.closeConnection(conexao, stmt, rs);}
        return orcamento;
        
    }

	public List<Orcamento> lerTabela(){

		List<Orcamento> orcamentos = new ArrayList<>();
		
		try {
      		Connection conexao = Conexao.getConnection();

		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM orcamento;");
		ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Orcamento orcamento = new Orcamento(false);
				orcamento.setIdOrcamento(rs.getInt("idorcamento"));
				orcamento.setIdCliente(rs.getInt("idcliente"));
				orcamento.setDescricao(rs.getString("descricao"));
                                
                                //calcular peso total
                                Connection conexao2 = Conexao.getConnection();

                                PreparedStatement stmt2 = conexao2.prepareStatement("select pesototal from itens_orcamento where idorcamento = "+rs.getInt("idorcamento"));

                                ResultSet rs2 = stmt2.executeQuery();
                                
                                double pesototal = 0;

                                while(rs2.next()) {
                                        pesototal = pesototal + rs2.getDouble("pesototal");
                                }

                                orcamento.setPesoTotal(pesototal);
                                
                                Conexao.closeConnection(conexao2, stmt2, rs2);
                                
                                //calcular valor total
                                Connection conexao3 = Conexao.getConnection();

                                PreparedStatement stmt3 = conexao3.prepareStatement("select valortotal from itens_orcamento where idorcamento = "+rs.getInt("idorcamento"));

                                ResultSet rs3 = stmt3.executeQuery();
                                
                                double valorTotal = 0;

                                while(rs3.next()) {
                                        valorTotal = valorTotal + rs3.getDouble("valortotal");
                                }

                                orcamento.setValorTotal(valorTotal);
                                
                                Conexao.closeConnection(conexao3, stmt3, rs3);

				orcamento.setDataHora(rs.getString("datahora"));
				orcamentos.add(orcamento);
			}
                Conexao.closeConnection(conexao, stmt, rs);
		} catch (Exception e) 
		{JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'Orcamentos' "+e);}
		return orcamentos;
		
	}
	

//    public List<Orcamento> lerParaNome(String nome) {
//
//        Connection conexao = Conexao.getConnection();
//        
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        List<Orcamento> orcamentos = new ArrayList<>();
//
//        try {
//            stmt = conexao.prepareStatement("SELECT * FROM orcamento WHERE descricao LIKE ?;");
//            stmt.setString(1, "%"+nome+"%");
//            
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//
//                Orcamento orcamento = new Orcamento();
//
//                orcamento.setIdOrcamento(rs.getInt("idorcamento"));
//				orcamento.setTipo(rs.getString("tipo"));
//				orcamento.setDescricao(rs.getString("descricao"));
//				orcamento.setFolhas(rs.getString("folhas"));
//				orcamento.setObservacoes(rs.getString("observacoes"));
//				orcamentos.add(orcamento);
//				
//            }
//
//        } catch (Exception e) {        } 
//        finally { Conexao.closeConnection(conexao, stmt, rs);}
//        return orcamentos;
//        }
        
        

        public void delete(Orcamento o) {

            Connection conexao = Conexao.getConnection();
            
            PreparedStatement stmt = null;

            try {
                stmt = conexao.prepareStatement("DELETE FROM orcamento WHERE idorcamento = ?;");
                stmt.setInt(1, o.getIdOrcamento());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }

        }
        
        public void update(Orcamento o) {

            Connection conexao = Conexao.getConnection();
            
            PreparedStatement stmt = null;

            try {
                stmt = conexao.prepareStatement("UPDATE orcamento SET idcliente = ?,"
                		+ "descricao= ? WHERE idorcamento = ?;");
          
    			stmt.setInt(1, o.getIdCliente());
    			stmt.setString(2, o.getDescricao());
                        stmt.setInt(3, o.getIdOrcamento());


                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "ID do cliente nao existe, selecione uma ID valida!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }
        }

	}


