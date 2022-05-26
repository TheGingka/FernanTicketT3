package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOManager {
    private Connection conn;
        private final String URL;
        private final String USER;
        private final String PASS;
        private static DAOManager singleton;

        private DAOManager() {
            this.conn = null;
            this.URL = "jdbc:mysql://127.0.0.1:3308/fernanticket?serverTimezone=UTC";
            this.USER = "root";
            this.PASS = "root";
        }

        public static DAOManager getSingletonInstance() {
            if (singleton == null){
                singleton = new DAOManager();
                return singleton;
            }else return singleton;
        }

        public Connection getConn(){return conn;}

        public void open() throws Exception{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL,USER,PASS);
            }catch (Exception e){
                throw e;
            }
        }

        public void close() throws Exception {
            try{
                if (this.conn != null)this.conn.close();
            }catch (Exception e){
                throw e;
            }
        }
}
