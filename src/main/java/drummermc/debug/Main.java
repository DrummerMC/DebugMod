package drummermc.debug;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new CmdCheckTileEntityUpdateTimeConsumption());
      event.registerServerCommand(new CmdCheckVariableTileEntity());
    }
}
