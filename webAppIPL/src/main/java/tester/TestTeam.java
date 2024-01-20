package tester;

import static utils.HibernateUtils.*;
import dao.TeamDao;
import dao.TeamDaoImpl;
import entities.Team;

public class TestTeam {
    public static void main(String[] args) throws InterruptedException {
        Team csk = new Team("CHENNAI SUPER KINGS","CSK","owner1",30,50,10);
        Team gt = new Team("GUJARAT TITANS","GT","owner2",32,55,20);
        Team kkr = new Team("KOLKATA KNIGHT RIDERS","KKR","owner3",28,30,40);

        TeamDao td =new TeamDaoImpl();
//            System.out.println(td.addTeam(csk));
//            System.out.println(td.addTeam(gt));
//            System.out.println(td.addTeam(kkr));
            td.getAllTeams().forEach(System.out::println);

    }

}