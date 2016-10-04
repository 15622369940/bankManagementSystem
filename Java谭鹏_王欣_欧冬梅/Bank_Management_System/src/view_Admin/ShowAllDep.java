package view_Admin;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import method.DepTableModal;


public class ShowAllDep extends JDialog{
	
	private JTable table = new JTable(){
		
		//��д����
		public boolean isCellEditable(int row, int column) {    //�к���			
			return false;			
		}
	};
	
	private JScrollPane pane = new JScrollPane();
	
	public ShowAllDep(){
		
		this.setTitle("�鿴���в���");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);   //����
		
		//�÷���ʵ�֣�д���ı�ģ��
		DefaultTableModel defaultTableModel = DepTableModal.getDepTableModal();
		table.setModel(defaultTableModel);
		
		pane.setBounds(20, 20, 470, 290);
		pane.getViewport().add(table);   
		add(pane);
		
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		//��Ⱦ��
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, cellRenderer);
	    
	    this.setLayout(null);
		this.setModal(true);
		this.setVisible(true);
		
		
		
		
		
	}

}
