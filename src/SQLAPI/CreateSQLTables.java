package SQLAPI;

import utilities.Debug;

import java.sql.SQLException;

public class CreateSQLTables {

    //    StatUtils utils = new StatUtils();
    private String data;

    public String getTableQuery() {
        return data;
    }

    public void setQuery(String value) {
        data = value;
    }

    private SQL val;
    private String query;

    public CreateSQLTables() {
    }

    public CreateSQLTables(SQL instance, String sql) {
        val = instance;
        query = sql;
        try {
            val.getConnection().prepareStatement(sql).executeUpdate();
            // logToConsole("&bSQL table has been created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param val      Takes a SQLData connection parameter.
     * @param instance Checks to see if the table should be created via a config.
     * @param sql      Parameters you wish to define for table.
     */
    @Deprecated
    public void createTable(SQL val, boolean instance, String tableName, String sql) {
        if (instance) {
            try {
                String values = sql;
                values = values.replace("%tablename%", tableName);

                val.getConnection().prepareStatement(values).executeUpdate();
                Debug.log("&bSQL table has been created!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param value Takes SQLData connection parameter.
     * @param sql   SQLData syntax.
     *              <p>
     *              Overridden method to create table with out a boolean value.
     */
    public void createTable(SQL value, String sql) {
        try {
            value.getConnection().prepareStatement(sql).executeUpdate();
            // logToConsole("&bSQL table has been created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}