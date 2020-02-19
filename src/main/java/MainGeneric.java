package main.java;

import main.java.dao.Database;

import java.sql.*;

public class MainGeneric {
    private Connection dbConnection;

    public MainGeneric() throws SQLException {
        dbConnection = Database.getInstance().getConnection();
    }

    public static void main(String[] args) throws SQLException {
        MainGeneric main = new MainGeneric();
        main.genericDemo();
    }

    /**
     * Werken met de database via het ResultSetMetaData Object.
     * Deze methode kan je gebruiken om een onbekende database te lezen.
     * @throws SQLException
     */
    private void genericDemo() throws SQLException {
        String dbName = "multilayer";
        DatabaseMetaData meta = dbConnection.getMetaData();
        ResultSet rs = meta.getTables(dbName, null, null, new String[] {"TABLE"});
        System.out.println("All tables in " + dbName + " :");
        while (rs.next()) {
            String tblName = rs.getString("TABLE_NAME");
            showTable(tblName);
        }
    }

    private void showTable(String tblName) throws SQLException {
        String sqlSelect = "SELECT * FROM " + tblName;
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(sqlSelect);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        System.out.println("Table : " + tblName);
        showMetaData(rsMetaData);
        showData(rsMetaData, rs);
    }

    private void showMetaData(ResultSetMetaData rsMetaData) throws SQLException {
        String lijn = "%10s : %10s : %10s\n";
        System.out.printf(lijn, "ColName", "Type", "Nullable");
        for(int colNr = 1; colNr <= rsMetaData.getColumnCount(); colNr++) {
            String colName = rsMetaData.getColumnName(colNr);
            String colType = rsMetaData.getColumnTypeName(colNr);
            int isNullable = rsMetaData.isNullable(colNr);
            System.out.printf(lijn, colName, colType, isNullable);
        }
        System.out.println();
    }

    private void showData(ResultSetMetaData rsMetaData, ResultSet rs) throws SQLException {
        String lijn   = "";
        String format = "";
        for (int colNr = 1; colNr <= rsMetaData.getColumnCount(); colNr++) {
            String colName = rsMetaData.getColumnName(colNr);
            lijn = lijn + String.format("%-10s: ", colName);
        }
        System.out.println(lijn);
        rs.beforeFirst();
        while (rs.next()) {
            lijn = "";
            for (int colNr = 1; colNr <= rsMetaData.getColumnCount(); colNr++) {
                int colType = rsMetaData.getColumnType(colNr);
                lijn = lijn + format(rs.getObject(colNr), colType);
            }
            System.out.println(lijn);
        }
    }

    private String format(Object data, int dataType) {
        switch (dataType) {
            case Types.CHAR:
            case Types.VARCHAR:
                return String.format("%10s: ", data);
            case Types.DATE:
                return String.format("%td/%tm/%ty  : ", data);
            case Types.INTEGER:
                return String.format("%10d: ", data);
            case Types.FLOAT:
                return String.format("%10.2f: ", data);
            default:
                return String.format("%10b: ", data);
        }
    }
}
