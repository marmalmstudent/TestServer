package Skills;
import Server.*;

public class Firemaking {

	/*
	 * TODO: properly reset animation when interruptin firemaking or
	 * when successfully lighting a fire.
	 * TODO: make it possible to light a fire when using a tinderbox
	 * on logs on the ground.
	 * TODO: walk 1 tile away from the fire when sucessfully lighting
	 * a fire.
	 */
	public Firemaking()
	{
		
	}
	
	public int getFiremakingLevelReq(int logsID)
	{
		switch(logsID)
		{
		case 1511: //normal
		case 2511: //normal
		case 2862: //achey
		case 7504: //red
		case 7505: //green
		case 7506: //blue
			return 1;
		case 1513: //magic
			return 75;
		case 1515: //yew
			return 60;
		case 1517: //maple
			return 45;
		case 1519: //willow
			return 30;
		case 1521: //oak
			return 15;
		case 6332: //mahogany
			return 50;
		case 6333: //teak
			return 35;
		default:
			return 99;
		}
	}
	
	public int getFiremakingXP(int logsID)
	{
		switch(logsID)
		{
		case 1511: //normal
		case 2511: //normal
		case 2862: //achey
			return 40;
		case 7504: //red
		case 7505: //green
		case 7506: //blue
			return 50;
		case 1513: //magic
			return (int)303.8;
		case 1515: //yew
			return (int)202.5;
		case 1517: //maple
			return 135;
		case 1519: //willow
			return 90;
		case 1521: //oak
			return 60;
		case 6332: //mahogany
			return (int)157.5;
		case 6333: //teak
			return 105;
		default:
			return 0;
		}
	}
	
	public void AttemptLight(int logsID, client clt)
	{
		if(clt.playerLevel[clt.playerFiremaking] >= getFiremakingLevelReq(logsID))
		{
			clt.OriginalWeapon = clt.playerEquipment[clt.playerWeapon];
			clt.OriginalShield = clt.playerEquipment[clt.playerShield];
			clt.playerEquipment[clt.playerWeapon] = 590;
			clt.playerEquipment[clt.playerShield] = -1;
			clt.startAnimation(0x2DD);
			clt.sendMessage("You attempt to light a fire...");
			clt.lastFireLightAnim = System.currentTimeMillis();
			clt.isLighting = true;
			clt.lightLogsID = logsID;
		} else
			clt.sendMessage("You need a firemaking level of at least "+getFiremakingLevelReq(logsID)+" to light these logs");
	}

	public void WhileAttempting(client clt)
	{
		if(9900*Math.random() < 50 + 5*clt.playerLevel[clt.playerFiremaking])
		{
			clt.isLighting = false;
			clt.resetAnimation();
			LightFire(clt);
		} else if(System.currentTimeMillis() - clt.lastFireLightAnim > 8000)
		{
			clt.startAnimation(0x2DD);
			clt.lastFireLightAnim = System.currentTimeMillis();
		}
	}
	public void LightFire(client clt)
	{
		clt.playerEquipment[clt.playerWeapon] = clt.OriginalWeapon;
		clt.playerEquipment[clt.playerShield] = clt.OriginalShield;
		clt.OriginalWeapon = -1;
		clt.OriginalShield = -1;
		clt.makeGlobalObject(clt.absX, clt.absY, 2732, 2, 10);
		clt.deleteItem(clt.lightLogsID, clt.getItemSlot(clt.lightLogsID), 1);
		clt.addSkillXP(getFiremakingXP(clt.lightLogsID), clt.playerFiremaking);
		clt.MoveCharacter(clt.absX - 1, clt.absY);
	}
}
