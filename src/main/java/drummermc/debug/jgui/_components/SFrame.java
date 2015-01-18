package drummermc.debug.jgui._components;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import drummermc.debug.jgui._layout.IPerLayoutHandler;
import drummermc.debug.jgui._layout.PerLayout;
import drummermc.debug.jgui._layout.PerLayoutConstraints;

public abstract class SFrame extends JFrame implements IPerLayoutHandler
{	
    private final PerLayoutConstraints plc;

    public SFrame(Dimension size, int defaultCloseOperation)
    {
        super();
        this.setDefaultCloseOperation(defaultCloseOperation); 
        this.setLayout(new PerLayout());
        this.plc = new PerLayoutConstraints();
        this.setMinimumSize(size);
        this.setPreferredSize(size); 
        this.setLocationRelativeTo(null);
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
