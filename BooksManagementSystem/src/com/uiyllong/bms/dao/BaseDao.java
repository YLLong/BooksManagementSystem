package com.uiyllong.bms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by uilong on 2016/5/25.
 */
public class BaseDao {

    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    public static Session getSession() {
        configuration = new Configuration().configure();
        sessionFactory  = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        return session;
    }

    public static void getclose() {
        session.close();
        sessionFactory.close();
    }

}
