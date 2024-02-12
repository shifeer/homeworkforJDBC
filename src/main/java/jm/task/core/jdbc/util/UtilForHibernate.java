package jm.task.core.jdbc.util;

import org.hibernate.cfg.Configuration;

public class UtilForHibernate {
    public static Configuration get() {
        return new Configuration().configure();
    }
}
