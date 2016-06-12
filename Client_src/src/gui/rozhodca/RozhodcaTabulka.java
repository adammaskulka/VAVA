package gui.rozhodca;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.Rozhodcovia;


/*
 * V tejto triede sa vytvara tabulka, kde su ulozene objednavky.
 */
@SuppressWarnings("serial")
public class RozhodcaTabulka extends JTable {
	
	private JTable tabulka;
	private RozhodcaTableModel tabulkaModel;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	private UpdateRozhodca updateref;
	
	public RozhodcaTabulka() {
		
		tabulkaModel = new RozhodcaTableModel();
		tabulka = new JTable(tabulkaModel);
		//updateref = new UpdateRozhodca();
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabulka.getColumn(rb.getString("gui.rozhodca.tabMeno")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.rozhodca.tabPriezvisko")).setCellRenderer( rightRenderer );
		tabulka.getColumn(rb.getString("gui.rozhodca.tabMesto")).setCellRenderer( rightRenderer );
		
		
		tabulka.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = tabulka.rowAtPoint(e.getPoint());
				
				if(e.getClickCount() == 2) {
					
					String priezvisko = (String) tabulkaModel.getValueAt(row, 1);
					//System.out.println(priezvisko);
					//updateref.setVisible(true);
					new UpdateRozhodca(priezvisko).setVisible(true);
					
				}
					//.out.println(
				
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(tabulka),BorderLayout.CENTER);
	}
	
	public void setData(List<Rozhodcovia> databaza) {
		tabulkaModel.setData(databaza);
	}
	
	
	public void refresh() {
		tabulkaModel.fireTableDataChanged();
	}

}
