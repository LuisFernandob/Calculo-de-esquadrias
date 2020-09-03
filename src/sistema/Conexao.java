package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

@SuppressWarnings({ "unused" })
public class Conexao {
	
	private static final String USUARIOBD = "root";
	private static final String ENDERECOPRISMA = "jdbc:mysql://localhost:3306/prisma?serverTimezone=GMT";
	private static final String SENHABD = "12345678";
	private static final String ENDERECOBD = "jdbc:mysql://localhost:3306/?serverTimezone=GMT";
	
	private final String usu = USUARIOBD;
	private final String pass = SENHABD;
	
	public String getUsuarioBD() {
		return this.usu;
	}
	public String getSenhaBD() {
		return this.pass;
	}
	
	
	public static Connection getConnection() {
		
            try{Class.forName("com.mysql.cj.jdbc.Driver");} catch (ClassNotFoundException e)
            {JOptionPane.showMessageDialog(null, "Erro ao carregar a classe do driver!");}

           
		try { 
                    return DriverManager.getConnection(ENDERECOPRISMA,USUARIOBD,SENHABD);
		} catch(SQLException excecao) { 
			JOptionPane.showMessageDialog(null, "Erro ao conectar no MySQL!");
			throw new RuntimeException(excecao);
			}
	}
	
	public static void TestarSchemaPrisma() {
		

		try {
		Connection conexao = Conexao.getConnection();
		closeConnection(conexao);
		} catch (RuntimeException e ) {
			
			if(JOptionPane.showConfirmDialog(null, "Não foi localizado o banco de dados "
					+ "\"prisma\", deseja cria-lo agora?")==0) {
				
				
				PreparedStatement stmt;
				
				try {
					
					Connection conexao2 = DriverManager.getConnection(ENDERECOBD,USUARIOBD,SENHABD);
					
					stmt = conexao2.prepareStatement("CREATE SCHEMA IF NOT EXISTS `prisma` DEFAULT CHARACTER SET latin1 ;");						
					stmt.execute();
		            stmt = conexao2.prepareStatement("USE `prisma` ;");
		            stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`cliente` (\r\n" + 
		            		"				  `idcliente` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
		            		"				  `nome` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `empresa` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `telefone` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `endereço` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `email` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `datanascimento` DATE NULL DEFAULT NULL,\r\n" + 
		            		"				  `cnpj` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `cpf` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `observacoes` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  PRIMARY KEY (`idcliente`))\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				AUTO_INCREMENT = 52\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
		            stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`componente` (\r\n" + 
		            		"				  `codigo_componente` VARCHAR(45) NOT NULL,\r\n" + 
		            		"				  `tipo` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `descricao` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  `comprimento_barra_m` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `unidade` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `largura_mm` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `largura_encaixe_mm` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `peso_por_metro` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  PRIMARY KEY (`codigo_componente`))\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`orcamento` (\r\n" + 
		            		"				  `idorcamento` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
		            		"				  `idcliente` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `descricao` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  `pesototal` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `valortotal` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `datahora` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,\r\n" + 
		            		"				  PRIMARY KEY (`idorcamento`),\r\n" + 
		            		"				  INDEX `idcliente_idx` (`idcliente` ASC) VISIBLE,\r\n" + 
		            		"				  CONSTRAINT `idcliente`\r\n" + 
		            		"				    FOREIGN KEY (`idcliente`)\r\n" + 
		            		"				    REFERENCES `prisma`.`cliente` (`idcliente`))\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				AUTO_INCREMENT = 23\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`produto` (\r\n" + 
		            		"				  `idproduto` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
		            		"				  `tipo` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `descricao` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  `folhas` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `observacoes` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  PRIMARY KEY (`idproduto`))\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				AUTO_INCREMENT = 20\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`itens_orcamento` (\r\n" + 
		            		"				  `iditens_orcamento` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
		            		"				  `idorcamento` INT(11) NOT NULL,\r\n" + 
		            		"				  `idproduto` INT(11) NOT NULL,\r\n" + 
		            		"				  `arremate` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `arremateinferior` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
		            		"				  `contramarco` VARCHAR(45) NULL DEFAULT NULL,\r\n" +
		            		"				  `contramarcoinferior` VARCHAR(45) NULL DEFAULT NULL,\r\n" +
		            		"				  `largura_mm` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `altura_mm` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `quantidade` INT(11) NULL DEFAULT NULL,\r\n" + 
		            		"				  `pesototal` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `valortotal` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  PRIMARY KEY (`iditens_orcamento`),\r\n" + 
		            		"				  UNIQUE INDEX `iditens_orcamento_UNIQUE` (`iditens_orcamento` ASC) VISIBLE,\r\n" + 
		            		"				  INDEX `idorcamento_idx` (`idorcamento` ASC) VISIBLE,\r\n" + 
		            		"				  INDEX `idproduto_idx` (`idproduto` ASC) VISIBLE,\r\n" + 
		            		"				  CONSTRAINT `idorcamento`\r\n" + 
		            		"				    FOREIGN KEY (`idorcamento`)\r\n" + 
		            		"				    REFERENCES `prisma`.`orcamento` (`idorcamento`)\r\n" + 
		            		"				    ON DELETE CASCADE\r\n" + 
		            		"				    ON UPDATE CASCADE,\r\n" + 
		            		"				  CONSTRAINT `idproduto`\r\n" + 
		            		"				    FOREIGN KEY (`idproduto`)\r\n" + 
		            		"				    REFERENCES `prisma`.`produto` (`idproduto`)\r\n" + 
		            		"				    ON DELETE CASCADE\r\n" + 
		            		"				    ON UPDATE CASCADE)\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				AUTO_INCREMENT = 20\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`tipologia` (\r\n" + 
		            		"				  `idtipologia` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
		            		"				  `id_produto` INT(11) NOT NULL,\r\n" + 
		            		"				  `codigo_componente` VARCHAR(45) NOT NULL,\r\n" + 
		            		"				  `descricao_componente` VARCHAR(120) NULL DEFAULT NULL,\r\n" + 
		            		"				  `multiplicadorlargura` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `offsetlargura_mm` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `multiplicadoraltura` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `offsetaltura_mm` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `peso_por_metro` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  PRIMARY KEY (`idtipologia`),\r\n" + 
		            		"				  INDEX `codigo_componente_idx` (`codigo_componente` ASC) VISIBLE,\r\n" + 
		            		"				  INDEX `idproduto_idx` (`id_produto` ASC) VISIBLE,\r\n" + 
		            		"				  CONSTRAINT `codigo_componente`\r\n" + 
		            		"				    FOREIGN KEY (`codigo_componente`)\r\n" + 
		            		"				    REFERENCES `prisma`.`componente` (`codigo_componente`)\r\n" + 
		            		"				    ON DELETE CASCADE\r\n" + 
		            		"				    ON UPDATE CASCADE,\r\n" + 
		            		"				  CONSTRAINT `id_produto`\r\n" + 
		            		"				    FOREIGN KEY (`id_produto`)\r\n" + 
		            		"				    REFERENCES `prisma`.`produto` (`idproduto`)\r\n" + 
		            		"				    ON DELETE CASCADE\r\n" + 
		            		"				    ON UPDATE CASCADE)\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				AUTO_INCREMENT = 35\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
		            stmt = conexao2.prepareStatement("CREATE TABLE IF NOT EXISTS `prisma`.`variaveis` (\r\n" + 
		            		"				  `referencia` INT(1) NULL DEFAULT NULL,\r\n" + 
		            		"				  `precoaluminio` DOUBLE NULL DEFAULT NULL,\r\n" + 
		            		"				  `porcentagemmdo` INT(11) NULL DEFAULT NULL\r\n" + 
		            		"				  `precocontramarco` DOUBLE NULL DEFAULT NULL)\r\n" + 
		            		"				ENGINE = InnoDB\r\n" + 
		            		"				DEFAULT CHARACTER SET = latin1;");
				    stmt.execute();
				    
				    stmt = conexao2.prepareStatement("INSERT INTO prisma.variaveis (referencia,precoaluminio,"
				    		+ "porcentagemmdo) VALUES (1,0,0);");
				    stmt.execute();
			    
				    closeConnection(conexao2, stmt);
				    
				    JOptionPane.showMessageDialog(null, "Banco de dados \"Prisma\" criado com sucesso!");
				            
				            
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

				


					
				
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Sistema sem banco de dados configurado: "
						+ "o programa será fechado!");
				System.exit(0);
				
			}
			
		}
		
		
	}
	
	public static void closeConnection(Connection conexao,PreparedStatement stmt,ResultSet rs) {
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe ResultSet para encerrar"+e);
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Statement para encerrar"+e);
		}
		try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Conexao para encerrar"+e);
		}
		
	}
	
	public static void closeConnection(Connection conexao) {
		
		try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Conexao para encerrar"+e);
		}
		
	}
	
	public static void closeConnection(PreparedStatement stmt) {
		
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Statement para encerrar"+e);
		}
		
	}
	
	public static void closeConnection(ResultSet rs) {
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe ResultSet para encerrar"+e);
		}
		
	}
	
	public static void closeConnection(Connection conexao,PreparedStatement stmt) {
		
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Statement para encerrar"+e);
		}
		try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nao existe Conexao para encerrar"+e);
		}
		
	}
	
}
