package gui.test;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



public class BadResultTabulka extends JTable {
	
	private JTable tabulka;
	private BadResultModel testModel;
	private TestTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	
	public BadResultTabulka() {
		
		//List<Testy> clubs = testServis.findAll();
		//System.out.println(clubs.get(0).toString());
		
				testModel = new BadResultModel();
		tabulka = new JTable(testModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tabulka.getColumn(rb.getString("gui.test.referee")).setCellRenderer( rightRenderer );
		
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}
	public void setData(List<String> databaza) {
		testModel.setData(databaza);
	}
	

}

