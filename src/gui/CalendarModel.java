package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class CalendarModel extends DefaultTableModel {
	
	String [][] calendar = new String[25][8];
	String [] days = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
	
	public CalendarModel(){
		for (int i = 0; i<days.length;++i){
			calendar[0][i+1] = days[i];
		}
		for (int j=0; j<17; ++j){
			calendar[j+1][0] = Integer.toString((7+j))+":00-"+ Integer.toString(8+j) +":00";
		}
		for (int k = 0; k<7; ++k ){
			calendar[18+k][0] = Integer.toString(k)+":00-"+ Integer.toString(k+1)+":00";
		}
	}
	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return 25;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return calendar[row][column];
	}

}
