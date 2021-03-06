/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.gmail.filoghost.holographicdisplays.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

/**
 * An object made of various lines, that can be items or holograms.
 * Holographic lines appear as a nametag without any entity below.
 * To create one, please see {@link HologramsAPI#createHologram(org.bukkit.plugin.Plugin, Location)}.
 */
public interface Hologram {
	
	/**
	 * Appends a text line to end of this hologram.
	 * 
	 * @param text the content of the line, can be null for an empty line
	 * @return the new TextLine appended
	 */
	public TextLine appendTextLine(String text);
	
	
	/**
	 * Appends an item line to end of this hologram.
	 * 
	 * @param itemStack the content of the line
	 * @return the new ItemLine appended
	 */
	public ItemLine appendItemLine(ItemStack itemStack);
	
	
	/**
	 * Inserts a text line in this hologram.
	 * 
	 * @param index the line is inserted before this index. If 0, the new line will
	 * be inserted before the first line.
	 * @param text the content of the line, can be null for an empty line
	 * @return the new TextLine inserted
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	public TextLine insertTextLine(int index, String text);
	
	
	/**
	 * Inserts an item line in this hologram.
	 * 
	 * @param index the line is inserted before this index. If 0, the new line will
	 * be inserted before the first line.
	 * @param itemStack the content of the line
	 * @return the new ItemLine inserted
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	public ItemLine insertItemLine(int index, ItemStack itemStack);
	
	
	/**
	 * Finds the element at a given index in the lines.
	 * 
	 * @param index the index of the line to retrieve.
	 * @return the hologram line at the given index, can be an {@link ItemLine} or a {@link TextLine}.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	public HologramLine getLine(int index);
	
	/**
	 * Removes a line at a given index. Since: v2.0.1
	 * 
	 * @param index the index of the line, that should be between 0 and size() - 1.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	public void removeLine(int index);
	
	
	/**
	 * Removes all the lines from this hologram.
	 */
	public void clearLines();

	
	/**
	 * Checks the amount of lines of the hologram.
	 * 
	 * @return the amount of lines
	 */
	public int size();
	
	
	/**
	 * The physical height of the hologram, counting all the lines. Since: v2.1.4
	 * 
	 * @return the height of the hologram, counting all the lines and the gaps between them
	 */
	public double getHeight();
	
	
	/**
	 * Teleports a hologram to the given location.
	 * 
	 * @param location the new location
	 */
	public void teleport(Location location);
	
	
	/**
	 * Teleports a hologram to the given location.
	 * 
	 * @param world the world where the hologram should be teleported,
	 * use {@link #getWorld()} to teleport it in the same world.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 */
	public void teleport(World world, double x, double y, double z);
	
	/**
	 * Returns the position of the hologram.
	 * 
	 * @return the Location of the hologram
	 */
	public Location getLocation();
	
	/**
	 * Returns the X coordinate.
	 * 
	 * @return the X coordinate of the hologram
	 */
	public double getX();
	
	
	/**
	 * Returns the Y coordinate.
	 * 
	 * @return the Y coordinate of the hologram
	 */
	public double getY();
	
	
	/**
	 * Returns the Z coordinate.
	 * 
	 * @return the Z coordinate of the hologram
	 */
	public double getZ();
	
	
	/**
	 * Returns the world.
	 * 
	 * @return the world of the hologram
	 */
	public World getWorld();
	
	
	/**
	 * Returns the {@link VisibilityManager} of this hologram.
	 * <br><b style = "color: red">Note</b>: the usage of the VisibilityManager requires ProtocolLib.
	 * Without the plugin, holograms will be always visible.
	 * 
	 * @return the VisibilityManager of this hologram
	 */
	public VisibilityManager getVisibilityManager();
	
	
	/**
	 * Returns when the hologram was created. Useful for removing old holograms.
	 * 
	 * @return the timestamp of when the hologram was created, in milliseconds
	 */
	public long getCreationTimestamp();

	/**
	 * Checks if the hologram will track and replace placeholders.
	 * This is false by default.
	 * 
	 * @return if the hologram allows placeholders
	 */
	public boolean isAllowPlaceholders();

	/**
	 * Sets if the hologram should track and replace placeholders.
	 * By default if will not track them.
	 * 
	 * @param allowPlaceholders if the hologram should track placeholders
	 */
	public void setAllowPlaceholders(boolean allowPlaceholders);
	
	/**
	 * Deletes this hologram. Editing or teleporting the hologram when deleted
	 * will throw an exception. Lines will be automatically cleared.
	 * You should remove all the references of the hologram after deletion.
	 */
	public void delete();
	
	
	/**
	 * Checks if a hologram was deleted.
	 * 
	 * @return true if this hologram was deleted
	 */
	public boolean isDeleted();

	/**
	 * Returns the line spacing for this hologram. By default, this is imported from the plugin configuration.
	 * Default is 0.02, a good one for a bit more spacing can be 0.1.
	 *
	 * @return the line spacing
	 */
	double getLineSpacing();

	/**
	 * Sets the line spacing for this hologram.
	 *
	 * @param spacing the new line spacing
	 */
	void setLineSpacing(double spacing);
}
