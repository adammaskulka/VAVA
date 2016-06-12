package gui.test;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


import entity.Testy;



public class TestTabulka extends JTable {
	
	private JTable tabulka;
	private TestTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	
	public TestTabulka() {
		
		//List<Testy> clubs = testServis.findAll();
		//System.out.println(clubs.get(0).toString());
		
		tabulkaModel = new TestTableModel();
		tabulka = new JTable(tabulkaModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.test.date")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.test.referee")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.test.event")).setCellRenderer( rightRenderer );
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}
	public void setData(List<Testy> databaza) {
		tabulkaModel.setData(databaza);
	}
	

}

