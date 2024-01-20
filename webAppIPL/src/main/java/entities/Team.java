package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{
    @Column(unique = true, length = 100)
    private String name;
    @Column(unique = true, length = 10)
    private String abbreviation;
    @Column(length = 20, nullable = false)
    private String owner;
    @Column(name = "max_age")
    private int maxAge;
    @Column(name = "batting_avg")
    private double battingAvg;
    @Column(name = "wickets_taken")
    private int wicketsTaken;

    @OneToMany(mappedBy = "theirTeam", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Player> playersList = new ArrayList<>();

    public Team() {
    }

    public Team(String name, String abbreviation, String owner, int maxAge, double battingAvg, int wicketsTaken) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.owner = owner;
        this.maxAge = maxAge;
        this.battingAvg = battingAvg;
        this.wicketsTaken = wicketsTaken;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public double getBattingAvg() {
        return battingAvg;
    }

    public void setBattingAvg(double battingAvg) {
        this.battingAvg = battingAvg;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public void addPlayer(Player player)
    {
        playersList.add(player);
        player.setTheirTeam(this);
    }
    public void removePlayer(Player player)
    {
        playersList.remove(player);
        player.setTheirTeam(null);
    }

    @Override
    public String toString() {
        return "Team{" +
                "Id : " + getId() +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", owner='" + owner + '\'' +
                ", maxAge=" + maxAge +
                ", battingAvg=" + battingAvg +
                ", wicketsTaken=" + wicketsTaken +
                '}';
    }
}
