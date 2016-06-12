package gui.tim;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import entity.Timy;

public class TimTableModel extends AbstractTableModel {
	
	private List<Timy> databaza;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private String[] colNames = {rb.getString("gui.tim.tabMeno"), 
			rb.getString("gui.tim.tabHomeCol") , rb.getString("gui.tim.tabAwayCol")};
	
	
	
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
	
	public TimTableModel() {
		
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
		Timy rozhodca = databaza.get(riadok);
		
		switch(stlpec) {
		case 0: return rozhodca.getName();
		case 1: return rozhodca.getHomecol();
		case 2: return rozhodca.getAwaycol();
		
		}
		fireTableRowsUpdated(riadok, stlpec);
		return null;
	}

	public void setData(List<Timy> databaza) {
		this.databaza = databaza;
	}

}