package gui.stadion;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Stadiony;



public class StadionTabulka extends JTable {
	
	private JTable tabulka;
	private StadionTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	
	public StadionTabulka() {
		
		tabulkaModel = new StadionTableModel();
		tabulka = new JTable(tabulkaModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.stadion.tabMeno")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.stadion.tabUlica")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.stadion.tabMesto")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.stadion.tabKlub")).setCellRenderer( rightRenderer );
		
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	public void setData(List<Stadiony> databaza) {
		tabulkaModel.setData(databaza);
	}
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}

}
