package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class ItemOrcamentoDAO {
	Conexao mysql = new Conexao();

	public void criarItemOrcamento(ItemOrcamento o) {

                double pesoTotal = 0;
                double pesoTotalContramarco = 0;

		try {
                    Connection conexao = Conexao.getConnection();
                    PreparedStatement stmt = conexao.prepareStatement("INSERT INTO itens_orcamento" 
		    + " (idorcamento,idproduto, arremate,arremateinferior, contramarco,contramarcoinferior, largura_mm,altura_mm,quantidade,"
			+ "pesototal,valortotal) VALUES (?,?,?,?,?,?,?,?,?,?,?);");

			stmt.setInt(1, o.getIdOrcamento());
            stmt.setInt(2, o.getIdProduto());
            stmt.setString(3, o.getArremate()); 
            stmt.setString(4, o.getArremateInferior()); 
            stmt.setString(5, o.getContramarco());
            stmt.setString(6, o.getContramarcoInferior()); 
			stmt.setInt(7, o.getLarguraMm());
			stmt.setInt(8, o.getAlturaMm());
			stmt.setInt(9, o.getQuantidade());
                
			// Buscar multiplicadores de todos os componentes da tipologia, multiplicar pelo tamanho do produto e subtrair os offsets
			try {

				Connection conexao2 = Conexao.getConnection();
                        PreparedStatement stmt2 = conexao2.prepareStatement("SELECT * FROM tipologia"
                                + " WHERE id_produto = ?");
                        stmt2.setInt(1, o.getIdProduto());
                        ResultSet rs2 = stmt2.executeQuery();

                                    while(rs2.next()) {
                                            	
                                            double largura = ((rs2.getDouble("multiplicadorlargura")*o.getLarguraMm())-rs2.getDouble("offsetlargura_mm"))/1000;
                                            double altura = ((rs2.getDouble("multiplicadoraltura")*o.getAlturaMm())-rs2.getDouble("offsetaltura_mm"))/1000;
                                            double comprimentoTotal = largura + altura;
                                            double pesoTotalComponente = comprimentoTotal*rs2.getDouble("peso_por_metro");
                                            pesoTotal = pesoTotal + pesoTotalComponente;                                     
                                    }
                            Conexao.closeConnection(conexao2, stmt2, rs2);
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Erro ao incluir o item: "+e);
            }
                        
			//adicionar arremate (Sup. + Lat.) (se tiver) ao peso total do produto
			if(!o.getArremate().contentEquals("Não")) {
			
				try {
			
					Connection conexao3 = Conexao.getConnection();
			                PreparedStatement stmt3 = conexao3.prepareStatement("SELECT peso_por_metro FROM componente"
			                        + " WHERE codigo_componente = ?");
			                stmt3.setString(1, o.getArremate());
			                
			                ResultSet rs3 = stmt3.executeQuery();
			
			                            if(rs3.next()) {
			                                    	
			                                    double largura = o.getLarguraMm()/1000;
			                                    double altura = 2*o.getAlturaMm()/1000;
			                                    double comprimentoTotal = largura + altura;
			                                    double pesoTotalComponente = comprimentoTotal*rs3.getDouble("peso_por_metro");
			                                    pesoTotal = pesoTotal + pesoTotalComponente;                                     
			                            }
			                    Conexao.closeConnection(conexao3, stmt3, rs3);
			    } catch (Exception e) {
			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o arremate (Sup. + Lat.) no calculo de peso do produto: "+e);
			    }
			} 
			
			//adicionar arremate (Inferior) (se tiver) ao peso total do produto
			if(!o.getArremateInferior().contentEquals("Não")) {
			
				try {
			
					Connection conexao3 = Conexao.getConnection();
			                PreparedStatement stmt3 = conexao3.prepareStatement("SELECT peso_por_metro FROM componente"
			                        + " WHERE codigo_componente = ?");
			                stmt3.setString(1, o.getArremateInferior());
			                
			                ResultSet rs3 = stmt3.executeQuery();
			
			                            if(rs3.next()) {
			                                    	
			                                    double largura = o.getLarguraMm()/1000;
			                                    
			                                    double pesoTotalComponente = largura*rs3.getDouble("peso_por_metro");
			                                    pesoTotal = pesoTotal + pesoTotalComponente;                                     
			                            }
			                    Conexao.closeConnection(conexao3, stmt3, rs3);
			    } catch (Exception e) {
			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o arremate (Sup. + Lat.) no calculo de peso do produto: "+e);
			    }
			} 
			
			
			//adicionar contramarco (Sup. + Lat.) (se tiver) ao peso total do produto
			if(!o.getContramarco().contentEquals("Não")) {
				
				try {
			
					Connection conexao4 = Conexao.getConnection();
			                PreparedStatement stmt4 = conexao4.prepareStatement("SELECT peso_por_metro FROM componente"
			                        + " WHERE codigo_componente = ?");
			                stmt4.setString(1, o.getContramarco());
			                
			                ResultSet rs4 = stmt4.executeQuery();
			
			                            if(rs4.next()) {
			                                    	
			                                    double largura = o.getLarguraMm()/1000;
			                                    double altura = 2*o.getAlturaMm()/1000;
			                                    double comprimentoTotal = largura + altura;
			                                    double pesoTotalComponente = comprimentoTotal*rs4.getDouble("peso_por_metro");
			                                    pesoTotalContramarco = pesoTotalContramarco + pesoTotalComponente;                                     
			                            }
			                    Conexao.closeConnection(conexao4, stmt4, rs4);
			    } catch (Exception e) {
			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o contramarco (Sup. + Lat.) no calculo de peso do produto: "+e);
			    }
			} 
			
			//adicionar contramarco (Inferior) (se tiver) ao peso total do produto
			if(!o.getContramarcoInferior().contentEquals("Não")) {
				
				try {
			
					Connection conexao4 = Conexao.getConnection();
			                PreparedStatement stmt4 = conexao4.prepareStatement("SELECT peso_por_metro FROM componente"
			                        + " WHERE codigo_componente = ?");
			                stmt4.setString(1, o.getContramarcoInferior());
			                
			                ResultSet rs4 = stmt4.executeQuery();
			
			                            if(rs4.next()) {
			                                    	
			                                    double largura = o.getLarguraMm()/1000;
			                                    double pesoTotalComponente = largura*rs4.getDouble("peso_por_metro");
			                                    pesoTotalContramarco = pesoTotalContramarco + pesoTotalComponente;                                     
			                            }
			                    Conexao.closeConnection(conexao4, stmt4, rs4);
			    } catch (Exception e) {
			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o contramarco (Sup. + Lat.) no calculo de peso do produto: "+e);
			    }
			} 
			
			pesoTotal = pesoTotal*o.getQuantidade();
			pesoTotalContramarco = pesoTotalContramarco*o.getQuantidade();

						                        
			//calcular PESO TOTAL!!!!!!!!!!!!!!!                        
			stmt.setDouble(10, pesoTotal+pesoTotalContramarco);
			                        
			stmt.setDouble(11, (Variaveis.getPrecoAluminio()*pesoTotal)+(Variaveis.getPrecoContramarco()*pesoTotalContramarco));                
			                        
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			Conexao.closeConnection(conexao, stmt);
			} catch (SQLException e) {
			
					JOptionPane.showMessageDialog(null, "Erro ao incluir o produto no orçamento!" + e);
			
			} 
			
	}
	
	public ItemOrcamento lerObjItemOrcamento(int idItemOrcamento) {

	        Connection conexao = Conexao.getConnection();
	        
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        ItemOrcamento itemOrcamento = new ItemOrcamento();

	        try {
	            stmt = conexao.prepareStatement("SELECT * FROM itens_orcamento WHERE iditens_orcamento LIKE ?;");
	            stmt.setInt(1, idItemOrcamento);
	            
	            rs = stmt.executeQuery();
	            
	            if (rs.next()) {

	            	itemOrcamento.setIdItensOrcamento(rs.getInt("iditens_orcamento"));
	            	itemOrcamento.setIdOrcamento(rs.getInt("idorcamento"));
	            	itemOrcamento.setIdProduto(rs.getInt("idproduto"));
	            	itemOrcamento.setContramarco(rs.getString("contramarco"));
	            	itemOrcamento.setContramarcoInferior(rs.getString("contramarcoinferior"));
	            	itemOrcamento.setArremateInferior(rs.getString("arremateinferior"));
	            	itemOrcamento.setArremate(rs.getString("arremate"));
	            	itemOrcamento.setLarguraMm(rs.getInt("largura_mm"));
	            	itemOrcamento.setAlturaMm(rs.getInt("altura_mm"));
	            	itemOrcamento.setQuantidade(rs.getInt("quantidade"));
	            	itemOrcamento.setPesoTotal(rs.getDouble("pesototal"));
	            	itemOrcamento.setValorTotal(rs.getDouble("valortotal"));
					
	            } else {JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id do \"ItemOrcamento\" selecionado na tabela!");}

	        } catch (Exception e) { JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id do \"ItemOrcamento\" selecionado na tabela!");       } 
	        finally { Conexao.closeConnection(conexao, stmt, rs);}
	        return itemOrcamento;
	        }
	
	public String[] carregarComboxContramarco() {	
		
		Connection conexao = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String[] comboboxInsertString;
        int cont = 2;
        int rows = 0;
		
        try {
            stmt = conexao.prepareStatement("SELECT * FROM componente WHERE tipo LIKE ?;");
            stmt.setString(1, "Contramarco");
            
            rs = stmt.executeQuery();
            
            if (rs.last()) {
               rows = rs.getRow();
         
                rs.beforeFirst();
            }
            
            comboboxInsertString = new String[rows+2];
            
            comboboxInsertString[0] = "Selecione um item...";
            comboboxInsertString[1] = "Não";

                      	
            	while (rs.next()) {

            		comboboxInsertString[cont] = rs.getString("codigo_componente");
            		cont += 1;
            		
            	}
            	
        } catch(Exception e) { JOptionPane.showMessageDialog(null, "Erro ao carregar a comboBox "
        		+ "de contramarcos: provavelmente não há contramarcos cadastrados! "+e);
        		comboboxInsertString = new String[0];
        }
        
 		return comboboxInsertString;

	}

	public String[] carregarComboxArremate() {
		
		Connection conexao = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String[] comboboxInsertString;
        int cont = 2;
        int rows = 0;
		
        try {
            stmt = conexao.prepareStatement("SELECT * FROM componente WHERE tipo LIKE ?;");
            stmt.setString(1, "Arremate");
            
            rs = stmt.executeQuery();
            
            if (rs.last()) {
               rows = rs.getRow();
         
                rs.beforeFirst();
            }
            
            comboboxInsertString = new String[rows+2];
            
            comboboxInsertString[0] = "Selecione um item...";
            comboboxInsertString[1] = "Não";
          	
            	while (rs.next()) {

            		comboboxInsertString[cont] = rs.getString("codigo_componente");
            		cont += 1;
            		
            	}
            	
        } catch(Exception e) { JOptionPane.showMessageDialog(null, "Erro ao carregar a comboBox "
        		+ "de arremates: provavelmente não há arremates cadastrados! "+e);
        		comboboxInsertString = new String[0];
        }
        
 		return comboboxInsertString;

		
	}
	
	public List<ItemOrcamento> lerTabela(){
		
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ItemOrcamento> itensOrcamento = new ArrayList<>();
		
		try {
	//pegar descricao do produto e componente
	    stmt = conexao.prepareStatement("SELECT * FROM itens_orcamento");

		rs = stmt.executeQuery();
		
			while(rs.next()) {
				ItemOrcamento itemOrcamento = new ItemOrcamento();
				itemOrcamento.setIdItensOrcamento(rs.getInt("iditens_orcamento"));                                
				itemOrcamento.setIdOrcamento(rs.getInt("idorcamento"));
				itemOrcamento.setIdProduto(rs.getInt("idproduto"));
				itemOrcamento.setArremate(rs.getString("arremate"));
				itemOrcamento.setArremateInferior(rs.getString("arremateinferior"));
				itemOrcamento.setContramarco(rs.getString("contramarco"));
				itemOrcamento.setContramarcoInferior(rs.getString("contramarcoinferior"));
				itemOrcamento.setLarguraMm(rs.getInt("largura_mm"));
				itemOrcamento.setAlturaMm(rs.getInt("altura_mm"));
				itemOrcamento.setQuantidade(rs.getInt("quantidade"));
                                itemOrcamento.setPesoTotal(rs.getDouble("pesototal"));
                                itemOrcamento.setValorTotal(rs.getDouble("valortotal"));
				itensOrcamento.add(itemOrcamento);
			}
		} catch (Exception e) 
		{JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'item orcamento' "+e);}
		finally { Conexao.closeConnection(conexao, stmt, rs);}
		return itensOrcamento;
	}
	

	// funcao para localizar um componente na tabela
    public List<ItemOrcamento> lerParaProduto(int idOrcamento) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ItemOrcamento> itensOrcamento = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM itens_orcamento WHERE idorcamento = ?");
            stmt.setInt(1, idOrcamento);           
            rs = stmt.executeQuery();
            while (rs.next()) {
                                           
                ItemOrcamento itemOrcamento = new ItemOrcamento();
                itemOrcamento.setIdItensOrcamento(rs.getInt("iditens_orcamento"));     
		itemOrcamento.setIdOrcamento(rs.getInt("idorcamento"));
                itemOrcamento.setIdProduto(rs.getInt("idproduto"));
                itemOrcamento.setArremate(rs.getString("arremate"));
				itemOrcamento.setArremateInferior(rs.getString("arremateinferior"));
				itemOrcamento.setContramarco(rs.getString("contramarco"));
				itemOrcamento.setContramarcoInferior(rs.getString("contramarcoinferior"));
                itemOrcamento.setLarguraMm(rs.getInt("largura_mm"));
                itemOrcamento.setAlturaMm(rs.getInt("altura_mm"));
                itemOrcamento.setQuantidade(rs.getInt("quantidade"));
                itemOrcamento.setPesoTotal(rs.getDouble("pesototal"));	
                itemOrcamento.setValorTotal(rs.getDouble("valortotal"));
                itensOrcamento.add(itemOrcamento);				
            }
        } catch (Exception e) {        } 
        finally { Conexao.closeConnection(conexao, stmt, rs);}
        return itensOrcamento;
        }        
        public void delete(ItemOrcamento o) {
            Connection conexao = Conexao.getConnection();    
            PreparedStatement stmt = null;
            try {
                stmt = conexao.prepareStatement("DELETE FROM itens_orcamento WHERE iditens_orcamento = ?");
                stmt.setInt(1, o.getIdItensOrcamento());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            } finally {
                Conexao.closeConnection(conexao, stmt);
            }

        }
        
        public void update(ItemOrcamento o) {
            
            double pesoTotal = 0;
            double pesoTotalContramarco = 0;
                     
            try {
                
                Connection conexao = Conexao.getConnection();           
                PreparedStatement stmt = conexao.prepareStatement("UPDATE itens_orcamento SET "
                		+ "arremate = ?, arremateinferior = ?, contramarco = ?, contramarcoinferior = ?, largura_mm = ?, "
                		+ "altura_mm = ?, quantidade = ?, pesototal = ?, valortotal = ? WHERE iditens_orcamento = ?");              
                stmt.setString(1, o.getArremate());
                stmt.setString(2, o.getArremateInferior());
                stmt.setString(3, o.getContramarco());
                stmt.setString(4, o.getContramarcoInferior());
                stmt.setInt(5, o.getLarguraMm());
      			stmt.setInt(6, o.getAlturaMm());
      			stmt.setInt(7, o.getQuantidade());
                        
                try {
                	
		            Connection conexao2 = Conexao.getConnection();
		            PreparedStatement stmt2 = conexao2.prepareStatement("SELECT * FROM tipologia"
		                    + " WHERE id_produto = ?");
		            stmt2.setInt(1, o.getIdProduto());
		            ResultSet rs2 = stmt2.executeQuery();
		                    
                    while(rs2.next()) {
                            	
                    	double largura = ((rs2.getDouble("multiplicadorlargura")*o.getLarguraMm())-rs2.getDouble("offsetlargura_mm"))/1000;
                    	double altura = ((rs2.getDouble("multiplicadoraltura")*o.getAlturaMm())-rs2.getDouble("offsetaltura_mm"))/1000;
                    	double comprimentoTotal = largura + altura;
                    	double pesoTotalComponente = comprimentoTotal*rs2.getDouble("peso_por_metro");
                           pesoTotal = pesoTotal + pesoTotalComponente;                  
                    }
		                        
	                Conexao.closeConnection(conexao2, stmt2, rs2);
	                
                } catch (Exception e){
                	
                	JOptionPane.showMessageDialog(null, "Erro ao incluir o item: "+e);
                
                }
                
            	//adicionar arremate (Sup. + Lat.) (se tiver) ao peso total do produto
    			if(!o.getArremate().contentEquals("Não")) {
    			
    				try {
    			
    					Connection conexao3 = Conexao.getConnection();
    			                PreparedStatement stmt3 = conexao3.prepareStatement("SELECT peso_por_metro FROM componente"
    			                        + " WHERE codigo_componente = ?");
    			                stmt3.setString(1, o.getArremate());
    			                
    			                ResultSet rs3 = stmt3.executeQuery();
    			
    			                            if(rs3.next()) {
    			                                    	
    			                                    double largura = o.getLarguraMm()/1000;
    			                                    double altura = 2*o.getAlturaMm()/1000;
    			                                    double comprimentoTotal = largura + altura;
    			                                    double pesoTotalComponente = comprimentoTotal*rs3.getDouble("peso_por_metro");
    			                                    pesoTotal = pesoTotal + pesoTotalComponente;                                     
    			                            }
    			                    Conexao.closeConnection(conexao3, stmt3, rs3);
    			    } catch (Exception e) {
    			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o arremate (Sup. + Lat.) no calculo de peso do produto: "+e);
    			    }
    			} 
    			
    			//adicionar arremate (Inferior) (se tiver) ao peso total do produto
    			if(!o.getArremateInferior().contentEquals("Não")) {
    			
    				try {
    			
    					Connection conexao3 = Conexao.getConnection();
    			                PreparedStatement stmt3 = conexao3.prepareStatement("SELECT peso_por_metro FROM componente"
    			                        + " WHERE codigo_componente = ?");
    			                stmt3.setString(1, o.getArremateInferior());
    			                
    			                ResultSet rs3 = stmt3.executeQuery();
    			
    			                            if(rs3.next()) {
    			                                    	
    			                                    double largura = o.getLarguraMm()/1000;
    			                                    
    			                                    double pesoTotalComponente = largura*rs3.getDouble("peso_por_metro");
    			                                    pesoTotal = pesoTotal + pesoTotalComponente;                                     
    			                            }
    			                    Conexao.closeConnection(conexao3, stmt3, rs3);
    			    } catch (Exception e) {
    			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o arremate (Sup. + Lat.) no calculo de peso do produto: "+e);
    			    }
    			} 
    			
    			
    			//adicionar contramarco (Sup. + Lat.) (se tiver) ao peso total do produto
    			if(!o.getContramarco().contentEquals("Não")) {
    				
    				try {
    			
    					Connection conexao4 = Conexao.getConnection();
    			                PreparedStatement stmt4 = conexao4.prepareStatement("SELECT peso_por_metro FROM componente"
    			                        + " WHERE codigo_componente = ?");
    			                stmt4.setString(1, o.getContramarco());
    			                
    			                ResultSet rs4 = stmt4.executeQuery();
    			
    			                            if(rs4.next()) {
    			                                    	
    			                                    double largura = o.getLarguraMm()/1000;
    			                                    double altura = 2*o.getAlturaMm()/1000;
    			                                    double comprimentoTotal = largura + altura;
    			                                    double pesoTotalComponente = comprimentoTotal*rs4.getDouble("peso_por_metro");
    			                                    pesoTotalContramarco = pesoTotalContramarco + pesoTotalComponente;                                     
    			                            }
    			                    Conexao.closeConnection(conexao4, stmt4, rs4);
    			    } catch (Exception e) {
    			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o contramarco (Sup. + Lat.) no calculo de peso do produto: "+e);
    			    }
    			} 
    			
    			//adicionar contramarco (Inferior) (se tiver) ao peso total do produto
    			if(!o.getContramarcoInferior().contentEquals("Não")) {
    				
    				try {
    			
    					Connection conexao4 = Conexao.getConnection();
    			                PreparedStatement stmt4 = conexao4.prepareStatement("SELECT peso_por_metro FROM componente"
    			                        + " WHERE codigo_componente = ?");
    			                stmt4.setString(1, o.getContramarcoInferior());
    			                
    			                ResultSet rs4 = stmt4.executeQuery();
    			
    			                            if(rs4.next()) {
    			                                    	
    			                                    double largura = o.getLarguraMm()/1000;
    			                                    double pesoTotalComponente = largura*rs4.getDouble("peso_por_metro");
    			                                    pesoTotalContramarco = pesoTotalContramarco + pesoTotalComponente;                                     
    			                            }
    			                    Conexao.closeConnection(conexao4, stmt4, rs4);
    			    } catch (Exception e) {
    			    	JOptionPane.showMessageDialog(null, "Erro ao incluir o contramarco (Sup. + Lat.) no calculo de peso do produto: "+e);
    			    }
    			} 
    			
    			pesoTotal = pesoTotal*o.getQuantidade();
    			pesoTotalContramarco = pesoTotalContramarco*o.getQuantidade();     
    			
        //calcular PESO TOTAL!!!!!!!!!!!!!!!
        stmt.setDouble(8, pesoTotal+pesoTotalContramarco); 
        stmt.setDouble(9, (pesoTotal*Variaveis.getPrecoAluminio())+(Variaveis.getPrecoContramarco()*pesoTotalContramarco));
              
		stmt.setInt(10, o.getIdItensOrcamento());
		
        stmt.executeUpdate();
        
        Conexao.closeConnection(conexao, stmt);
        
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        
            } catch (SQLException e) {
            	
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
                
            }
        }
  
}
