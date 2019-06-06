package ScoutingApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class AllianceSelection {
    private ArrayList<Integer> teamsAvailable;
    private HashMap<Integer, int[]> alliances;
    private Scanner scanner;
    private int pick;
    //private boolean iriMode;
    public AllianceSelection(ArrayList<Integer> teamList, int alliances, int teamsPerAlliance, boolean iriMode) {
        this.scanner = new Scanner(System.in);
        //this.iriMode = iriMode;
        this.teamsAvailable = teamList;
        this.alliances = new HashMap<>();
        if (teamsPerAlliance < 0) {
            teamsPerAlliance*=-1;
        }
        if (teamsPerAlliance > teamList.size()) {
            teamsPerAlliance = teamList.size()/2;
        }
        if (alliances * teamsPerAlliance > teamList.size() || alliances < 0) {
            alliances = teamList.size()/teamsPerAlliance;
            System.out.println("Invalid number of alliances or teamsPerAlliance. New amount of alliances is: " + alliances);
        }
        for (int i = 1; i <= alliances; i++) {
            int[] alliance = new int[teamsPerAlliance];
            this.alliances.put(i, alliance);
        }

        System.out.println("Alliance block generated!");
        this.pick = 1;
    }

    public boolean isFinished() {
        int[] allOne = this.alliances.get(1);
        int[] allBot = this.alliances.get(this.alliances.size());
        int allSize = allOne.length - 1;
        if ((allOne[allSize] != 0)) {
            if ((allBot[allSize] != 0)) {
                return true;
            }
        }
        return false;
    }

    public int select(int round) {
        if (round%2==1) {
            if (roundOdd(round) == true) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            if (roundEven(round) == true) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }

    public boolean roundOdd(int round) {
        int i = 1;
        while(true) {
            if (!this.alliances.containsKey(i)) {
                return false;
            }
            int[] allCheck = this.alliances.get(i);
            //Check for captain first
            if (allCheck[0] == 0) {
                while(true) {
                    System.out.println("Teams Available:");
                    System.out.println(this.teamsAvailable);
                    System.out.println("Who is the #" + i + " seeded alliance captain?");
                    int team = this.scanner.nextInt();
                    if (this.teamsAvailable.contains(team)) {
                        allCheck[0] = team;
                        this.alliances.put(i, allCheck);
                        this.teamsAvailable.remove(this.teamsAvailable.indexOf(team));
                        System.out.println("Congratulations, team #" + team);
                        break;
                    } else {
                        System.out.println("Invalid team.");
                    }
                }
                break;
            }
            else if (allCheck[round] == 0) {
                while(true) {
                    System.out.println("Teams Available:");
                    System.out.println(this.teamsAvailable);
                    System.out.println("Who is the #" + this.pick + " overall pick?");
                    int team = this.scanner.nextInt();
                    if (this.teamsAvailable.contains(team)) {
                        this.pick++;
                        allCheck[round] = team;
                        this.alliances.put(i, allCheck);
                        this.teamsAvailable.remove(this.teamsAvailable.indexOf(team));
                        System.out.println("Congratulations, team #" + team);
                        break;
                    } else {
                        System.out.println("Invalid team.");
                    }
                }
                break;
            } else {
            i++;
            }
        }
        return true;
    }

    public boolean roundEven(int round) {
        int i = this.alliances.size();
        while(true) {
            if (!this.alliances.containsKey(i)) {
                return false;
            }
            int[] allCheck = this.alliances.get(i);
            //Check for if 8th seed has picked
            if (allCheck[round] == 0) {
                while(true) {
                    System.out.println("Teams Available:");
                    System.out.println(this.teamsAvailable);
                    System.out.println("Who is the #" + this.pick + " overall pick?");
                    int team = this.scanner.nextInt();
                    if (this.teamsAvailable.contains(team)) {
                        this.pick++;
                        allCheck[2] = team;
                        this.alliances.put(i, allCheck);
                        this.teamsAvailable.remove(this.teamsAvailable.indexOf(team));
                        System.out.println("Congratulations, team #" + team);
                        break;
                    } else {
                        System.out.println("Invalid team.");
                    }
                }
                break;
            } else {
            i--;
            }
        }
        return true;
    }

    public void displayAlliances() {
        System.out.println("Alliances are as follows:");
        for (int i = 1; i <= this.alliances.size(); i++) {
            int[] alliance = this.alliances.get(i);
            String allString = "\t" + alliance[0];
            for (int j = 1; j < alliance.length; j++) {
                allString+= ",\t" + alliance[j];
            }
            System.out.println("Alliance #" + i + ": " + allString);
        }
    }

    public void runAllianceSelection() {
        int round = 1;
        while (!isFinished()) {
            round += select(round);
            displayAlliances();
        }
        System.out.println("Good luck teams!");

    }

}
