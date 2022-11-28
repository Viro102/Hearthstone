package game;

import java.io.File;
import java.util.Scanner;

public class Load {
    public Load(String filename) {
        try (var sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String name = sc.next();
                String type = sc.next();
                int hp = sc.nextInt();
                int damage = sc.nextInt();
                int cost = sc.nextInt();
                System.out.println(name + " " + type + " " + hp + " " + damage + " " + cost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
