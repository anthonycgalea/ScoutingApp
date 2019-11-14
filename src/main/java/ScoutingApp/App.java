/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ScoutingApp;

import com.thebluealliance.api.v3.*;
import java.util.ArrayList;
import java.util.Scanner;


public class App {



    public static void main(String[] args) {
/*        String authKey = "cJQfMZDk3HKWc2odfpLjhTjUrhvNx2n2PUWx1MZLy3odT9X1mHPm1I5pUjDvhw2Q"; //Insert auth key here
        TBA tba = new TBA(authKey);
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter event key:\t");
            String key = scanner.nextLine();
            Event newEvent = new Event(tba, key);
            newEvent.printTeamList();
            System.out.println("Enter number of alliances:\t");
            int alliances = scanner.nextInt();
            System.out.println("Enter teams per alliance:\t");
            int tPA = scanner.nextInt();
            AllianceSelection as = new AllianceSelection(newEvent.getTeamList(), alliances, tPA, false);
            as.runAllianceSelection();
            System.out.println("yay, it worked :)");

        } catch (Exception e) {
            //DONE: handle exception
            System.out.println(e.toString());
            System.out.println("Wow didn't work :(");
        }
        return;

 */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event key:\t");
        String key = scanner.nextLine();
        System.out.println("Enter team number");
        int teamNum = scanner.nextInt();
        Team team = new Team(teamNum, key);
        team.testImport();
        team.testScout();
        team.updateStats();



    }

}
