package Server;
public class CastleWars {

   public static int CastleWarsTimer = 1320;
   public static int StartTimer = 500;
   public static int SaraScore = 0;
   public static int ZammyScore = 0;
   public static boolean castleWarsOn = false;
   public static boolean castleinterface = false;
   public static boolean ZammyFlagCapture = false;
   public static boolean SaraFlagCapture = false;
   public static boolean catoperational = true;
   public static int doorhealth = 1000;
   public static int maxPlayers = 50;
   public static int maxZammy = 0;
   public static int maxSara = 0;


   public static boolean setUp() {
      return true;
   }

   public boolean process() {
      ++StartTimer;
      if(StartTimer >= 600 && !castleWarsOn) {
         PlayerHandler.messageToAll = "A CastleWars game has begun!!!";
         castleWarsOn = true;
         StartTimer = 0;
         castleinterface = false;
      }

      if(castleWarsOn && CastleWarsTimer >= 0) {
         --CastleWarsTimer;
      }

      if(castleWarsOn && CastleWarsTimer < 0) {
         castleWarsEnd();
      }

      return true;
   }

   public static void castleLoop(int var0) {
      if(var0 != 0) {
         ;
      }
   }

   public static void castleWarsEnd() {
      CastleWarsTimer = 1320;
      StartTimer = 0;
      SaraScore = 0;
      ZammyScore = 0;
      castleinterface = true;
      castleWarsOn = false;
      //client.castleinter = false; // variable does not appear to exist
      SaraFlagCapture = false;
      ZammyFlagCapture = false;
      PlayerHandler.messageToAll = "A CastleWars game has just ended";
      maxSara = 0;
      maxZammy = 0;
   }

}
