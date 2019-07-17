package TileGame.Worlds;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MakeTextFile {

    // 0 is barrier and 3

    // World 1 ; 0 is barrier
    // World 2 : 3 is barrier

    // Boss Method Done right



    public void createBossTextFile(String fileName, int numberOfGold, String[] enemies) {
        Path file = Paths.get(fileName);
        try {
            createEnemies(file, enemies);
            createBossGold(file, numberOfGold);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNonBossTextFile(String input, String output, int barrier, String[] enemies, int gold, World world) {
        Path inputPath = Paths.get(input);
        Path outputPath = Paths.get(output);

        try {
            createEnemies(outputPath, enemies);
            createNonBossGold(inputPath, outputPath, barrier, gold, world);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

    private void createNonBossGold(Path inputPath, Path outputPath, int barrier, int gold, World world) throws IOException {
        List<String> tempLines = Files.readAllLines(outputPath);
        tempLines.add("Items:");

        for (int i = 0; i < gold; i++) {
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            sb.append("1 ");

            while (true) {
                int x = r.nextInt(1200);
                int y = r.nextInt(1200);
                
                if (world.getTile(y, x).isWalkable()) {
                	//System.out.println("x is " + x + "y is " + y);
                    sb.append(x);
                    sb.append(" ");
                    sb.append(y);
                    tempLines.add(sb.toString());
                    break;
                }
                            
            }
        }


        Files.write(outputPath, tempLines, Charset.forName("UTF-8"));
    }

    private void createBossGold(Path file, int numberOfGold) throws IOException {
        List<String> tempLines = Files.readAllLines(file);
        tempLines.add("Items:");

        Random r = new Random(); // Randomly generate a number between 1280

        for (int i = 0; i < numberOfGold; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("1 ");
            sb.append(r.nextInt(1280));
            sb.append(" ");
            sb.append(r.nextInt(1280));
            tempLines.add(sb.toString());
        }

        Files.write(file, tempLines, Charset.forName("UTF-8"));
    }

    private void createEnemies(Path file, String[] enemies) throws IOException {
        List<String> lines = Arrays.asList(enemies);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

}