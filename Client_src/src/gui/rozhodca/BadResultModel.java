package gui.rozhodca;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

public class BadResultModel extends AbstractTableModel {
	
	private List<String> testy = new ArrayList<String>();
	  
	  ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	  private String[] columns = { 
				rb.getString("gui.test.referee") };
	  
	  public BadResultModel() {
		  
	  }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return testy.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	
	
	 public String getColumnName(int column) {
			// TODO Auto-generated method stub
		  //System.out.println(column);
			return columns[column];
		}
	 public void setData(List<String> databaza) {
			this.testy = databaza;
		}

	

	  // The object to render in a cell
	  public Object getValueAt(int row, int col) {
	    String club = testy.get(row);
	    switch(col) {
	      case 0: return club;
	      
	      default: return null;
	    }
	  }

}
