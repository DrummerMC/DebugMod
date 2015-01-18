package drummermc.debug.jgui._layout;

import java.awt.Component;
import java.awt.Insets;

public interface IPerLayoutHandler 
{
    public void add(Component cmp, double x, double width, boolean nextLine);
    public void add(Component cmp, double x, double width, boolean nextLine, Insets insets);
    
    public void add(Component cmp, double x, double width, int lineCount, boolean nextLine);
    public void add(Component cmp, double x, double width, int lineCount, boolean nextLine, Insets insets);
    
    public void add(Component cmp, double x, double width, double y, double height);
    
    public void setAnchor(int anchor);
}
