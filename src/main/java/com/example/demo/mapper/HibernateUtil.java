package com.example.demo.mapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public final class HibernateUtil {

    private static SessionFactory sf; // 现实中需要考虑属性的线程安全问题。
    public static final ThreadLocal<Session> tl = new ThreadLocal<Session>();
    // 用线程局部变量。ThreadLocal其实更像一个工具类，他能维持每个不同线程的局部变量，
    //简单的说就是能够根据程序运行时的线程分配给它独立的变量，这样可以很好的在同一个线程的不同对象间共享同一个变量
    // 防止出现同属一个session的事务启用多个session会出现错误。

    static {
        try {
            Configuration cfg = new Configuration().configure();
            sf = cfg.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
        }

    } // singleton 单例模式。。。。

    /*
     * SessionFactory是重量级资源,创建此对象需要耗费更多的系统资源.所以当我们应用中针对一个数据库资源我们只创建一个SessionFactory实例,
     * 当我们的应用中需要连接到多个数据库的时候,我们就会为每一个数据库创建一个SessionFactory.
     * 单一实例：在系统中全局使用一个唯一的SessionFactory实 例。 主要的原因一是Factory只需要一个实例可以调用方法就可以；
     * 另一方面取得SessionFactory需要的时间太久，每次都实例化， 会过分浪费系统CPU资源。
     * 每个线和使用自身对应的数据库连接session： 这里是为每个线程建立了一个局部的变量来达到这个目的。
     */

    /**
     * 得到session。
     * @return
     */
    public synchronized static Session getSessoin() {
        Session session = tl.get();
        if (session == null) {
            session = sf.openSession();
            tl.set(session);
        }
        return session;
    }

    /**
     * 关闭session
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = tl.get();
        tl.set(null);
        if (session != null)
            session.close();
    }

    /**
     * 得到sessionfactory。
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            Configuration cfg = new Configuration().configure();
            sf = cfg.buildSessionFactory();
        }
        return sf;
    }

    /**
     * 关闭sessionfactory。
     *
     * @return
     */
    public static void closeSessionFactory() {
        if (!sf.isClosed()) { // 避免潜在的多线程并发问题
            sf.close();
        }
    }
}
