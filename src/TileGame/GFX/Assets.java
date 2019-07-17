package TileGame.GFX;

import java.awt.image.BufferedImage;

public class Assets {


    public static BufferedImage player1, blackCircle,player2, lantern, darkScr, rockTile, flowers, treePink, player3, key, coin, eva, heather, amber;
    public static BufferedImage grassTileLight, fireball, grassTileDark, water, beach, baseChar, menuScr, exitscr, inventoryscr, charselscr, npc, winscr, ctrlscr, aplus, a, aminus, bplus, b, bminus, cplus, c, cminus;
    public static BufferedImage[] player_down, basechar, player_up, player_still, btn_start, btn_highscores, h_player_down, h_player_up, h_player_still, e_player_down, e_player_up, e_player_still, mbtn;
    public static BufferedImage[] g_dinorun_right, g_dinorun_left, r_dinorun_right, r_dinorun_left, b_dinorun_right, b_dinorun_left, y_dinorun_right, y_dinorun_left;
    public static BufferedImage[] bossAttack, bossIdle, bossDie;
    public static BufferedImage[] greenSlime, redSlime, blueSlime;

	public static void init() {
		SpriteSheet lanternScr = new SpriteSheet(ImageLoader.loadImage("/Textures/darkScreen.png"));
		SpriteSheet lanternPic = new SpriteSheet(ImageLoader.loadImage("/Textures/lantern.png"));
		SpriteSheet black = new SpriteSheet(ImageLoader.loadImage("/Textures/black3.png"));
		SpriteSheet rock = new SpriteSheet(ImageLoader.loadImage("/Textures/rocktile.png"));
		SpriteSheet waterTile = new SpriteSheet(ImageLoader.loadImage("/Textures/0.png"));
		SpriteSheet heatherLastWalk = new SpriteSheet(ImageLoader.loadImage("/Textures/walk.png"));
		SpriteSheet slime = new SpriteSheet(ImageLoader.loadImage("/Textures/slime.png"));
		SpriteSheet fire = new SpriteSheet(ImageLoader.loadImage("/Textures/FB500-1.png"));
		SpriteSheet bgTiles = new SpriteSheet(ImageLoader.loadImage("/Textures/tiles.png"));
		SpriteSheet playerMovements = new SpriteSheet(ImageLoader.loadImage("/Textures/baseWalking.png"));
		SpriteSheet idleBoss = new SpriteSheet(ImageLoader.loadImage("/Textures/Idle_Spritesheet.png"));
		SpriteSheet attackBoss = new SpriteSheet(ImageLoader.loadImage("/Textures/Attack_Spritesheet.png"));
		SpriteSheet dieBoss = new SpriteSheet(ImageLoader.loadImage("/Textures/Death_Spritesheet.png"));
		SpriteSheet inventory = new SpriteSheet(ImageLoader.loadImage("/Textures/inventory.png"));
    	SpriteSheet keyPic = new SpriteSheet(ImageLoader.loadImage("/Textures/key.png"));
    	SpriteSheet coinPic = new SpriteSheet(ImageLoader.loadImage("/Textures/coin.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/download.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/Textures/grass_tile_1.png"));
        SpriteSheet menu = new SpriteSheet(ImageLoader.loadImage("/Textures/menu.png"));
        SpriteSheet heatherscr = new SpriteSheet(ImageLoader.loadImage("/Textures/heather.png"));
        SpriteSheet evascr = new SpriteSheet(ImageLoader.loadImage("/Textures/eva.png"));
        SpriteSheet amberscr = new SpriteSheet(ImageLoader.loadImage("/Textures/amber.png"));
        SpriteSheet exit = new SpriteSheet(ImageLoader.loadImage("/Textures/exit_bg.jpg"));
		SpriteSheet walking = new SpriteSheet(ImageLoader.loadImage("/Textures/walking.png"));
		SpriteSheet walking1 = new SpriteSheet(ImageLoader.loadImage("/Textures/walking1.png"));
		SpriteSheet walking2 = new SpriteSheet(ImageLoader.loadImage("/Textures/walking2.png"));
		SpriteSheet dinos = new SpriteSheet(ImageLoader.loadImage("/Textures/dinos.png"));
        SpriteSheet idle = new SpriteSheet(ImageLoader.loadImage("/Textures/idle.png"));
        SpriteSheet idle1 = new SpriteSheet(ImageLoader.loadImage("/Textures/idle1.png"));
        SpriteSheet idle2 = new SpriteSheet(ImageLoader.loadImage("/Textures/idle2.png"));
        SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/Textures/buttons.png"));
        SpriteSheet NPC = new SpriteSheet(ImageLoader.loadImage("/Textures/NPC.png"));
        SpriteSheet winScr = new SpriteSheet(ImageLoader.loadImage("/Textures/WinScreen.png"));
        SpriteSheet ctrlScr = new SpriteSheet(ImageLoader.loadImage("/Textures/Controls.png"));
        SpriteSheet mPlayer = new SpriteSheet(ImageLoader.loadImage("/Textures/mPlayer.png"));
        SpriteSheet aPlus = new SpriteSheet(ImageLoader.loadImage("/Textures/Ap.png"));
        SpriteSheet A = new SpriteSheet(ImageLoader.loadImage("/Textures/A.png"));
        SpriteSheet aMinus = new SpriteSheet(ImageLoader.loadImage("/Textures/A-.png"));
        SpriteSheet bPlus = new SpriteSheet(ImageLoader.loadImage("/Textures/Bp.png"));
        SpriteSheet B = new SpriteSheet(ImageLoader.loadImage("/Textures/B.png"));
        SpriteSheet bMinus = new SpriteSheet(ImageLoader.loadImage("/Textures/B-.png"));
        SpriteSheet cPlus = new SpriteSheet(ImageLoader.loadImage("/Textures/Cp.png"));
        SpriteSheet C = new SpriteSheet(ImageLoader.loadImage("/Textures/C.png"));
        SpriteSheet cMinus = new SpriteSheet(ImageLoader.loadImage("/Textures/C-.png"));
        
        player_down = new BufferedImage[4];
        player_up = new BufferedImage[4];
        player_still = new BufferedImage[5];
        
        h_player_down = new BufferedImage[4];
        h_player_up = new BufferedImage[4];
        h_player_still = new BufferedImage[5];
        
        e_player_down = new BufferedImage[4];
        e_player_up = new BufferedImage[4];
        e_player_still = new BufferedImage[5];
        
        r_dinorun_left = new BufferedImage[11];
        g_dinorun_left = new BufferedImage[11];
        b_dinorun_left = new BufferedImage[11];
        y_dinorun_left = new BufferedImage[11];
        
        r_dinorun_right = new BufferedImage[11];
        g_dinorun_right = new BufferedImage[11];
        b_dinorun_right = new BufferedImage[11];
        y_dinorun_right = new BufferedImage[11];
        
        greenSlime = new BufferedImage[3];
        blueSlime = new BufferedImage[3];
        redSlime = new BufferedImage[3];
        
        bossIdle = new BufferedImage[24];
        bossAttack = new BufferedImage[24];
        bossDie = new BufferedImage[16];
        
        btn_start = new BufferedImage[2];
        btn_highscores = new BufferedImage[2];
        basechar = new BufferedImage[2];
        mbtn = new BufferedImage[2];
        
        for (int i = 0; i < 3; i++) {
        	greenSlime[i] = slime.crop(i * 50 + 14, 17, 30, 30);
        }
        
        for (int i = 0; i < 3; i++) {
        	blueSlime[i] = slime.crop(i * 50 + 14, 65, 30, 30);
        }
        
        for (int i = 0; i < 3; i++) {
        	redSlime[i] = slime.crop(i * 50 + 14, 115, 30, 30);
        }
        
        for(int i = 0; i < g_dinorun_left.length; i++) {
        	g_dinorun_left[i] = dinos.crop(72 * i, 0, 72, 72); 
        }
         
        for(int i = 0; i < r_dinorun_left.length; i++) {
        	r_dinorun_left[i] = dinos.crop(72 * i, 72, 72, 72); 
        }
        
        for(int i = 0; i < y_dinorun_left.length; i++) {
        	y_dinorun_left[i] = dinos.crop(72 * i, 144, 72, 72); 
        }
        
        for(int i = 0; i < b_dinorun_left.length; i++) {
        	b_dinorun_left[i] = dinos.crop(72 * i, 216, 72, 72); 
        }
        
        for(int i = 0; i < g_dinorun_right.length; i++) {
        	g_dinorun_right[i] = dinos.crop(72 * i, 298, 72, 72); 
        }
         
        for(int i = 0; i < r_dinorun_right.length; i++) {
        	r_dinorun_right[i] = dinos.crop(72 * i, 370, 72, 72); 
        }
        
        for(int i = 0; i < y_dinorun_right.length; i++) {
        	y_dinorun_right[i] = dinos.crop(72 * i, 442, 72, 72); 
        }
        
        for(int i = 0; i < b_dinorun_right.length; i++) {
        	b_dinorun_right[i] = dinos.crop(72 * i, 514, 72, 72); 
        }
        
        int count = 0;
        for(int j = 0; j < 6; j++) {
        	for(int i = 0; i < 4; i++) {
        		bossIdle[count] = idleBoss.crop(990 * i, 681 * j, 990, 681);
        		count++;
        	}
        } 
        
        count = 0;
        for(int j = 0; j < 6; j++) {
        	for(int i = 0; i < 4; i++) {
        		bossAttack[count] = attackBoss.crop(990 * i, 681 * j, 990, 681);
        		count++;
        	}
        } 
        
        count = 0;
        for(int j = 0; j < 4; j++) {
        	for(int i = 0; i < 4; i++) {
        		bossDie[count] = dieBoss.crop(990 * i, 681 * j, 990, 681);
        		count++;
        	}
        } 
        
        
   
        
        
        
        btn_start[0] = buttons.crop(5, 207, 220, 95);
        btn_start[1] = buttons.crop(341, 5, 220, 95);
        
        btn_highscores[0] = buttons.crop(5, 5, 330, 95);
        btn_highscores[1] = buttons.crop(5,  106, 330, 95);
        
        mbtn[0] = mPlayer.crop(0, 0, 332, 119);
        mbtn[1] = mPlayer.crop(0, 0, 332, 119);

        //AMBER
        
        player_still[0] = idle.crop(5, 5, 511, 447);
        player_still[1] = idle.crop(521, 5, 511, 447);
        player_still[2] = idle.crop(1037, 5, 511, 447);
        player_still[3] = idle.crop(5, 457, 511, 447);
        player_still[4] = idle.crop(521, 457, 511, 447);
        
        player_down[0] = walking.crop(539, 929, 529, 457);
        player_down[1] = walking.crop(1073, 5, 529, 457);
        player_down[2] = walking.crop(1073, 467, 529, 457);
        player_down[3] = walking.crop(5, 929, 529, 457);
        
        player_up[0] = walking.crop(539, 5, 529, 457);
        player_up[1] = walking.crop(539, 467, 529, 457);
        player_up[2] = walking.crop(5, 467, 529, 457);
        player_up[3] = walking.crop(5, 5, 529, 457);
        
        
        //EVA
        
        e_player_still[0] = idle1.crop(5, 5, 480, 447);
        e_player_still[1] = idle1.crop(491, 5, 480, 447);
        e_player_still[2] = idle1.crop(977, 5, 480, 447);
        e_player_still[3] = idle1.crop(5, 457, 480, 447);
        e_player_still[4] = idle1.crop(491, 457, 480, 447);
        
        e_player_up[0] = walking1.crop(0, 0, 491, 452);
        e_player_up[1] = walking1.crop(0, 452, 491, 452);
        e_player_up[2] = walking1.crop(982, 0, 491, 452);
        e_player_up[3] = walking1.crop(0, 904, 491, 452);
        
        e_player_down[0] = walking1.crop(491, 0, 491, 452);
        e_player_down[1] = walking1.crop(491, 452, 491, 452);
        e_player_down[2] = walking1.crop(982, 452, 491, 452);
        e_player_down[3] = walking1.crop(491, 904, 491, 452);
        
        
        //HEATHER
        
        for(int i = 0; i < 5; i++) {
        	h_player_still[i] = idle2.crop(511 * i, 0, 511, 454);
        }
        
        h_player_up[0] = walking2.crop(0, 0, 524, 464);
        h_player_up[1] = walking2.crop(0, 464, 524, 464);
        h_player_up[2] = walking2.crop(1048, 0, 524, 464);
        h_player_up[3] = walking2.crop(1048, 917, 524, 464);
        
        h_player_down[0] = walking2.crop(524, 0, 524, 464);
        h_player_down[1] = walking2.crop(524, 464, 524, 464);
        h_player_down[2] = walking2.crop(1048, 464, 524, 464);
        h_player_down[3] = heatherLastWalk.crop(0, 0, 510, 453);
        
        fireball = fire.crop(0,0,512,512);
        
        menuScr = menu.crop(0,0,1024,768);
        darkScr = lanternScr.crop(0,0,1024,768);
        lantern = lanternPic.crop(0,0,692,936);
        blackCircle = black.crop(0,0,1660,942);
        heather = heatherscr.crop(0,0,1024,768);
        eva = evascr.crop(0,0,1024,768);
        amber = amberscr.crop(0,0,1024,768);
        exitscr = exit.crop(0, 0, 850, 480);
        inventoryscr = inventory.crop(0, 0, 880, 480);
        winscr = winScr.crop(0, 0, 224, 160);
        ctrlscr = ctrlScr.crop(0, 0, 960, 720);
        
        aplus = aPlus.crop(0, 0, 250, 150);
        a = A.crop(0, 0, 250, 150);
        aminus = aMinus.crop(0, 0, 250, 150);
        
        bplus = bPlus.crop(0, 0, 250, 150);
        b = B.crop(0, 0, 250, 150);
        bminus = bMinus.crop(0, 0, 250, 150);
        
        cplus = cPlus.crop(0, 0, 250, 150);
        c = C.crop(0, 0, 250, 150);
        cminus = cMinus.crop(0, 0, 250, 150);

		player_down[0] = walking.crop(539, 929, 529, 457);
		player_down[1] = walking.crop(1073, 5, 529, 457);
		player_down[2] = walking.crop(1073, 467, 529, 457);
		player_down[3] = walking.crop(5, 929, 529, 457);

		player_up[0] = walking.crop(539, 5, 529, 457);
		player_up[1] = walking.crop(539, 467, 529, 457);
		player_up[2] = walking.crop(5, 467, 529, 457);
		player_up[3] = walking.crop(5, 5, 529, 457);

		key = keyPic.crop(0, 0, 100, 100);
		coin = coinPic.crop(0, 0, 100, 100);
		player1 = sheet.crop(16, 205, 60, 60);
		player2 = sheet.crop(5, 189, 130, 130);
		flowers = sheet.crop(463, 31, 56, 28);
		treePink = sheet.crop(463, 254, 53, 74);
		player3 = sheet1.crop(0, 0, 53, 74);

		player1 = sheet.crop(16, 205, 60, 60);
		player2 = sheet.crop(5, 189, 130, 130);
		flowers = sheet.crop(463, 31, 56, 28);
		treePink = sheet.crop(463, 254, 53, 74);
		player3 = sheet1.crop(0, 0, 53, 74);

		basechar[0] = playerMovements.crop(0, 0, 20, 25);
		basechar[1] = playerMovements.crop(0, 0, 30, 35);
        
		
		baseChar = playerMovements.crop(0, 0, 30, 35);
		rockTile = rock.crop(0,  0, 625, 625);
		water = waterTile.crop(0,  0, 625, 625);
		grassTileLight = bgTiles.crop(5, 415, 400, 400);
		grassTileDark = bgTiles.crop(415, 5, 400, 400);
		beach = bgTiles.crop(415, 415, 400, 400); // Find actual width/height value
		
		npc = NPC.crop(5, 5, 225, 200);
		
	}
}
