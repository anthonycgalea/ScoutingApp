package ScoutingApp;

import com.thebluealliance.api.v3.TBA;
import com.thebluealliance.api.v3.models.Match;
import com.thebluealliance.api.v3.models.SimpleMatch;
import com.thebluealliance.api.v3.models.SimpleTeam;


import java.util.ArrayList;

public class Event {

    public String eventKey;
    private com.thebluealliance.api.v3.models.Event event;
    private Match[] matches;
    private String eventName;
    public ArrayList<Integer> teamList;
    private ArrayList<ScoutingApp.Match> matchList;


    public Event(TBA tba, String eventKey) {
        try {
          //  this.event = tba.eventRequest.getEvent(eventKey);
            this.eventKey = eventKey;
            this.eventName = tba.eventRequest.getEvent(eventKey).getName();
            this.matches = tba.eventRequest.getMatches(eventKey);
            this.teamList = new ArrayList<>();
            this.matchList = new ArrayList<>();
            SimpleTeam[] teams = tba.eventRequest.getSimpleTeams(eventKey);
            for (int i = 0; i < teams.length; i++) {
                this.teamList.add(teams[i].getTeamNumber());
            }
            SimpleMatch[] matches = tba.eventRequest.getSimpleMatches(eventKey);
            for (int i = 0; i < matches.length; i++) {
                ScoutingApp.Match myMatch = new ScoutingApp.Match(matches[i]);
                this.matchList.add(myMatch);
            }

        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error, event doesn't exit");
        }

    }

    public Match getMatch(int mN) {
        try {
            return this.matches[mN];
        }
        catch (Exception e) {
            System.out.println("This match doesn't exist");
            return null;
        }
    }

    public String getEventName() {
        return this.eventName;
    }

    public void printTeamList() {
        for (int i = 0; i < this.teamList.size(); i++) {
            System.out.println(teamList.get(i));
        }
    }

    public ArrayList<Integer> getTeamList() {
        return this.teamList;
    }

    public void printMatchList() {
        for (int i = 0; i < this.matchList.size(); i++) {
            this.matchList.get(i).printMatch();
        }
    }


}
