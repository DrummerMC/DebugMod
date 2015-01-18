package drummermc.debug.jgui._layout;

import java.awt.Insets;

public class PerLayoutConstraints
{
    private double x;
    private double y;
    
    private double width;
    private double height;
    
    private int lineCount;
    public static final int LINECOUNT_FILL = -1;
    
    private boolean nextLine;
    
    private Insets insets;
    
    private int anchor = ANCHOR_TOP;
    public static final int ANCHOR_TOP = 1;
    public static final int ANCHOR_BOTTOM = 2;
    
    public static final int NULLVALUE = -100;

    public void setConstraints(double x, double width, boolean nextLine)
    {
        this.setConstraints(x, width, NULLVALUE, NULLVALUE, 1, nextLine,new Insets(5, 5, 5, 5));
    }
    public void setConstraints(double x, double width, boolean nextLine, Insets insets)
    {
        this.setConstraints(x, width, NULLVALUE, NULLVALUE, 1, nextLine, insets);
    }
    
    public void setConstraints(double x, double width, int lineCount, boolean nextLine)
    {
        this.setConstraints(x, width, NULLVALUE, NULLVALUE, lineCount, nextLine,new Insets(5, 5, 5, 5));
    }
    public void setConstraints(double x, double width, int lineCount, boolean nextLine, Insets insets)
    {
        this.setConstraints(x, width, NULLVALUE, NULLVALUE, lineCount, nextLine, insets);
    }

    public void setConstraints(double x, double width, double y, double height)
    {
        this.setConstraints(x, width, y, height, NULLVALUE, false, new Insets(5, 5, 5, 5));
    }
    
    private void setConstraints(double x, double width, double y, double height, int lineCount, boolean nextLine, Insets insets)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lineCount = lineCount;
        this.nextLine = nextLine;
        this.insets = insets;
    }
    
    public PerLayoutConstraints copy()
    {
        PerLayoutConstraints copy = new PerLayoutConstraints();
        copy.x = this.x;
        copy.y = this.y;
        copy.width = this.width;
        copy.height = this.height;
        copy.lineCount = this.lineCount;
        copy.nextLine = this.nextLine;
        copy.anchor = this.anchor;
        copy.insets = this.insets;
        return copy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getLineCount() {
        return lineCount;
    }
    
    public boolean placeNextLine() {
        return this.nextLine;
    }
    
    public int getAnchor() {
        return this.anchor;
    }
    
    public Insets getInsets()
    {
        return this.insets;
    }
    
    public void setAnchor(int anchor)
    {
        this.anchor = anchor;
    }
    
}
