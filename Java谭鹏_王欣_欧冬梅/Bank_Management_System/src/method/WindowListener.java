package method;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import method.WindowListener;


public class WindowListener extends WindowAdapter{

	public void windowClosing(WindowEvent e) {

		int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
				JOptionPane.YES_NO_OPTION);
		
		if(n==0){
			System.exit(0);
		}	
		
		
		
	}
	
	
}
