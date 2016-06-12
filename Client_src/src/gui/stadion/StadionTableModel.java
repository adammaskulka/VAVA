package gui.stadion;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import entity.Stadiony;

public class StadionTableModel extends AbstractTableModel {
	
	private List<Stadiony> databaza;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private String[] colNames = {rb.getString("gui.stadion.tabMeno"), 
			rb.getString("gui.stadion.tabUlica"), rb.getString("gui.stadion.tabMesto"), rb.getString("gui.stadion.tabKlub") };
	
	
	
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
	
	public StadionTableModel() {
		
	}
	
	
	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public void setData(List<Stadiony> databaza) {
		this.databaza = databaza;
	}
	@Override
	public int getRowCount() {
		//return databaza.size();
		return databaza.size();
	}

	public Object getValueAt(int riadok, int stlpec) {
		Stadiony rozhodca = databaza.get(riadok);
		
		switch(stlpec) {
		case 0: return rozhodca.getName();
		case 1: return rozhodca.getStreet();
		case 2: return rozhodca.getCity();
		case 3: if (rozhodca.getTimy()== null)
					return null;
				else 
					return rozhodca.getTimy().getName();
			
		}
		fireTableRowsUpdated(riadok, stlpec);
		return null;
	}

}
