package drummermc.debug.jgui.debug;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import drummermc.debug.jgui._components.SFrame;
import drummermc.debug.jgui._components.SPanel;
import drummermc.debug.jgui._components.STable;
import drummermc.debug.jgui._layout.PerLayoutConstraints;

public class FrameDebug extends SFrame
{
	private static FrameDebug instance;
	public FrameDebug(STable table)
	{
		super(new Dimension(1024, 768), SFrame.DISPOSE_ON_CLOSE);
		JScrollPane scp = new JScrollPane();
		scp.setViewportView(table);
		this.add(scp, 0, 100, PerLayoutConstraints.LINECOUNT_FILL, true);
		this.setVisible(true);
	}
}
