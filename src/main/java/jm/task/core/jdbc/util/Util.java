package jm.task.core.jdbc.util;


import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public final class Util {
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE = "db.pool.size";
    private static final int DEFAULT_SIZE = 10;
    private static ArrayBlockingQueue<Connection> pool;
    private static List<Connection> connectionListForClose;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        int size = PropertiesUtil.get(POOL_SIZE) != null ? Integer.parseInt(PropertiesUtil.get(POOL_SIZE)) : DEFAULT_SIZE;
        pool = new ArrayBlockingQueue<>(size);
        connectionListForClose = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Connection connection = open();
            Connection proxyCon = (Connection) Proxy.newProxyInstance(Util.class.getClassLoader(), new Class[]{Connection.class}, (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) : method.invoke(connection, args));
            pool.add(proxyCon);
            connectionListForClose.add(connection);
        }
    }
    public static void closePoolConnection() {
        for (Connection connection : connectionListForClose) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Connection get(){
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Util() {
    }
    private static Connection open() {
        try {
            return DriverManager.getConnection(PropertiesUtil.get(URL_KEY), PropertiesUtil.get(USERNAME_KEY), PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
