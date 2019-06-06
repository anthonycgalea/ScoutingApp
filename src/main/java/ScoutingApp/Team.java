package ScoutingApp;

import java.util.ArrayList;

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
        for (int i = 0; i < values; i++) {
            ArrayList<Double> newList = new ArrayList<>();
            this.stats.add(newList);
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


}