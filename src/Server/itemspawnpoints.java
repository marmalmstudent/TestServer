package Server;
import java.io.*;
public class itemspawnpoints 
{
public int spawntimer = 0;

public void LoadItems()
{
for (int i = 0; i <= 5000; i++){
if(spawntimer <= 1)
{
ItemHandler.addItem(3122, 2091, 3147, 1, ItemHandler.globalItemController[i], false); // Granite shield7145
ItemHandler.addItem(4718, 3260, 3437, 1, ItemHandler.globalItemController[i], false); // Gnomebowl mould
ItemHandler.addItem(1040, 3251, 3451, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(1038, 3251, 3452, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(1042, 3251, 3453, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(1044, 3251, 3446, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(1046, 3251, 3445, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(1048, 3251, 3444, 1, ItemHandler.globalItemController[i], false); // party hat
ItemHandler.addItem(35, 3252, 3445, 1, ItemHandler.globalItemController[i], false); // sword
ItemHandler.addItem(1057, 2637, 9905, 1, ItemHandler.globalItemController[i], false); // bronze axe
ItemHandler.addItem(1055, 2636, 9904, 1, ItemHandler.globalItemController[i], false);
ItemHandler.addItem(1044, 2640, 9902, 1, ItemHandler.globalItemController[i], false);
ItemHandler.addItem(5507, 2597, 3193, 1, ItemHandler.globalItemController[i], false); //Book 
ItemHandler.addItem(6739, 3259, 3432, 1, ItemHandler.globalItemController[i], false); //axe
 ItemHandler.addItem(594, 2597, 3193, 1, ItemHandler.globalItemController[i], false); //Lit Torch
ItemHandler.addItem(995, 3269, 3434, 1000000, ItemHandler.globalItemController[i], false); // 1m
ItemHandler.addItem(995, 3253, 3436, 10000000, ItemHandler.globalItemController[i], false); // 10m
ItemHandler.addItem(2339, 3254, 3437, 1, ItemHandler.globalItemController[i], false); // 99m
ItemHandler.addItem(7119, 3257, 3439, 5000, ItemHandler.globalItemController[i], false); // c balls 5k
ItemHandler.addItem(1050, 3258, 3439, 1, ItemHandler.globalItemController[i], false); // santa
ItemHandler.addItem(4726, 3258, 3439, 1, ItemHandler.globalItemController[i], false); // 
ItemHandler.addItem(392, 3257, 3439, 5000, ItemHandler.globalItemController[i], false); // manta 5k
ItemHandler.addItem(537, 3257, 3439, 999, ItemHandler.globalItemController[i], false); //999 d bones
ItemHandler.addItem(2412, 3257, 3438, 1, ItemHandler.globalItemController[i], false); // sara cape
ItemHandler.addItem(2415, 3257, 3438, 1, ItemHandler.globalItemController[i], false); // sara staff
ItemHandler.addItem(2661, 3240, 3500, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2663, 3240, 3500, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2665, 3240, 3500, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2667, 3240, 3500, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2653, 3228, 3471, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2655, 3228, 3471, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2657, 3228, 3471, 1, ItemHandler.globalItemController[i], false); // god amrour
ItemHandler.addItem(2659, 3228, 3471, 1, ItemHandler.globalItemController[i], false); // god amrour
spawntimer = 95;
}
}
}

public void process()
{
LoadItems();
spawntimer -= 1;
}
}