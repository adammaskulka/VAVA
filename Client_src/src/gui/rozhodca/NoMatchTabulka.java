package gui.rozhodca;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;



public class NoMatchTabulka extends JTable {
	
	private JTable tabulka;
	private BadResultModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	private UpdateRozhodca updateref;
	
	public NoMatchTabulka() {
		
		tabulkaModel = new BadResultModel();
		tabulka = new JTable(tabulkaModel);
		updateref = new UpdateRozhodca();
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tabulka.getColumn(rb.getString("gui.test.referee")).setCellRenderer( rightRenderer );
		
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	public void setData(List<String> databaza) {
		tabulkaModel.setData(databaza);
	}
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}

}
