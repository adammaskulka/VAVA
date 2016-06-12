package gui.tim;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Timy;

public class TimTabulka extends JTable {
	
	private JTable tabulka;
	private TimTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	public TimTabulka() {
		
		tabulkaModel = new TimTableModel();
		tabulka = new JTable(tabulkaModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.tim.tabMeno")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.tim.tabHomeCol")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.tim.tabAwayCol")).setCellRenderer( rightRenderer );
		
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}


	public void setData(List<Timy> databaza) {
		tabulkaModel.setData(databaza);
	}

}
