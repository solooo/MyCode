package solooo.mycode.hive;

import java.sql.*;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:10/31-031
 * History:
 * his1:
 */
public class HiveJdbcTest {

    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    private String connectionUrl = "jdbc:hive2://192.168.1.247:10000/default";

    public void createTable() throws ClassNotFoundException, SQLException {
        String createSql = "create table testTable(c1 string, c2 string, c3 string)";
        Class.forName(DRIVER_NAME);
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void jobTest() throws ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             Statement stmt = conn.createStatement()) {
            String querySql = "select c1 from test";
            System.out.println("running:" + querySql);
            ResultSet result = stmt.executeQuery(querySql);
            while(result.next()) {
                System.out.println(result.getString(1));
            }

            String countSql = "select count(*) from test";
            System.out.println("running:" + countSql);
            ResultSet res = stmt.executeQuery(countSql);
            while(res.next()) {
                System.out.println(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new HiveJdbcTest().jobTest();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
