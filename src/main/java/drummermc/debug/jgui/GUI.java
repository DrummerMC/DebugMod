package drummermc.debug.jgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.UIManager;

public class GUI 
{
    public static Font FONT_TITLE_EXTREME = new Font("Arial", Font.BOLD, 40);
    public static Font FONT_TITLE_BIG = new Font("Arial", Font.BOLD, 26);
    public static Font FONT_TITLE_MEDIUM = new Font("Arial", Font.BOLD, 20);
    public static Font FONT_TITLE_SMALL = new Font("Arial", Font.BOLD, 14);
    
    public static Font FONT_LABEL = new Font("Arial", Font.PLAIN, 12);
    public static Font FONT_BUTTON = new Font("Arial", Font.BOLD, 12);
    public static Font FONT_INPUT = new Font("Arial", Font.BOLD, 12);
    public static Font FONT_MENU = new Font("Arial", Font.PLAIN, 14);

    public static Color COLOR_ELEMENT_BORDER = new Color(170, 170, 170);
    
    public static Color COLOR_ELEMENT_BACKGROUND_ENABLED = new Color(255,255,255);
    public static Color COLOR_ELEMENT_BACKGROUND_DISABLED = new Color(230, 230, 230);
    public static Color COLOR_ELEMENT_BACKGROUND_MANDATORY = new Color(255, 254, 168);
    
    
    public static Color COLOR_ELEMENT_FOREGROUND_DISABLED = new Color(0, 0, 0);
    public static Color COLOR_ELEMENT_FOREGROUND_ENABLED = new Color(0, 0, 0);
    
    public static Color COLOR_CONTAINER_BORDER = new Color(210, 210, 210);
    
    public static final Color COLOR_VERWIEGUNG_PANEL_BACKGROUND = new Color(222, 222, 222);
    public static final Color COLOR_PANEL_BACKGROUND_DEFAULT = Color.WHITE;
    
    public static final Color COLOR_PANEL_BACKGROUND_MASTERDATA = Color.WHITE;
    
    
    public static final Color COLOR_IRIS_BLUE = Color.BLUE;
    public static final Color COLOR_IRIS_PINK = new Color(240, 30, 200);
    
    public static final Color COLOR_STANDSTILL_TRUE = Color.GREEN;
    public static final Color COLOR_STANDSTILL_FALSE = Color.RED;
    
    public static final Color COLOR_BUTTON_FOREGROUND_SAVE = new Color(1, 120, 14);
    public static final Color COLOR_BUTTON_FOREGROUND_CANCEL = new Color(120, 0, 14);
    
    public static final int FONT_SIZE_HTML_TOOLTIP = 5;
    
  
    public static final Font FONT_HELP = new Font("Arial", Font.ITALIC, 10);
    

    public static final Font FONT_MENUE_PLANT = new Font("Arial", Font.BOLD, 14);
    
    public static final Dimension DIMENSION_FRAME_MIN = new Dimension(1024, 768);
    
}
