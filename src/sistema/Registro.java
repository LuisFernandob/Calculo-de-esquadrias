package sistema;

import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class Registro {
	
	private String sKey = "fj9h23h9gerw-h89t";
	
	public void lerRegistro() {
		
        Preferences userPref = Preferences.userRoot();
		String PREF_KEY = "skcep";
		

		if (!userPref.get(PREF_KEY, PREF_KEY + " was not found.").toString().contentEquals(sKey)) {
		
			String serialInput = JOptionPane.showInputDialog("Digite o serial key para registrar o produto!").toString();
			

			if( serialInput.contentEquals(sKey)) {
        // Write Preferences information to HKCU (HKEY_CURRENT_USER),
        // HKCUSoftwareJavaSoftPrefsorg.kodejava
        userPref.put(PREF_KEY, sKey);
        JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
			} else {JOptionPane.showMessageDialog(null, "Serial key incorreta, fechando o programa!");
					System.exit(0);}
        }
	
	
}
}