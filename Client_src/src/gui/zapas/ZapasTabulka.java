package gui.zapas;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Zapasy;

public class ZapasTabulka extends JTable {
	
	private JTable tabulka;
	private ZapasTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	public ZapasTabulka() {
		
		tabulkaModel = new ZapasTableModel();
		tabulka = new JTable(tabulkaModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.zapas.tabDate")).setCellRenderer( rightRenderer );
		
		tabulka.getColumn(rb.getString("gui.zapas.tabHome")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.zapas.tabAway")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.zapas.tabHR")).setCellRenderer( rightRenderer );
		
		tabulka.getColumn(rb.getString("gui.zapas.tabCR")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.zapas.tabStadium")).setCellRenderer( rightRenderer );
		
		tabulka.getColumn(rb.getString("gui.zapas.tabHR")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.zapas.tabCR")).setCellRenderer( rightRenderer );
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	public void setData(List<Zapasy> databaza) {
		tabulkaModel.setData(databaza);
	}
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}
}
