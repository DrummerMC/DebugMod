package drummermc.debug.jgui.debug;

import java.awt.Dimension;

import drummermc.debug.jgui._components.SFrame;
import drummermc.debug.jgui._components.SPanel;
import drummermc.debug.jgui._components.STable;
import drummermc.debug.jgui._layout.PerLayoutConstraints;

public class FrameDebug extends SFrame
{
	public FrameDebug(STable table)
	{
		super(new Dimension(1024, 768), SFrame.DISPOSE_ON_CLOSE);
		this.add(table, 0, 100, PerLayoutConstraints.LINECOUNT_FILL, true);
		this.setVisible(true);
	}
}
