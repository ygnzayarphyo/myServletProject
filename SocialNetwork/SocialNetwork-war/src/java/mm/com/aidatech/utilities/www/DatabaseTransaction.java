/*
 * DatabaseConnection.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/*
 * This class is about implementation of all database operations that required in this project.
 */

/**
 *
 * @author Zayar
 */
public class DatabaseTransaction implements DbTransaction {

	Connection conn = null;

    /**
     *
     * @return
     */
    @SuppressWarnings("static-access")
	public Connection getconnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String host_address = new DatabaseLoader().getDbUrl();
			String db_name = "social"; // this is constant
			String db_username = new DatabaseLoader().getDbUserName();
			String db_password = new DatabaseLoader().getDbPassword();
			String db = "jdbc:mysql://" + host_address + "/" + db_name;

			conn = (Connection) DriverManager.getConnection(db, db_username,
					db_password);

		} catch (SQLException e) {
			System.out.println("SQLException error" + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

    /**
     *
     * @param userName
     * @param email
     * @param phone
     * @param dob
     * @param password
     * @return
     */
    public int insertUser(String userName, String email, String phone,
			String dob, String password) {
		int taskFinish = 00; // 00=not success, 10=record already exist. 11=save
								// success.
		try {
			String insertQuery = "INSERT INTO tbl_user(UserName,Email,Phone,DOB,ActivationStatus,Password)"
					+ " VALUES (?,?,?,?,?,?);";

			PreparedStatement preparedStmt = getconnection().prepareStatement(
					insertQuery);
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, dob);
			preparedStmt.setInt(5, 1);
			preparedStmt.setString(6, password);
			preparedStmt.execute();
			taskFinish = 11; // success;

			disconnect();

		} catch (SQLException ex) {
			taskFinish = 10;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return taskFinish;

	}

    /**
     *
     * @param session
     * @param email
     * @param password
     * @return
     */
    public int loginUser(HttpSession session, String email, String password) {
		int taskFinish = 00; // 00=not success, 01=no User Exist, 10=Not
								// Activated user, 11=Active UserName found,
		try {
			String selectQuery = "SELECT UserId,UserName,Email,Phone,DOB,ActivationStatus FROM tbl_user WHERE Email=\""
					+ email + "\" AND Password=\"" + password + "\";";
			Statement stmt = getconnection().createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			if (rs.next()) {
				String[] key = { "UserId", "UserName", "Email", "Phone", "DOB",
						"ActivationStatus" };
				String[] value = { "" + rs.getInt("UserId"),
						rs.getString("UserName"), rs.getString("Email"),
						rs.getString("Phone"), rs.getString("DOB"),
						rs.getString("ActivationStatus") };
				new WebSession().setSession(session, key, value);
				if (rs.getInt("ActivationStatus") == 1) {
					taskFinish = 11;
				} else if (rs.getInt("ActivationStatus") == 0) {
					taskFinish = 10;
				}
			} else {
				taskFinish = 01;
			}
			disconnect();

		} catch (SQLException ex) {
			taskFinish = 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return taskFinish;

	}

    /**
     *
     * @param session
     * @param flag
     * @return
     */
    public int updateActivationStatus(HttpSession session, int flag) {
		/*
		 * flag is used to determined activation or de-activation of account.
		 * flag=0 will change activation account to de-active account and also
		 * flag=1 will be vice visa.
		 */
		int taskFinish = 00; // 00=not success, 11=update success.
		try {
			String updateQuery = "UPDATE tbl_user SET ActivationStatus=? WHERE UserId=?;";
			PreparedStatement stmt = getconnection().prepareStatement(
					updateQuery);

			stmt.setInt(1, flag);
			stmt.setInt(2,
					Integer.valueOf(session.getAttribute("UserId").toString())); // Set
																					// User
																					// ID
																					// from
																					// current
																					// Session
																					// value.

			stmt.executeUpdate();

			taskFinish = 11;

			// change in session.
			String[] key = { "ActivationStatus" };
			String[] value = { "" + flag };
			new WebSession().setSession(session, key, value);

			disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskFinish;
	}

    /**
     *
     * @param session
     * @param title
     * @param message
     * @return
     */
    public int[] insertStatus(HttpSession session, String title, String message) {

		int taskFinish = 00; // 00=not success, 11=save success.
		int statusId = 0; // to Return status Id for Image uploading process.
		try {
			Calendar calendar = Calendar.getInstance();
			String timestamp = String.valueOf(new java.sql.Timestamp(calendar
					.getTimeInMillis()));
			String insertStatusQuery = "INSERT INTO tbl_status(UserId,Title,Status,PostDate)"
					+ " VALUES (?,?,?,?);";

			PreparedStatement preparedStmt = getconnection().prepareStatement(
					insertStatusQuery);
			preparedStmt.setInt(1,
					Integer.valueOf(session.getAttribute("UserId").toString()));
			preparedStmt.setString(2, title);
			preparedStmt.setString(3, message);
			preparedStmt.setString(4, timestamp);
			preparedStmt.execute();
			taskFinish = 11; // success;
			disconnect();
			// -----the end of insert statement------
			System.out.println(timestamp);
			String selectQuery = "SELECT StatusId FROM tbl_status WHERE PostDate='"
					+ timestamp + "';"; // only last status where user upload
										// with specific time.
			Statement stmt = getconnection().createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			if (rs.next()) {
				System.out.println(rs.getInt("StatusId"));
				statusId = rs.getInt("StatusId");
			} else {
				taskFinish = 01;
			}
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[] returnValue = { taskFinish, statusId };
		return returnValue;

	}

    /**
     *
     * @param limit
     * @param StatusCondition
     * @return
     */
    public ArrayList[][] getStatus(int limit, int StatusCondition) {

		try {
			String selectQuery = (StatusCondition == 0) ? "SELECT StatusId,UserId,Title,Status,PostDate FROM tbl_status ORDER BY StatusId DESC limit "
					+ limit + ";"
					: "SELECT StatusId,UserId,Title,Status,PostDate FROM tbl_status WHERE StatusId="
							+ StatusCondition + ";";
			Statement stmt = getconnection().createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			rs.last();
			limit = rs.getRow();
			rs.beforeFirst();
			ArrayList[][] values = new ArrayList[limit][5];
			/*
			 * reset limit value from count(*) to create array room. This is
			 * necesssary for fetching all values from table like show all.
			 */
			int index = 0;
			while (rs.next()) {
				for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
					values[index][columnIndex] = new ArrayList();
				}
				values[index][0].add("" + rs.getInt("StatusId"));
				values[index][1].add("" + rs.getInt("UserId"));
				values[index][2].add(rs.getString("Title"));
				values[index][3].add(rs.getString("Status"));
				values[index][4].add(rs.getString("PostDate"));

				index++;
			}
			disconnect();
			return values;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

    /**
     *
     * @param tableName
     * @param columnName
     * @param data
     * @return
     */
    public ArrayList[][] getList(String tableName, String[] columnName,
			String[] data) { // For analytics process.

		try {
			String selectQuery = "";
			if (tableName.equals("status")) {
				selectQuery = "SELECT Title,Status,PostDate FROM tbl_status WHERE ";
			} else {
				selectQuery = "SELECT Comment, CommentedDate FROM tbl_comment WHERE ";
			}
			for (int count = 0; count < (tableName.equalsIgnoreCase("comment") ? columnName.length - 1
					: columnName.length); count++) {
				// to reduce one column if comment table is accessed.
				if (!data[count].equals("null")) {
					selectQuery += ((count == 0) ? "" : " AND ")
							+ columnName[count] + " LIKE \'%"
							+ data[count].toString() + "%\' ";
					/*
					 * If count is zero, no condition need. In this case, AND
					 * condition is used because there might exists one column
					 * to request and all the other column will select all. Only
					 * intersect record will keep
					 */

				}
			}
			selectQuery += ";";
			System.out.println(selectQuery);

			Statement stmt = getconnection().createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			rs.last();
			int limit = rs.getRow();
			rs.beforeFirst();
			ArrayList[][] values = new ArrayList[limit][3];
			/*
			 * reset limit value from count(*) to create array room. This is
			 * necesssary for fetching all values from table like show all.
			 */
			int index = 0;
			while (rs.next()) {
				for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
					values[index][columnIndex] = new ArrayList();
				}
				values[index][0].add(rs.getString(columnName[0].toString()));
				values[index][1].add(rs.getString(columnName[1].toString()));
				values[index][2].add((tableName.equalsIgnoreCase("status") ? rs
						.getString(columnName[2].toString()) : ""));

				index++;
			}
			disconnect();
			return values;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

    /**
     *
     * @param session
     * @param statusId
     * @param userId
     * @param comment
     * @return
     */
    public int insertComment(HttpSession session, String statusId,
			String userId, String comment) {
		int taskFinish = 00; // 00=not success, 11=save success.
		try {
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(
					calendar.getTimeInMillis());
			String insertStatusQuery = "INSERT INTO tbl_comment(StatusId,UserId,Comment,CommentedDate)"
					+ " VALUES (?,?,?,?);";

			PreparedStatement preparedStmt = getconnection().prepareStatement(
					insertStatusQuery);
			preparedStmt.setInt(1, Integer.valueOf(statusId));
			preparedStmt.setInt(2, Integer.valueOf(userId));
			preparedStmt.setString(3, comment);
			preparedStmt.setString(4, String.valueOf(timestamp));
			preparedStmt.execute();
			taskFinish = 11; // success;

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return taskFinish;
	}

    /**
     *
     * @param statusId
     * @return
     */
    public ArrayList[][] getComment(String statusId) {
		try {
			String selectQuery = "SELECT cmt.CommentId,cmt.UserId,cmt.Comment,cmt.CommentedDate,usr.UserName FROM tbl_comment cmt, tbl_user usr where cmt.StatusId="
					+ statusId
					+ " and usr.UserId=cmt.UserId ORDER BY cmt.CommentId DESC";

			Statement stmt = getconnection().createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			rs.last();
			int limit = rs.getRow();
			rs.beforeFirst();
			ArrayList[][] values = new ArrayList[limit][5]; // no status id
															// count back.

			int index = 0;
			while (rs.next()) {
				for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
					values[index][columnIndex] = new ArrayList();
				}
				values[index][0].add("" + rs.getInt("CommentId"));
				values[index][1].add(rs.getString("UserId"));
				values[index][2].add(rs.getString("Comment"));
				values[index][3].add("" + rs.getString("CommentedDate"));
				values[index][4].add(rs.getString("UserName"));
				index++;
			}
			disconnect();
			return values;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

    /**
     *
     * @param tableName
     * @param columnId
     * @param dataId
     * @return
     */
    public int deleteProcess(String tableName, String columnId, int dataId) {
		int taskFinish = 00; // 00=not success, 11=save success.
		try {
			String query = "DELETE FROM " + tableName + " WHERE " + columnId
					+ "=?";
			PreparedStatement preparedStmt = getconnection().prepareStatement(
					query);
			preparedStmt.setInt(1, dataId);
			preparedStmt.execute();

			taskFinish = 11;

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskFinish;
	}

	private void disconnect() throws SQLException {
		conn.close();
	}
}
