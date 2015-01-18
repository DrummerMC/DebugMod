package drummermc.debug.jgui._components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import drummermc.debug.jgui._layout.IPerLayoutHandler;
import drummermc.debug.jgui._layout.PerLayout;
import drummermc.debug.jgui._layout.PerLayoutConstraints;

public class SPanel extends JPanel implements IPerLayoutHandler
{	
    private PerLayoutConstraints plc;
    
    public SPanel(LayoutManager layout)
    {
        super();
        this.setLayout(layout);
    }
    
    public SPanel()
    {
        super();
        this.plc = new PerLayoutConstraints();
        this.setLayout(new PerLayout());
    }
    
    @Override
    public void add(Component cmp, double x, double width, boolean nextLine) 
    {
        this.plc.setConstraints(x, width, nextLine);
        this.add(cmp, this.plc);
    }

    @Override
    public void add(Component cmp, double x, double width, boolean nextLine, Insets insets) 
    {
        this.plc.setConstraints(x, width, nextLine, insets);
        this.add(cmp, this.plc);
    }

    @Override
    public void add(Component cmp, double x, double width, int lineCount, boolean nextLine) 
    {
        this.plc.setConstraints(x, width, lineCount, nextLine);
        this.add(cmp, this.plc);
    }

    @Override
    public void add(Component cmp, double x, double width, int lineCount, boolean nextLine, Insets insets) 
    {
        this.plc.setConstraints(x, width, lineCount, nextLine, insets);
        this.add(cmp, this.plc);
    }

    @Override
    public void add(Component cmp, double x, double width, double y, double height) 
    {
        this.plc.setConstraints(x, width, y, height);
        this.add(cmp, this.plc);
    }

    @Override
    public void setAnchor(int anchor) 
    {
        this.plc.setAnchor(anchor);
    }
}
