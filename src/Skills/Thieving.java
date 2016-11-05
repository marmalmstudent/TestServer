package Skills;
import Server.*;

public class Thieving
{
	private int[] STALL_TIMERS;
	
	private int[] lastStall;
	
	private boolean[] stallEmpty;

	/**
	 * 
	 * @param objectID id of the object (stall)
	 * @return the stall's timer id used in STALL_TIMERS depending
	 * only on object id
	 */
	public int getStallTimerID(int objectID)
	{
		switch(objectID)
		{
		case 4708: //vegetable stall
			return 0;
		case 2561: //baker's stall
			return 1;
		case 100001: //crafting stall
			return 2;
		case 100002: //monkey food stall
			return 3;
		case 100003: //monkey general stall
			return 4;
		case 100004: //tea stall
			return 5;
		case 2560: //silk stall
			return 6;
		case 100005: //wine stall
			return 7;
		case 100006: //seed stall
			return 8;
		case 100007: //fur stall
			return 9;
		case 100008: //fish stall
			return 10;
		case 100009: //crossbow stall
			return 11;
		case 2565: //silver stall
			return 12;
		case 100010: //spice stall
			return 13;
		case 100011: //magic stall
			return 14;
		case 100012: //scimitar stall
			return 15;
		case 2562: //gem stall
			return 16;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param objectID id of the object (stall)
	 * @param objectX x-coord of the object (stall)
	 * @param objectY y-coord of the object (stall)
	 * @return
	 */
	public int getStallID(int objectID, int objectX, int objectY)
	{
		switch(objectID)
		{
		case 4708: //vegetable stall
			return 3*getStallTimerID(objectID);
		case 2561: //baker's stall
			if(objectX == 2655 && objectY == 3311)
				return 3*getStallTimerID(objectID);
			if(objectX == 2667 && objectY == 3310)
				return 3*getStallTimerID(objectID) + 1;
		case 100001: //crafting stall
			return 3*getStallTimerID(objectID);
		case 100002: //monkey food stall
			return 3*getStallTimerID(objectID);
		case 100003: //monkey general stall
			return 3*getStallTimerID(objectID);
		case 100004: //tea stall
			return 3*getStallTimerID(objectID);
		case 2560: //silk stall
			if(objectX == 2662 && objectY == 3314)
				return 3*getStallTimerID(objectID);
			if(objectX == 2656 && objectY == 3302)
				return 3*getStallTimerID(objectID) + 1;
		case 100005: //wine stall
			return 3*getStallTimerID(objectID);
		case 100006: //seed stall
			return 3*getStallTimerID(objectID);
		case 2563: //fur stall
			if(objectX == 2663 && objectY == 3296)
				return 3*getStallTimerID(objectID);
		case 100008: //fish stall
			return 3*getStallTimerID(objectID);
		case 100009: //crossbow stall
			return 3*getStallTimerID(objectID);
		case 2565: //silver stall
			if(objectX == 2657 && objectY == 3314)
			return 3*getStallTimerID(objectID);
		case 2564: //spice stall
			if(objectX == 2658 && objectY == 3297)
				return 3*getStallTimerID(objectID);
		case 100011: //magic stall
			return 3*getStallTimerID(objectID);
		case 100012: //scimitar stall
			return 3*getStallTimerID(objectID);
		case 2562: //gem stall
			if(objectX == 2667 && objectY == 3303)
				return 3*getStallTimerID(objectID);
		}
		return 0;
	}
	public Thieving()
	{
		STALL_TIMERS = new int[17]; // 17 different types of stalls
		STALL_TIMERS[0] = (int) (0.5 + 2000/server.cycleTime); //vegetable
		STALL_TIMERS[1] = (int) (0.5 + 2000/server.cycleTime); //bakers
		STALL_TIMERS[2] = (int) (0.5 + 7000/server.cycleTime); //crafting
		STALL_TIMERS[3] = (int) (0.5 + 7000/server.cycleTime); //monkey
		STALL_TIMERS[4] = (int) (0.5 + 7000/server.cycleTime); //monkeyGenerall
		STALL_TIMERS[5] = (int) (0.5 + 7000/server.cycleTime); //tea
		STALL_TIMERS[6] = (int) (0.5 + 5000/server.cycleTime); //silk
		STALL_TIMERS[7] = (int) (0.5 + 16000/server.cycleTime); //wine
		STALL_TIMERS[8] = (int) (0.5 + 18000/server.cycleTime); //seed
		STALL_TIMERS[9] = (int) (0.5 + 10000/server.cycleTime); //fur
		STALL_TIMERS[10] = (int) (0.5 + 25000/server.cycleTime); //fish
		STALL_TIMERS[11] = (int) (0.5 + 9000/server.cycleTime); //crossbow
		STALL_TIMERS[12] = (int) (0.5 + 30000/server.cycleTime); //silver
		STALL_TIMERS[13] = (int) (0.5 + 80000/server.cycleTime); //spice
		STALL_TIMERS[14] = (int) (0.5 + 80000/server.cycleTime); //magic
		STALL_TIMERS[15] = (int) (0.5 + 80000/server.cycleTime); //scimitar
		STALL_TIMERS[16] = (int) (0.5 + 180000/server.cycleTime); //gem
		lastStall = new int[3*STALL_TIMERS.length];
		stallEmpty = new boolean[3*STALL_TIMERS.length];
	}
	public boolean process()
	{
		/*
		 * loops through all stalls in the game, decreasing timers
		 * if needed. If timer has reached zero, the stall is ready
		 * again.
		 */
		for(int i = 0; i < lastStall.length; i++)
		{
			if(lastStall[i] > 0)
				lastStall[i]--;
			else
				stallEmpty[i] = false;
		}
		return false;
	}
	
	public boolean FailPickpocket(int NPCID, client clt)
	{
		switch(NPCID)
		{
		case 1: //man, woman
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 16:
		case 24:
		case 25:
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 4000);
		case 7:
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 4500);
		case 1715: //female ham
		case 1714: //male ham
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 4500);
		case 15: //warrior woman
		case 18: //al-kharid warrior
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 5000);
		case 187: //rogue
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 5100);
		case 2234: //master farmer
		case 2235:
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 6000);
		case 9: //guard
		case 10:
		case 32:
			return (misc.random(10000) + 40*clt.playerLevel[17] <= 6000);
		default:
			return false;
		}
	}
	
	public int getNPCStunAnim(int NPCID)
	{
		switch(NPCID)
		{
		case 7: //farmer
			return 412;
		case 15: //warrior woman
		case 18: //al-kharid warrior
		case 187: //rogue
		case 9: //guard
		case 10:
		case 32:
			return 451;
		default:
			return 422;
		}
	}
	
	public int getNPCStunDmg(int NPCID)
	{
		switch(NPCID)
		{
		case 1: //man, woman
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 16:
		case 24:
		case 25:
		case 7: //farmer
			return 1;
		case 1715: //female ham
		case 1714: //male ham
			return misc.random2(3);
		case 15: //warrior woman
		case 18: //al-kharid warrior
		case 187: //rogue
		case 9: //guard
		case 10:
		case 32:
			return 2;
		case 2234: //master farmer
		case 2235:
			return 3;
		default:
			return 1;
		}
	}
	
	public void WhenFailPickpocket(int NPCID, int NPCSlot, client clt)
	{
		//clt.talk2("What do you think you're doing?", "", NPCID);
		/* causes client to crash */
		server.npcHandler.npcs[NPCSlot].textUpdate = "What do you think you're doing?";
		server.npcHandler.npcs[NPCSlot].textUpdateRequired = true;
		/**/
		//server.npcHandler.npcs[NPCSlot].TurnNpcTo(clt.absX, clt.absY);
		server.npcHandler.npcs[NPCSlot].animNumber = getNPCStunAnim(NPCID); //npc stun animaiton
		server.npcHandler.npcs[NPCSlot].animUpdateRequired = true;
		server.npcHandler.npcs[NPCSlot].updateRequired = true;
		
		//clt.dealDamageToPlayer(getNPCStunDmg(NPCID)); //deal damage if fail
		clt.setAnimation(424); //unarmed block animation
		//clt.stillgfx(245, clt.absY, clt.absX, 100, 0); //birds above head
		clt.lastStun = System.currentTimeMillis();
		if (NPCID == 1714 || NPCID == 1715)
		{ //ham
			clt.stunDelay = 4000; //stun for 4000 ms, can't move
			clt.actionTimer = 4000; //stun for 4000 ms, can't act
		} else if (NPCID == 21 || NPCID == 2363 || NPCID == 2364
				|| NPCID == 2365 || NPCID == 2366 || NPCID == 2367)
		{ //hero & elf
			clt.stunDelay = 6000; //stun for 6000 ms, can't move
			clt.actionTimer = 6000; //stun for 6000 ms, can't act
		} else
		{
			clt.stunDelay = 5000; //stun for 5000 ms, can't move
			clt.actionTimer = 5000; //stun for 5000 ms, can't act
		}
		//clt.lastAction = System.currentTimeMillis();
	}
	public void WhenSuccessPickpocket(int NPCID, client clt)
	{
		clt.actionTimer = 2000;
		//clt.lastAction = System.currentTimeMillis();
		clt.addSkillXP(RobXP(NPCID), 17);
		int[] loot = RobLoot(NPCID);
		clt.addItem(loot[0], loot[1]);
		clt.setAnimation(881);
	}
	
	public int[] RobLoot(int NPCID)
	{
		int[] loot = new int[2]; //id, ammount
		int chance;
		switch(NPCID)
		{
		case 1: //man, woman
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 16:
		case 24:
		case 25:
			loot[0] = 995;
			loot[1] = 3;
			break;
		case 7: //farmer
			chance = misc.random(2);
			if(chance < 1)
			{
				loot[0] = 5318;
				loot[1] = misc.random2(3);
			} else
			{
				loot[0] = 995;
				loot[1] = 9;
			}
			break;
		case 1715: //female ham
			loot[0] = 995;
			loot[1] = 15;
			break;
		case 1714: //male ham
			loot[0] = 995;
			loot[1] = 16;
			break;
		case 15: //warrior woman
		case 18: //al-kharid warrior
			loot[0] = 995;
			loot[1] = 18;
			break;
		case 187: //rogue
			chance = misc.random(50);
			if(chance >= 0 && chance <= 30)
			{
				loot[0] = 995;
				loot[1] = 25;
			} else if(chance > 30 && chance <= 35)
			{
				loot[0] = 995;
				loot[1] = 40;
			} else if(chance > 35 && chance <= 42)
			{
				loot[0] = 1523;
				loot[1] = 1;
			} else if(chance > 42 && chance <= 44)
			{
				loot[0] = 1219;
				loot[1] = 1;
			} else if(chance > 44 && chance <= 46)
			{
				loot[0] = 1993;
				loot[1] = 1;
			} else if(chance > 46 && chance <= 50)
			{
				loot[0] = 556;
				loot[1] = 8;
			}
			break;
		case 2234: //master farmer
		case 2235:
			chance = misc.random(54);
			if(chance >= 0 && chance <= 10) //placeholder. different droprates
			{
				loot[0] = 5096 + misc.random(10);
				loot[1] = 1;
			} else if(chance > 10 && chance <= 54)
			{
				loot[0] = 5281 + misc.random(43);
				loot[1] = 1;
			}
			break;
		case 9: //guard
		case 10:
		case 32:
			loot[0] = 995;
			loot[1] = 30;
			break;
		}
		return loot;
	}
	public int RobXP(int NPCID)
	{
		switch(NPCID)
		{
		case 1: //man, woman
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 16:
		case 24:
		case 25:
			return 8;
		case 7: //farmer
			return 15;
		case 1715: //female ham
			return 19;
		case 1414: //male ham
			return 23;
		case 15: //warrior woman
		case 18: //al-kharid warrior
			return 26;
		case 187: //rogue
			return 36;
		case 2234: //master farmer
		case 2235:
			return 43;
		case 9: //guard
		case 10:
		case 32:
			return 47;
		default:
			return 1;
		}
	}
	public int getRobLevel(int NPCID)
	{
		switch(NPCID)
		{
		case 1: //man, woman
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 16:
		case 24:
		case 25:
			return 1;
		case 7: //farmer
			return 10;
		case 1715: //female h.a.m
			return 15;
		case 1714: // male h.a.m
			return 20;
		case 15: //warrior woman
		case 18: //al-kharid warrior
			return 25;
		case 187: //rogue
			return 32;
		case 2234: //master farmer
		case 2235:
			return 38;
		case 9: //guard
		case 10:
		case 32:
			return 40;
		case 36:
		case 1822: //cave goblin
		case 1823:
		case 1824:
		case 1825:
			return 36;
		case 1305: //fremennik citizen
		case 1306:
		case 1307:
		case 1308:
		case 1309:
		case 1310:
		case 1311:
		case 1312:
		case 1313:
		case 1314:
		case 1883: //bearded pollnivian bandit
		case 1884:
			return 45;
		case 1926: //desert bandit
		case 1927:
		case 1928:
		case 1929:
		case 1930:
		case 1931:
			return 53;
		case 55: //Knight, pollnivian bandit
		case 23: //Knight of Ardougne
		case 26:
		case 1880: //pollnivian bandit
		case 1881:
			return 55;
		case 34: //yanille watchman
		case 1905: //menaphite thug
			return 65;
		case 20: //paladin
		case 365:
		case 2256:
			return 70;
		case 168: //gnome woman
		case 169:
		case 66: //gnome
		case 67:
		case 68:
			return 75;
		case 21: //hero
			return 80;
		case 2363: //elf
		case 2364:
		case 2365:
		case 2366:
		case 2367:
			return 85;
		}
		return 99;
	}
	
	public void RobPerson(int id, int slot, client clt)
	{
		clt.faceNPC(slot);
		/*
		if(clt.inCombat)
		{
			clt.sM(clt.isFighting);
			return;
		}
		if(System.currentTimeMillis() - clt.lastAction < clt.actionTimer)
			return;
			*/
		if(clt.playerLevel[17] < getRobLevel(id))
		{
			clt.sendMessage("You need at least "+getRobLevel(id)+" thieving to pickpicket this "+server.npcHandler.NpcList[id].npcName+".");
			return;
		}
		if(FailPickpocket(id, clt))
		{
			clt.sendMessage("You fail to pickpocket the "+server.npcHandler.NpcList[id].npcName+".");
			WhenFailPickpocket(id, slot, clt);
			return;
		} else
		{
			clt.sendMessage("You pickpocket the "+server.npcHandler.NpcList[id].npcName+".");
			WhenSuccessPickpocket(id, clt);
		}
	}
	
	public boolean NoGuards(int objectID, client clt, int objectX, int objectY)
	{ //placeholder for method that attracts nearby guards/knights etc.
		return true;
	}
	
	public void WhenSuccessSteal(int objectID, client clt, int objectX, int objectY)
	{
		//TODO: make the stall empty when stolen from
		clt.makeGlobalObject(objectX, objectY, 6162, 0, 10);
		lastStall[getStallID(objectID, objectX, objectY)] = getStallTimerID(objectID);
		stallEmpty[getStallID(objectID, objectX, objectY)] = true;
		clt.actionTimer = 4000;
		//clt.lastAction = System.currentTimeMillis();
		clt.addSkillXP(StallXP(objectID), 17);
		int[] loot = StallLoot(objectID);
		clt.addItem(loot[0], loot[1]);
		clt.setAnimation(0x340);
		/*
		if(loot[1] == 1)
			clt.sM("You steal " + loot[1] + " " + ItemHandler.ItemList[loot[0]].itemName + ".");
		if(loot[1] > 1)
			clt.sM("You steal " + loot[1] + " " + ItemHandler.ItemList[loot[0]].itemName + "s.");
			*/
	}
	
	public int[] StallLoot(int objectID)
	{
		int[] loot = new int[2]; //id, ammount
		int chance;
		switch(objectID)
		{
		case 4708: //vegetable stall
			chance = misc.random3(5);
			loot[1] = 1;
			switch(chance)
			{
			case 0:
				loot[0] = 1957; //onion
				break;
			case 1:
				loot[0] = 1965; //cabbage
				break;
			case 2:
				loot[0] = 1942; //potato
				break;
			case 3:
				loot[0] = 1982; //tomato
				break;
			case 4:
				loot[0] = 1550; //garlic
				break;
			}
			break;
		case 2561: //baker's stall
			chance = misc.random3(100);
			loot[1] = 1;
			if(chance < 62)
				loot[0] = 1891; //cake
			if(chance >= 62 && chance < 90)
				loot[0] = 2309; //bread
			if(chance >= 90)
				loot[0] = 1901; //chocolate slice
			break;
		case 100001: //crafting stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100002: //monkey food stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100003: //monkey general stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100004: //tea stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 2560: //silk stall
			loot[0] = 950;
			loot[1] = 1;
			break;
		case 100005: //wine stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100006: //seed stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 2563: //fur stall
			loot[0] = 958;
			loot[1] = 10;
			break;
		case 100008: //fish stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100009: //crossbow stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 2565: //silver stall
			loot[0] = 442;
			loot[1] = 1;
			break;
		case 2564: //spice stall
			loot[0] = 2007;
			loot[1] = 1;
			break;
		case 100011: //magic stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 100012: //scimitar stall
			loot[0] = 995;
			loot[1] = 10;
			break;
		case 2562: //gem stall
			chance = misc.random3(20);
			loot[1] = 1;
			if(chance < 10)
				loot[0] = 1623; //uncut sapphire
			if(chance >= 10 && chance < 16)
				loot[0] = 1621; //uncut emerald
			if(chance >= 16 && chance < 19)
				loot[0] = 1619; //uncut ruby
			if(chance >= 19)
				loot[0] = 1617; //uncut diamond
			break;
		}
		return loot;
	}
	
	public int StallXP(int objectID)
	{
		switch(objectID)
		{
		case 100006: //seed stall
		case 4708: //vegetable stall
			return 10;
		case 2561: //baker's stall
		case 100001: //crafting stall
		case 100002: //monkey food stall
		case 100003: //monkey general stall
		case 100004: //tea stall
			return 16;
		case 2560: //silk stall
			return 24;
		case 100005: //wine stall
			return 27;
		case 2563: //fur stall
			return 36;
		case 100008: //fish stall
			return 42;
		case 100009: //crossbow stall
			return 52;
		case 2565: //silver stall
			return 54;
		case 2564: //spice stall
			return 81;
		case 100011: //magic stall
			return 100;
		case 100012: //scimitar stall
			return 100;
		case 2562: //gem stall
			return 160;
		}
		return 1;
	}
	
	public int getStallLevel(int objectID)
	{
		switch(objectID)
		{
		case 2560: //silk stall
			return 20;
		case 100001: //crafting stall
		case 100002: //monkey food stall
		case 100003: //monkey general stall
		case 100004: //tea stall
		case 2561: //baker's stall
			return 5;
		case 100005: //wine stall
			return 22;
		case 100006: //seed stall
			return 27;
		case 2563: //fur stall
			return 35;
		case 100008: //fish stall
			return 42;
		case 100009: //crossbow stall
			return 49;
		case 2564: //spice stall
		case 100011: //magic stall
		case 100012: //scimitar stall
			return 65;
		case 2562: //gem stall
			return 75;
		case 2565: //silver stall
			return 50;
		case 4708: //vegetable stall
			return 2;
		}
		return 99;
	}
	
	public void ThiefStall(int objectID, client clt, int objectX, int objectY)
	{
		if(stallEmpty[getStallID(objectID, objectX, objectY)])
			return;
		/*
		if(clt.inCombat)
		{
			clt.sM(clt.isFighting);
			return;
		}
		if((System.currentTimeMillis() - clt.lastAction) < clt.actionTimer)
			return;
			*/
		if(clt.playerLevel[17] < getStallLevel(objectID))
		{
			clt.sendMessage("You need a theiving level of at least " + 
					getStallLevel(objectID) + " to steal from this stall.");
			return;
		}
		if(NoGuards(objectID, clt, objectX, objectY))
		{

		} else
		{
			return;
		}
		if(!stallEmpty[getStallID(objectID, objectX, objectY)])
			WhenSuccessSteal(objectID, clt, objectX, objectY);
	}
}
