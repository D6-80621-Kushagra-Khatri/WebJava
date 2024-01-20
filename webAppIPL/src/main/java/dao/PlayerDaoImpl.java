package dao;

import entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static utils.HibernateUtils.*;

public class PlayerDaoImpl implements PlayerDao{

    @Override
    public String addPlayer(Player player) {
        String mesg = "Player adding failed :(";

        try(SessionFactory sf = getSf())
        {

        }

        return mesg;
    }
}
