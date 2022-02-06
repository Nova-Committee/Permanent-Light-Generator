package committee.nova.plg.init.packs;

import net.minecraft.resources.IPackFinder;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.lifecycle.IModBusEvent;

import java.util.function.Consumer;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/2/6 9:49
 * Version: 1.0
 */
public class AddPackFindersEvent extends Event implements IModBusEvent {
    //private final ResourcePackType packType;
    private final Consumer<IPackFinder> sources;

    public AddPackFindersEvent( Consumer<IPackFinder> sources)
    {
        //this.packType = packType;
        this.sources = sources;
    }

    /**
     * Adds a new source to the list of pack finders.
     * @param source the pack finder
     */
    public void addRepositorySource(IPackFinder source)
    {
        sources.accept(source);
    }

    /**
     * @return the {@link net.minecraft.resources.ResourcePackType} of the resource manager being constructed.
     */
//    public ResourcePackType getPackType()
//    {
//        return packType;
//    }
}
