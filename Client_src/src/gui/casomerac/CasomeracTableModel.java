package gui.casomerac;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import entity.Casomeraci;

public class CasomeracTableModel extends AbstractTableModel {
	
	private List<Casomeraci> databaza;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private String[] colNames = {rb.getString("gui.rozhodca.tabMeno"), 
			rb.getString("gui.rozhodca.tabPriezvisko"),
			rb.getString("gui.rozhodca.tabMesto") };
	
	List<Color> rowColours = Arrays.asList(
	        Color.RED,
	        Color.GREEN,
	        Color.CYAN
	    );

	    public void setRowColour(int row, Color c) {
	        rowColours.set(row, c);
	        fireTableRowsUpdated(row, row);
	    }

	    public Color getRowColour(int row) {
	        return rowColours.get(row);
	    }
	
	public CasomeracTableModel() {
		
	}
	
	public void setData(List<Casomeraci> databaza) {
		this.databaza = databaza;
	}
	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		//return databaza.size();
		return databaza.size();
	}

	
	public Object getValueAt(int riadok, int stlpec) {
		Casomeraci rozhodca = databaza.get(riadok);
		
		switch(stlpec) {
		case 0: return rozhodca.getName();
		case 1: return rozhodca.getSurname();
		case 2: return rozhodca.getCity();
		
		}
		fireTableRowsUpdated(riadok, stlpec);
		return null;
	}

}
