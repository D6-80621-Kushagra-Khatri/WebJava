package dao;

import entities.Team;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static utils.HibernateUtils.*;

public class TeamDaoImpl implements TeamDao{
    @Override
    public String addTeam(Team team) {

        SessionFactory sf = getSf();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Serializable teamId = session.save(team);
            tx.commit();
            return "record inserted! :) with id " + teamId;
        } catch (HibernateException e) {
            if (tx.isActive())
                tx.rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }
    }

    @Override
    public String deleteTeam(Long teamId) throws InterruptedException {

        SessionFactory sf = getSf();
        Session sesh = sf.getCurrentSession();
        Transaction tx = sesh.beginTransaction();
//        Team teamToDel = sesh.get(Team.class, teamId);
//        sesh.delete(teamToDel);
//        System.out.println(sesh.get(Team.class, teamId));
        List<Team> teams = sesh.createQuery("select t from Team t", Team.class).getResultList();
        Thread.sleep(10000);
        teams.forEach(System.out::println);
        tx.commit();
        return "deleted!!";

    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teamList;
        Session sesh = getSf().getCurrentSession();
        Transaction tx = sesh.beginTransaction();
        try {
            teamList = sesh.createQuery("select t from Team t", Team.class).getResultList();
            tx.commit();
        }
        catch (RuntimeException e)
        {
            tx.rollback();
            throw e;
        }
        return teamList;
    }

}
