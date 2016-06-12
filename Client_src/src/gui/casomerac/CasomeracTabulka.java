package gui.casomerac;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Casomeraci;


/*
 * V tejto triede sa vytvara tabulka, kde su ulozene objednavky.
 */
@SuppressWarnings("serial")
public class CasomeracTabulka extends JTable {
	
	private JTable tabulka;
	private CasomeracTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	public CasomeracTabulka() {
		
		tabulkaModel = new CasomeracTableModel();
		tabulka = new JTable(tabulkaModel);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.rozhodca.tabMeno")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.rozhodca.tabPriezvisko")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.rozhodca.tabMesto")).setCellRenderer( rightRenderer );
		
		
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	public void setData(List<Casomeraci> databaza) {
		tabulkaModel.setData(databaza);
	}
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}

}
