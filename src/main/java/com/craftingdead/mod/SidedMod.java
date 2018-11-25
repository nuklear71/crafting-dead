package com.craftingdead.mod;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.function.Supplier;

import com.craftingdead.mod.client.ClientMod;
import com.craftingdead.mod.server.LogicalServer;
import com.craftingdead.mod.server.dedicated.ServerMod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import sm0keysa1m0n.network.wrapper.Session;

/**
 * Implemented by each side of the game, one for the client and one for the
 * server
 * 
 * @author Sm0keySa1m0n
 *
 */
public interface SidedMod {

	/**
	 * Called before {@link FMLInitializationEvent} during mod startup. This is the
	 * first of three commonly called events during mod initialization.
	 */
	default void preInitialization(FMLPreInitializationEvent event) {
		;
	}

	/**
	 * Called after {@link FMLPreInitializationEvent} and before
	 * {@link FMLPostInitializationEvent} during mod startup. This is the second of
	 * three commonly called events during mod initialization.
	 */
	default void initialization(FMLInitializationEvent event) {
		;
	}

	/**
	 * Called after {@link FMLInitializationEvent} has been dispatched on every mod.
	 * This is the third and last commonly called event during mod initialization.
	 */
	default void postInitialization(FMLPostInitializationEvent event) {
		;
	}

	/**
	 * Indicates that loading is complete.
	 */
	default void loadComplete(FMLLoadCompleteEvent event) {
		;
	}

	/**
	 * Called when a remote network connection is offered.
	 * 
	 * @param mods - all mods and their versions on the remote host
	 * @param side - the side of the remote host
	 * @return if the remote host is acceptable
	 */
	default boolean networkCheck(Map<String, String> mods, Side side) {
		return true;
	}

	/**
	 * Signals the shutdown of the game, do any shutdown tasks here.<br>
	 * <b>Runs in a separate shutdown thread.<b>
	 */
	default void shutdown() {
		;
	}

	/**
	 * Get a {@link Supplier} for the {@link LogicalServer} associated with this
	 * side.
	 * 
	 * @return the {@link Supplier}
	 */
	Supplier<? extends LogicalServer> getLogicalServerSupplier();

	/**
	 * Get the {@link InetSocketAddress} used to connect to the master server.
	 * 
	 * @return the {@link InetSocketAddress}
	 */
	InetSocketAddress getMasterServerAddress();

	/**
	 * If supported should the master server connection use Epoll.
	 * 
	 * @return the result
	 */
	boolean useEpoll();

	/**
	 * Create a new {@link S}.
	 * 
	 * @return the {@link S}
	 */
	Session newSession();

	/**
	 * Gets the folder associated with the current side.
	 * 
	 * @return the {@link File} instance
	 */
	default File getSidedFolder() {
		return new File(CraftingDead.instance().getModFolder(), FMLLaunchHandler.side().name());
	}

	/**
	 * Casts the {@link SidedMod} instance to a {@link ClientMod} instance.<br>
	 * <b>Throws an exception if called from the incorrect side.
	 * 
	 * @return the instance
	 */
	default ClientMod getClientMod() {
		if (this instanceof ClientMod)
			return (ClientMod) this;
		else
			throw new RuntimeException("Accessing physical client on wrong side");
	}

	/**
	 * Casts the {@link SidedMod} instance to a {@link ServerMod} instance.<br>
	 * <b>Throws an exception if called from the incorrect side.
	 * 
	 * @return the instance
	 */
	default ServerMod getServerMod() {
		if (this instanceof ServerMod)
			return (ServerMod) this;
		else
			throw new RuntimeException("Accessing physical server on wrong side");
	}

}
