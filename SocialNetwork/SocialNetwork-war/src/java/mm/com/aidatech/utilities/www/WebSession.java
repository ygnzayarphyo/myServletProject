/*
 * WebSession.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * Custom Plugin for Web session.
 */
public class WebSession {

    /**
     *
     * @param session
     * @param key
     * @param value
     */
    public void setSession(HttpSession session, String[] key, String[] value) {
		for (int count = 0; count < key.length; count++) {
			session.setAttribute(key[count], value[count]);
		}
	}

    /**
     *
     * @param request
     */
    public void destroySession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("Session Destroy Success.");
	}
}
