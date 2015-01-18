package drummermc.debug.jgui._layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import drummermc.debug.jgui._components.SPanel;
import drummermc.debug.jgui._components.SSeperator;

public class PerLayout implements LayoutManager2
{
    private final ArrayList<PerLayoutComponent> components;

    public PerLayout()
    {
        this.components = new ArrayList();
    }
    
    @Override
    public void layoutContainer(Container cntnr) 
    {
        int maxWidth = cntnr.getWidth();
        int maxHeight = cntnr.getHeight();

        double yTop = 0;
        double yBot = maxHeight;
        
        double heightLastComponentTop = 0;
        double heightLastComponentBot = 0;
        
        int lineCountLast=0;
        
        for(PerLayoutComponent ilc : this.components)
        {
            Component cmp = ilc.getComponent();
            PerLayoutConstraints constr = ilc.getConstraints();
            
            double cX = constr.getX();
            double cY = constr.getY();
            double cWidth = constr.getWidth();
            double cHeight = constr.getHeight();
            int lineCount = constr.getLineCount();
            boolean nextLine = constr.placeNextLine();
            int anchor = constr.getAnchor();
            Insets insets = constr.getInsets();
            
            double y2 = 0;
            double x = 0;
            double width = 0;
            double height = 0;
            
            int nr = 1;
            
            if (cY == PerLayoutConstraints.NULLVALUE && cHeight == PerLayoutConstraints.NULLVALUE)
            {
                nr = 1;
                // Positionierung: Oben nach unten (mit Insets)
                x = (maxWidth / 100d) * cX + insets.left;
                width = (maxWidth / 100d) * cWidth - insets.left - insets.right;
               
                if (lineCount == PerLayoutConstraints.LINECOUNT_FILL)
                {
                    if (lineCountLast == PerLayoutConstraints.LINECOUNT_FILL)
                    {
                        height = heightLastComponentTop;
                    }
                    else
                    {
                        height = yBot - yTop - heightLastComponentTop - insets.top - insets.bottom;
                    }
                    
                }
                else
                {
                    
                    if (cmp instanceof JButton)
                    {
                    	JButton bt = (JButton) cmp;
                        if (bt.getIcon() == null)
                        {
                            height = lineCount * cmp.getFont().getSize() + (cmp.getFont().getSize() / 2) + 4;
                        }
                        else
                        {
                            height = lineCount * bt.getIcon().getIconHeight();
                        }
                    }
                    else if (cmp instanceof SPanel)
                    {
                        height = lineCount * cmp.getPreferredSize().height + 8;
                    }
                    else if (cmp instanceof JPanel)
                    {
                        height = lineCount * cmp.getPreferredSize().height;
                    }
                    else if (cmp instanceof JLayeredPane)
                    {
                        height = lineCount * cmp.getPreferredSize().height + 8;
                    }
                    else if (cmp instanceof SSeperator)
                    {
                        height = lineCount * cmp.getFont().getSize();
                    }
                    else
                    {
                        height = lineCount * cmp.getFont().getSize() + 8;
                    }
                   
                }
                
                
                if (anchor == PerLayoutConstraints.ANCHOR_TOP)
                {
                    if (nextLine)
                    {
                        yTop+=heightLastComponentTop + insets.top;
                        heightLastComponentTop = height;
                    }
                    else
                    {
                        if (height > heightLastComponentTop)
                        {
                            heightLastComponentTop = height;
                        }
                    }
                }
                else
                {
                    // Anchor Bottom:
                    if (yBot == maxHeight)
                    {
                        yBot-=height;
                        yBot-= insets.bottom;
                        heightLastComponentBot = height;
                    }
                    else
                    {
                        if (nextLine)
                        {
                            yBot-=heightLastComponentBot;
                            yBot-= insets.bottom;
                            heightLastComponentBot = height;
                        }
                        else
                        {
                            if (height > heightLastComponentBot)
                            {
                                heightLastComponentBot = height;
                            }
                        }
                    }
                    
                }
                
                
            }
            else
            {
                nr = 2;
                if (cHeight == PerLayoutConstraints.NULLVALUE)
                {
                    // Positionierung: Frei (ohne Insets)
                    y2 = (maxHeight / 100d) * cY;
                    x = (maxWidth / 100d) * cX;
                    width = (maxWidth / 100d) * cWidth;
                    height = lineCount * cmp.getFont().getSize() + 4;
                }
                else
                {
                    // Panel-Positionierung
                    y2 = (maxHeight / 100d) * cY;
                    x = (maxWidth / 100d) * cX;
                    width = (maxWidth / 100d) * cWidth;
                    height = (maxHeight / 100d) * cHeight;
                }
            }
            
            double y;
            if (nr == 1)
            {
                if (anchor == PerLayoutConstraints.ANCHOR_TOP)
                {
                    y = yTop;
                }
                else
                {
                    y = yBot;
                }
                
            }
            else
            {
                y = y2;
            }
           
            cmp.setLocation((int)x, (int)y);
            cmp.setSize((int)width, (int)height);
            
            lineCountLast = lineCount;
            
        }
        
    }
    
    @Override
    public void addLayoutComponent(Component cmpnt, Object o) 
    {
        this.components.add(new PerLayoutComponent(cmpnt, (PerLayoutConstraints)o));
    }

    @Override
    public void removeLayoutComponent(Component cmpnt) 
    {
        for(PerLayoutComponent ilc : this.components)
        {
            if (ilc.getComponent() == cmpnt)
            {
                this.components.remove(ilc);
                return;
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container cntnr) 
    {
        int width = 0, height = 0;
        this.layoutContainer(cntnr);
        for(Component cmp : cntnr.getComponents())
        {
            
            int cXEnd, cYEnd;
            
            if (cmp instanceof SPanel)
            {
            	SPanel pn = (SPanel) cmp;
                Dimension dim = pn.getPreferredSize();
                cXEnd = cmp.getX() + cmp.getWidth();
                cYEnd = cmp.getY() + cmp.getHeight();
            }
            else
            {
                cXEnd = cmp.getX() + cmp.getWidth();
                cYEnd = cmp.getY() + cmp.getHeight();
            }
            
            if (cXEnd > width)
            {
                width = cXEnd;
            }
            
            if (cYEnd > height)
            {
                height = cYEnd;
            }
        }
        return new Dimension(width, (int) (height - (5 / 1.5)));
    }

    @Override public void invalidateLayout(Container cntnr){}
    
    @Override public void addLayoutComponent(String string, Component cmpnt) {}

    @Override
    public Dimension maximumLayoutSize(Container cntnr) 
    {
        return(new Dimension(0, 0));
    }
    
    @Override
    public Dimension minimumLayoutSize(Container cntnr) 
    {
        return(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
    
    @Override
    public float getLayoutAlignmentX(Container cntnr) 
    {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container cntnr) 
    {
        return 0;
    }
}
