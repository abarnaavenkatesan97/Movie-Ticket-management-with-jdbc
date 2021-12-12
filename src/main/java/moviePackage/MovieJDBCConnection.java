package moviePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieJDBCConnection
{
	
	@Autowired
	private Connection con;
	public void bookingseats(int seatswanttobook,String category,String theatre,String timings) throws SQLException
	{
		String sqllquery = "update seats set availableseats = ? where (category = ? and theatrename = ? and timings = ?)";
		PreparedStatement prstmt = con.prepareStatement(sqllquery);
		prstmt.setInt(1, seatswanttobook);
		prstmt.setString(2, category);
		prstmt.setString(3, theatre);
		prstmt.setString(4, timings);
		prstmt.executeUpdate();
		System.out.println("Booking successfull");
	}
	public int availableseatsupdate(int availableseatsincategory,String theatre,String timings) throws SQLException
	{
		int seatswanttobook;
		System.out.println("How many seats do you want to book?");
		Scanner sc = new Scanner(System.in);
		seatswanttobook = sc.nextInt();
		if(availableseatsincategory >= seatswanttobook)
		{
			return seatswanttobook;
		}
		else
		{
			System.out.println("Housefull");
			return 0;
		}
	}
	public int checkingseatsavailability(String category,String theatre,String timings) throws SQLException
	{
		int availableseatsincategory = 0;
		String sqlcheckingseatsavailability = "select availableseats from seats where (theatrename = ? and timings = ? and category = ?)";
		PreparedStatement pstatement = con.prepareStatement(sqlcheckingseatsavailability);
		pstatement.setString(1, theatre);
		pstatement.setString(2, timings);
		pstatement.setString(3, category);
		ResultSet rssets = pstatement.executeQuery();
		while(rssets.next())
		{
			availableseatsincategory = rssets.getInt(1);
		}
		return availableseatsincategory;
	}
	public String availableMovies() throws SQLException
	{
		System.out.println("Please enter the location in which you want to watch the movie");
		Scanner sc = new Scanner(System.in);
		String location = sc.nextLine();
		String sql = "select * from movie where location = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1,location);
		ResultSet rs = stmt.executeQuery();
		
		System.out.println("Please find the available movies at your location ");
		while(rs.next())
		{
			System.out.println(rs.getString("moviename"));
		}
	
		return location;
	}
	
	public String availabletheatre(String location) throws SQLException
	{
		System.out.println("Please select a movie and we will show the theatres");
		Scanner sc = new Scanner(System.in);
		String movie = sc.nextLine();
		String sqlformovie = "select distinct theatrename from theatre where (moviename = ? and theatrelocation = ?)";
		PreparedStatement stmtformovie = con.prepareStatement(sqlformovie);
		stmtformovie.setString(1, movie);
		stmtformovie.setString(2, location);
		ResultSet rsformovie = stmtformovie.executeQuery();
		while(rsformovie.next())
		{
			System.out.println(rsformovie.getString("theatrename"));
		}
		return movie;
	}
	public String availabletimings(String movie,String location) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select a theatre");
		String theatre = sc.nextLine();
		System.out.println("These are the show timings for the " + movie + " movie at " + location);
		String sqlfortheatre = "select timings from theatre where (theatrename = ? and moviename = ?)";
		PreparedStatement stmtfortheatre = con.prepareStatement(sqlfortheatre);
		stmtfortheatre.setString(1, theatre);
		stmtfortheatre.setString(2, movie);
		ResultSet rsfortheatre = stmtfortheatre.executeQuery();
		while(rsfortheatre.next())
		{
			System.out.println(rsfortheatre.getString("timings"));
		}
		return theatre;
		
	}
	public String seatsandpriceinformation(String theatre) throws SQLException
	{
		System.out.println("Please select one from the above");
		Scanner scn = new Scanner(System.in);
		String timings = scn.nextLine();
		System.out.println("Please find the price details for the respective classes");
		String query = "select * from seats where theatrename = ? and timings = ?";
		PreparedStatement stmts = con.prepareStatement(query);
		stmts.setString(1, theatre);
		stmts.setString(2, timings);
		ResultSet rsset = stmts.executeQuery();
		while(rsset.next())
		{
			System.out.println(rsset.getString(3) + "-->" + rsset.getInt(4));
		}
		return timings;
		}
	public String categoryselection(String theatre,String timings)
	{
		System.out.println("Please select a class to proceed");
		Scanner scn = new Scanner(System.in);
		String category = scn.nextLine();
		return category;
	}
		
	}
	

		
		

