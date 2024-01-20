package beans;

import dao.TeamDao;
import dao.TeamDaoImpl;
import entities.Team;


import java.util.List;

public class TeamBean {
    TeamDao td ;
    public TeamBean() {
        td = new TeamDaoImpl();
    }

    public List<Team> getTeams() {
        System.out.println("here");
        return td.getAllTeams();
    }
}
