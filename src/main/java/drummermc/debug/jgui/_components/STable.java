package drummermc.debug.jgui._components;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class STable extends JTable
{
    public STable()
    {
        super();
        this.init();
    }
     
    public STable(Object[][] data, Object[] header)
    {
        super(new DefaultTableModel(data, header));
        this.init();
    }
    
    private void init()
    {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        this.setAutoCreateRowSorter(true); 
        this.getTableHeader().setReorderingAllowed(false);
        this.putClientProperty("terminateEditOnFocusLost", true);
    }
        
    
    public void setCellComponent(int column, JComboBox cmp)
    {
        TableColumn col = this.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(cmp));
    }
    
    public void setCellComponent(int column, JTextField cmp)
    {
        TableColumn col = this.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(cmp));
    }
    
    public void setCellComponent(int column, JCheckBox cmp)
    {
        TableColumn col = this.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(cmp));
    }
    
    @Override
    public void setEnabled(boolean enabled)
    {
        for(Component c : this.getComponents())
        {
            c.setEnabled(enabled); 
        }
    }
    
    public Object getSelectedValueAt(int column)
    {
        int row = this.getSelectedRow();
        if (row != -1)
        {
            return this.getValueAt(row, column).toString();
        }
        return null;
    }
}