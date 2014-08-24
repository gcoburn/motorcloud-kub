package com.motorcloudmusic.bass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.motorcloudmusic.bass.util.FormUtils;

/**
 * Servlet implementation class RegisterBassPlayer
 */
@WebServlet("/mobilefeed")
public class RegisterBassPlayerMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterBassPlayerMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;

		ArrayList<BassPlayer> players = new ArrayList<BassPlayer>();

		try {
			// create a mysql database connection
			Class.forName("com.mysql.jdbc.Driver");
			Context ctx = new InitialContext();
			Context envctx = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/PlayerList");
			conn = ds.getConnection();

			// Get ALL bass players
			sql = "Select * from players";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BassPlayer player = new BassPlayer();
				player.setCatchPhrase(scrubString(rs.getString("catch_phrase")));
				player.setEmail(scrubString(rs.getString("email_id")));
				player.setFirstName(scrubString(rs.getString("first_name")));
				player.setLastName(scrubString(rs.getString("last_name")));
				player.setMiniBio(scrubString(rs.getString("mini_bio")));
				player.setStageName(scrubString(rs.getString("stage_name")));
				player.setTwitterId(scrubString(rs.getString("twitter_id")));
				players.add(player);
			}

			rs.close();
			stmt.close();
			stmt = null;

		} catch (Exception e) {
			System.err.println("Database went BOOM!  Lookie here (below):" + e);
			System.err.println(e.getMessage());
		}

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		Gson gson = new Gson();
		out.println(gson.toJson(players));
		out.close();

	}

	private String scrubString(String value) {
		if (FormUtils.isAbsent(value)) {
			return "";
		} else {
			return value.trim();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
