package drummermc.debug.jgui._layout;

import java.awt.Component;

public class PerLayoutComponent 
{
    private final Component component;
    private final PerLayoutConstraints constraints;
    
    public PerLayoutComponent(Component component, PerLayoutConstraints constraints)
    {
        this.component = component;
        this.constraints = constraints.copy();
    }

    public Component getComponent() 
    {
        return component;
    }

    public PerLayoutConstraints getConstraints() 
    {
        return constraints;
    }
}
