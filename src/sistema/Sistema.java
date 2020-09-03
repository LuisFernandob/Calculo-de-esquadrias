package sistema;

import dao.VariaveisDAO;
import gui.TelaOrcamentos;

public class Sistema {

	public static void main(String[] args) {
		
		
		Registro reg = new Registro(); 
		
		reg.lerRegistro();
		
		Conexao.TestarSchemaPrisma();
		
		VariaveisDAO vdao = new VariaveisDAO();
		vdao.atualizarVariavelLocal();
		
		new TelaOrcamentos().setVisible(true);
		
	}
}
