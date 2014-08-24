package com.motorcloudmusic.bass;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.motorcloudmusic.bass.util.FormUtils;
import com.motorcloudmusic.bass.util.MessageFactory;

/**
 * Servlet implementation class RegisterBassPlayer
 */
@WebServlet("/enter")
public class RegisterBassPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterBassPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		request.getRequestDispatcher("/enter.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Boolean noFieldsEntered = new Boolean(true);
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("emailaddress");
		String twitterId = request.getParameter("twitterid");
		String stageName = request.getParameter("sname");
		String catchPhrase = request.getParameter("cphrase");
		String miniBio = request.getParameter("minibio");

		MessageFactory m = new MessageFactory();

		// Check that all fields have been filled in
		checkRequiredField(firstName, "First Name", noFieldsEntered, m);
		checkRequiredField(lastName, "Last Name", noFieldsEntered, m);
		checkRequiredField(email, "Email address", noFieldsEntered, m);
		checkRequiredField(stageName, "Stage Name", noFieldsEntered, m);
		checkRequiredField(catchPhrase, "Catch Phrase", noFieldsEntered, m);
		checkRequiredField(miniBio, "Mini Bio", noFieldsEntered, m);
//		checkRequiredField(twitterId, "Twitter ID", noFieldsEntered, m);

		// Certain fields get a special exception since we don't want to REQUIRE twitter
		if(twitterId ==null||twitterId.length()==0){
			twitterId = new String(" ");
		}
		
		// Check that all fields are of proper length (or shorter)
		checkFieldLength(firstName, "First Name", 100, m);
		checkFieldLength(lastName, "Last Name", 100, m);
		checkFieldLength(email, "Email Address", 100, m);
		checkFieldLength(twitterId, "Twitter ID", 100, m);
		checkFieldLength(stageName, "Stage Name", 100, m);
		checkFieldLength(catchPhrase, "Catch Phrase", 100, m);
		checkFieldLength(miniBio, "Mini Bio", 500, m);

		if (m.isEmpty()) {

			try {
				// create a mysql database connection
				Class.forName("com.mysql.jdbc.Driver");

				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/PlayerList");
				Connection conn = ds.getConnection();

				// the mysql insert statement
				String query = " insert into players (first_name, last_name, email_id, twitter_id, stage_name,catch_phrase,mini_bio)"
						+ " values (?, ?, ?, ?, ?,?,?)";

				// create the mysql insert preparedstatement
				java.sql.PreparedStatement preparedStmt = conn
						.prepareStatement(query);
				preparedStmt.setString(1, firstName.trim());
				preparedStmt.setString(2, lastName.trim());
				preparedStmt.setString(3, email.trim());
				preparedStmt.setString(4, twitterId.trim());
				preparedStmt.setString(5, stageName.trim());
				preparedStmt.setString(6, catchPhrase.trim());
				preparedStmt.setString(7, miniBio.trim());

				// execute the preparedstatement
				preparedStmt.execute();

				conn.close();
			} catch (Exception e) {
				System.err.println("Database went BOOM!  Lookie here (below):" + e);
				System.err.println(e.getMessage());
			}

			request.getRequestDispatcher("/list.jsp")
					.include(request, response);
		} else {
			request.setAttribute("message", m.getMessages());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/index.jsp");
			rd.forward(request, response);

		}

	}


	private void checkFieldLength(String field, String fieldLabel, int maxLength, MessageFactory m) {
		if (!FormUtils.isAbsent(field)) {
			if(field.trim().length()>=maxLength){
				m.addMessage(fieldLabel + " can only be up to "+maxLength+" characters.");
			}
		} 
	}

	private void checkRequiredField(String field, String label,
			Boolean noFieldsEntered, MessageFactory m) {
		if (FormUtils.isAbsent(field)) {
			m.addMessage(label + " required.");
		} else {
			noFieldsEntered = new Boolean(true);
		}
	}

}
