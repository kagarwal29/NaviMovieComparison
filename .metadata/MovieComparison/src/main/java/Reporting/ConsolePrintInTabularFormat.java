package Reporting;

import dnl.utils.text.table.TextTable;

public class ConsolePrintInTabularFormat {

	public void convertResultInTabularFormat(String[] string, Object[][] objects) {
				
				TextTable tt = new TextTable(string, objects);
				tt.setAddRowNumbering(true);
				tt.setSort(0);
				tt.printTable();
	}
}
