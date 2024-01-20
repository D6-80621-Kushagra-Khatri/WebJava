package dao;

import entities.Team;

import java.util.List;

public interface TeamDao{
    public String addTeam(Team team);
    public String deleteTeam(Long teamId) throws InterruptedException;

    public List<Team> getAllTeams();
}
