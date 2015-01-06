package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Phone;
import model.Subscriber;

public class DBHelper {

	private Connection connection;

	public DBHelper() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager
					.getConnection("jdbc:oracle:thin:sasha/frbvtyrj@localhost:1521:xe");
			System.out.println("Connected to db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Conntection failed cause" + e.getStackTrace());
			throw new RuntimeException(e);
		}
	}
	public void executeStatement(String query) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (SQLException e) {
			System.out.println("Failed to execute query: " + "query"
					+ " cause of:\n" + e.getStackTrace());
			throw new RuntimeException(e);
		}
	}

	public <T> T executeSelect(String query, String stratetdgy) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			T result;
			switch (stratetdgy) {
				case "getSubscribers" :
					result = (T) subscribersStratetgy(resultSet);
					break;
				case "getPhonesBySubscriber" :
					result = (T) getPhonesBySubscriber(resultSet);
					break;
				case "getSubscriber" :
					result = (T) getSubscriber(resultSet);
					break;
				case "getPhone" :
					result = (T) getPhone(resultSet);
					break;
				case "getPhones" :
					result = (T) getPhones(resultSet);
					break;
				default :
					result = null;
			}

			statement.close();
			return result;
		} catch (SQLException e) {
			System.out.println("Failed to execute query: " + "query"
					+ " cause of:\n" + e.getStackTrace());
			throw new RuntimeException(e);
		}
	}

	private List<Phone> getPhones(ResultSet resultSet) {
		List<Phone> res = new ArrayList<Phone>();
		try {
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				double balance = resultSet.getDouble("balance");
				double hours = resultSet.getDouble("hours");
				long sId = resultSet.getLong("id_Subscriber");
				res.add(new Phone(id, balance, hours, sId));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	private Phone getPhone(ResultSet resultSet) {
		Phone res = null;
		try {
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				double balance = resultSet.getDouble("balance");
				double hours = resultSet.getDouble("hours");
				long sId = resultSet.getLong("id_Subscriber");
				res = new Phone(id, balance, hours, sId);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to close connection");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.closeConnection();
	}
	private Subscriber getSubscriber(ResultSet resultSet) {
		Subscriber res = null;
		try {
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String passport = resultSet.getString("passport");
				String fio = resultSet.getString("fio");
				String address = resultSet.getString("address");
				String birthday = resultSet.getString("birthday");
				res = new Subscriber(id, passport, fio, address, birthday);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}
	public List<Subscriber> subscribersStratetgy(ResultSet resultSet) {
		List<Subscriber> res = new ArrayList<Subscriber>();
		try {
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String passport = resultSet.getString("passport");
				String fio = resultSet.getString("fio");
				String address = resultSet.getString("address");
				String birthday = resultSet.getString("birthday");
				Subscriber subs = new Subscriber(id, passport, fio, address,
						birthday);
				// subs.setPhoneList(getPhonesBySubscriber(id));
				res.add(subs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Long> getPhonesBySubscriber(ResultSet resultSet) {
		List<Long> res = new ArrayList<Long>();

		try {
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				res.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	};

}
