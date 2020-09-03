package sistema;

	import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

	public class MySQLDumper {


	private static String path="C:\\Users\\luisf\\OneDrive\\Documents\\"+LocalDateTime.now().toString().replace(":", "-").replace(".", "-")+".sql";

	public static void export(){
	String dumpCommand = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump -u  root  --add-drop-database -B prisma -r C:\\Users\\luisf\\OneDrive\\Documents\\"+LocalDateTime.now().toString().replace(":", "-").replace(".", "-")+".sql";
	Runtime rt = Runtime.getRuntime();
	File test=new File(path);
	PrintStream ps;

	try{
	Process child = rt.exec(dumpCommand);
	ps=new PrintStream(test);
	InputStream in = child.getInputStream();
	int ch;
	while ((ch = in.read()) != -1) {
	ps.write(ch);
	System.out.write(ch); //to view it by console
	}

	InputStream err = child.getErrorStream();
	while ((ch = err.read()) != -1) {
	System.out.write(ch);
	}
	}catch(Exception exc) {
	exc.printStackTrace();
	}
	
	JOptionPane.showMessageDialog(null, "Arquivo de backup exportado para \"Documentos\"!");
	
	}

	}
