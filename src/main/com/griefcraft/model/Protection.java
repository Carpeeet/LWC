/**
 * This file is part of LWC (https://github.com/Hidendra/LWC)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.griefcraft.model;

import com.griefcraft.lwc.LWC;
import com.griefcraft.util.Colors;

public class Protection {

	// just a footnote, if a flag is "on", it is SET in the database, however if it's set to "off"
	// it is REMOVED from the database if it is in it!
	public enum Flag {
		REDSTONE(0x01, "If set to \"on\", redstone will be able to interact with the protection (ex. open a door)"); // if set, redstone use is DISABLED!
		
		Flag(int bit, String description) {
			this.bit = bit;
			this.description = description;
		}
		
		private int bit;
		private String description;
		
		public int getBit() {
			return bit;
		}
		
		public String getDescription() {
			return description;
		}
	};
	
	/**
	 * The block id
	 */
	private int blockId;

	/**
	 * The password for the chest
	 */
	private String data;

	/**
	 * The date created
	 */
	private String date;

	/**
	 * Unique id (in sql)
	 */
	private int id;
	
	/**
	 * Bit-packed flags
	 */
	private int flags;

	/**
	 * The owner of the chest
	 */
	private String owner;

	/**
	 * The chest type
	 */
	private int type;

	/**
	 * The world this protection is in
	 */
	private String world;

	/**
	 * The x coordinate
	 */
	private int x;

	/**
	 * The y coordinate
	 */
	private int y;

	/**
	 * The z coordinate
	 */
	private int z;
	
	/**
	 * Check if a flag is toggled
	 * 
	 * @param flag
	 * @return
	 */
	public boolean hasFlag(Flag flag) {
		return (flags & flag.getBit()) == flag.getBit();
	}
	
	/**
	 * Add a flag to the protection
	 * 
	 * @param flag
	 * @return
	 */
	public boolean addFlag(Flag flag) {
		if(!hasFlag(flag)) {
			flags |= flag.getBit();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove a flag from the protection
	 * TODO: redo? :s
	 * 
	 * @param flag
	 * @return
	 */
	public void removeFlag(Flag flag) {
		if(!hasFlag(flag)) {
			return;
		}
		
		flags = 0;
		
		for(Flag tmp : Flag.values()) {
			if(flag != tmp) {
				addFlag(tmp);
			}
		}
	}
	
	public int getFlags() {
		return flags;
	}

	public int getBlockId() {
		return blockId;
	}

	public String getData() {
		return data;
	}

	public String getDate() {
		return date;
	}

	public int getId() {
		return id;
	}
	
	

	public String getOwner() {
		return owner;
	}

	public int getType() {
		return type;
	}

	public String getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return id:owner->[x,y,z]
	 */
	@Override
	public String toString() {
		return String.format("%s %s" + Colors.White + " {" + Colors.Green + "Id=%d Owner=%s Location=[@%s %d,%d,%d] Created=%s Flags=%d" + Colors.White + "}", typeToString(), (blockId > 0 ? (LWC.materialToString(blockId)) : "Not yet cached"), id, owner, world, x, y, z, date, flags);
	}

	/**
	 * @return string representation of the protection type
	 */
	public String typeToString() {
		switch (type) {
		case ProtectionTypes.PRIVATE:
			return "Private";

		case ProtectionTypes.PUBLIC:
			return "Public";

		case ProtectionTypes.PASSWORD:
			return "Password";

		case ProtectionTypes.TRAP_KICK:
			return "Kick trap";

		case ProtectionTypes.TRAP_BAN:
			return "Ban trap";
		}

		return "Unknown(raw:" + type + ")";
	}

}
