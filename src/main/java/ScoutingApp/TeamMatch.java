package ScoutingApp;

import java.util.ArrayList;

public class TeamMatch {

    private Team robot;
    private double[] matchData;

/*  info for data
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

 */

    public TeamMatch(Team robot, int matchNum) {

        this.matchData = new double[robot.values];
        this.matchData[0] = matchNum;

    }


    public void finishMatch() {
        this.robot.submitMatch(this.matchData);
    }


}
