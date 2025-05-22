package lesson13;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DBTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try(final Connection	c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "user")) {
			System.err.println("Connected");
			DatabaseMetaData md = c.getMetaData();
			
			try(final PreparedStatement	ps = c.prepareStatement("insert into x(y,z) values (?,?)")) {
				ParameterMetaData md = ps.getParameterMetaData();
				ps.setInt(1, 10);
				ps.setString(2, "slfgldfg");
				ps.executeUpdate();
				ps.setInt(1, 30);
				ps.setString(2, "fglf;ld");
				ps.executeUpdate();
			}
			
			try(final Statement	s = c.createStatement()) {
//				s.executeUpdate("create table x(y integer,z text)");
				s.executeUpdate("insert into x(y,z) values (10,'sss')");
//				s.addBatch("");
//				s.executeBatch();
//				s.clearBatch();
				try(final ResultSet	rs = s.executeQuery("select * from x")) {
					final ResultSetMetaData	rsmd = rs.getMetaData();
					
					System.err.println("number of columns="+rsmd.getColumnCount());
					for (int index = 1; index <= rsmd.getColumnCount(); index++) {
						System.err.println("Name="+rsmd.getColumnName(index));
						System.err.println("Type="+rsmd.getColumnTypeName(index));
					}

					while (rs.next()) {
						for (int index = 1; index <= rsmd.getColumnCount(); index++) {
							System.err.print(rsmd.getColumnName(index)+"="+rs.getObject(index));
						}
						System.err.println();
						System.err.println("y="+rs.getInt("y")+",z="+rs.getString("z"));
					}
				}
			}
		}
	}

}
