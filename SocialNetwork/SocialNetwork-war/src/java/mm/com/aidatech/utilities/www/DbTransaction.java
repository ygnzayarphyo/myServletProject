/*
 * DbTransaction.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * This interface describe about the database processes of DatabaseTransaction
 * class.
 */
public interface DbTransaction {

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
			String dob, String password);

	// insert new User to the tbl_user database.

    /**
     *
     * @param session
     * @param email
     * @param password
     * @return
     */
    public int loginUser(HttpSession session, String email, String password);

	// check if the user is exist or not from tbl_user database and if user
	// exist, add this user to the session.

    /**
     *
     * @param session
     * @param flag
     * @return
     */
    public int updateActivationStatus(HttpSession session, int flag);

	// Changes of Activation Status based on user action.

    /**
     *
     * @param session
     * @param title
     * @param message
     * @return
     */
    public int[] insertStatus(HttpSession session, String title, String message);

	// This is a new status insertion function to the database.

    /**
     *
     * @param limit
     * @param StatusCondition
     * @return
     */
    public ArrayList[][] getStatus(int limit, int StatusCondition);

	// This will fetch status from the tbl_status database based on condition
	// and limit of staus. Default limit fetch from web application is 6;

    /**
     *
     * @param tableName
     * @param columnName
     * @param data
     * @return
     */
    public ArrayList[][] getList(String tableName, String[] columnName,
			String[] data);

	// This function is mainly for analytics processes for status and comment
	// tables.

    /**
     *
     * @param session
     * @param statusId
     * @param userId
     * @param comment
     * @return
     */
    public int insertComment(HttpSession session, String statusId,
			String userId, String comment);

	// This is a new comment insertion function to the database.

    /**
     *
     * @param statusId
     * @return
     */
    public ArrayList[][] getComment(String statusId);

	// Fetch the comment from the tbl_comment table that match with statusId.

    /**
     *
     * @param tableName
     * @param columnId
     * @param dataId
     * @return
     */
    public int deleteProcess(String tableName, String columnId, int dataId);
	// Delete the comment or status.
}
