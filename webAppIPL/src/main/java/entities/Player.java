package entities;

import javax.persistence.*;
import java.time.LocalDate;

//create table players(player_id bigint primary key auto_increment,
// first_name varchar(20),last_name varchar(20),dob date,batting_avg double,
// wickets_taken int,team_id int ,
// constraint fk_teams foreign key (team_id) references teams(team_id));
@Entity
@Table(name = "players")
public class Player extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private LocalDate dob;
    @Column(name = "batting_avg")
    private double battingAvg;
    @Column(name = "wickets_taken")
    private int wicketsTaken;

    @ManyToOne
    @JoinColumn(name = "Team", nullable = false)
    private Team theirTeam;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public Team getTheirTeam() {
        return theirTeam;
    }

    public void setTheirTeam(Team theirTeam) {
        this.theirTeam = theirTeam;
    }

    @Override
    public String toString() {
        return "Player{" +
                "Id : " + getId() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", battingAvg=" + battingAvg +
                ", wicketsTaken=" + wicketsTaken +
                '}';
    }
}
