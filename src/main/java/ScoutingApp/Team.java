package ScoutingApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    public int id; //Team Number
    public String eventKey; //EventKey as a backup. Will probably remove eventually.
    //Indexes for stats.
    public final int matchNo = 0;
    public final int csCargoPM = 1;
    public final int csHatchPM = 2;
    public final int csCargoSS = 3;
    public final int csHatchSS = 4;
    public final int rkLCargoPM = 5;
    public final int rkLHatchPM = 6;
    public final int rkMCargoPM = 7;
    public final int rkHCargoPM = 8;
    public final int rkMHatchPM = 9;
    public final int rkHHatchPM = 10;
    public final int rkCargoSS = 11;
    public final int rkHatchSS = 12;
    //Below here should be API data from TBA.
    public final int apiData = 13; //number for data that you don't receive from the API
    public final int endgamePos = 13;
    public final int ssCross = 14;
    public final int ssStart = 15;
    public final int values = 16; //CHANGE THIS IF YOU ADD OR REMOVE VALUES OTHERWISE IT WILL NOT WORK
    public int matchesPlayed;
    private double[] averages;
    private double[] maxes;
    private double[] medians;
    private double[] mins;
    private double[] stdDevs;
    private ArrayList<ArrayList<Double>> stats;
    public boolean upToDate;

    public Team(int id, String eventKey) {
        this.id = id;
        this.eventKey = eventKey;
        this.averages = new double[this.values];
        this.maxes = new double[this.values];
        this.medians = new double[this.values];
        this.mins = new double[this.values];
        this.stdDevs = new double[this.values];
        this.matchesPlayed = 0;
        this.stats = new ArrayList<>();
        for (int i = 0; i < values; i++) {
            ArrayList<Double> newList = new ArrayList<>();
            this.stats.add(newList);
        }
        try {
            String line = "";
            Scanner scanner = new Scanner(new File("res/" + id + ".txt"));
            while(scanner.hasNext()) {
                line = scanner.nextLine();
                char j;
                Double mNo;
                int k = 0;
                String temp = "";
                j = line.charAt(0);
                while (j != ',') {
                    temp+=j;
                    k++;
                    j = line.charAt(k);
                }
                mNo = Double.parseDouble(temp);
                if (!stats.get(0).contains(mNo)) {
                    System.out.println("Found unique match!");
                    matchesPlayed++;
                    k = 0;
                    for (int l = 0; l < values; l++) {
                        System.out.println("importing stat #" + l);
                        Double num;
                        temp = "";
                        j = line.charAt(k);
                        while (j != ',') {
                            temp+=j;
                            k++;
                            j = line.charAt(k);
                        }
                        k++;
                        num = Double.parseDouble(temp);
                        stats.get(l).add(num);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("New team!");

        }
        upToDate = true;
    }

    public double findMax(int stat) {
        return this.maxes[stat];
    }

    public double findMin(int stat) {
        return this.mins[stat];
    }

    public double findMedian(int stat) {
        return this.medians[stat];
    }

    public void updateAverages() {
        for (int i = 0; i < this.averages.length; i++) {
            this.averages[i] = myMath.average(this.stats.get(i));
        }
    }

    public void displayStats() {
        System.out.println("cargo average:" + averages[csCargoPM]);
    }

    public void testScout() {
        TeamMatch testMatch = new TeamMatch(this, 1);
        testMatch.scoutMatch();
        exportMatches();
    }

    public void testImport() {
        importMatches();
    }

    public void updateMaxes() {
        for (int i = 0; i < this.maxes.length; i++) {
            this.maxes[i] = myMath.average(this.stats.get(i));
        }
    }

    public void updateMedians() {
        for (int i = 0; i < this.medians.length; i++) {
            this.medians[i] = myMath.findMedian(this.stats.get(i));
        }
    }

    public void updateMins() {
        for (int i = 0; i < this.mins.length; i++) {
            this.mins[i] = myMath.findMin(this.stats.get(i));
        }
    }

    public void updateStdDevs() {
        for (int i = 0; i < this.stdDevs.length; i++) {
            this.stdDevs[i] = myMath.stdDev(this.stats.get(i));
        }
    }

    public void updateStats() {
        this.updateAverages();
        this.updateMaxes();
        this.updateMedians();
        this.updateMins();
        this.updateStdDevs();
        this.upToDate = true;
        return;
    }

    public void submitMatch(double[] matchStats) {
        this.matchesPlayed++;
        for (int i = 0; i < matchStats.length; i++) {
            ArrayList<Double> listToUpdate = this.stats.get(i);
            listToUpdate.add(matchStats[i]);
            this.stats.set(i, listToUpdate);
        }
        this.upToDate = false;
    }

    public void importMatches() {
        //DONE: this
        try {
            String line = "";
            Scanner scanner = new Scanner(new File("res/toImport/" + id + ".txt"));
            while(scanner.hasNext()) {
                line = scanner.nextLine();
                char j;
                Double mNo;
                int k = 0;
                String temp = "";
                j = line.charAt(0);
                while (j != ',') {
                    temp+=j;
                    k++;
                    j = line.charAt(k);
                }
                mNo = Double.parseDouble(temp);
                if (!stats.get(0).contains(mNo)) {
                    System.out.println("Found unique match!");
                    matchesPlayed++;
                    k = 0;
                    for (int l = 0; l < values; l++) {
                        System.out.println("importing stat #" + l);
                        Double num;
                        temp = "";
                        j = line.charAt(k);
                        while (j != ',') {
                            temp+=j;
                            k++;
                            j = line.charAt(k);
                        }
                        k++;
                        num = Double.parseDouble(temp);
                        stats.get(l).add(num);
                    }
                }
            }
            scanner.close();
            File file = new File("res/toImport/" + id + ".txt");
            file.delete();
        }
        catch (IOException e) {
            System.out.println("No file to import!");
    }
        return;
    }

    public void exportMatches() {
        //DONE: this?????
        //I think this should work? I love how I'm programming this in a stupid order so I can't test this until way later.
        try {
            File file = new File("res/" + id + ".txt");
            file.delete();
            String line = "";
            FileWriter fw = new FileWriter("res/" + id+ ".txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < matchesPlayed; i++) {
                line = "";
                System.out.println("Exporting match #" + i);
                for (int j = 0; j < values; j++) {
                    System.out.println("Exporting stat #" + j);
                    line+=stats.get(j).get(i);
                        line += ",";
                }
                line+=".";
                System.out.println(line);
                pw.println(line);
            }
            pw.close();
            System.out.println("successfully exported");
        }
        catch (IOException e) {
            System.out.println("die dumbass");
        }
    }

}