package moviePackage;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) throws SQLException {
		String location;
		String movie;
		String theatre;
		String timings;
		String category;
		int availableseatsincategory;
		int seatswanttobook = 0;
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
		MovieJDBCConnection obj = context.getBean("movieJDBCConnection",MovieJDBCConnection.class);
		location= obj.availableMovies();
		if (location == null)
		{
			System.out.println("No movies at your location");
		}
		movie = obj.availabletheatre(location);
		theatre = obj.availabletimings(movie, location);
		timings = obj.seatsandpriceinformation(theatre);
		category = obj.categoryselection(theatre,timings);
		availableseatsincategory = obj.checkingseatsavailability(category,theatre,timings);
		if (availableseatsincategory > 0)
		{
			seatswanttobook = obj.availableseatsupdate(availableseatsincategory,theatre,timings);
		}
		if (seatswanttobook != 0)
		{
			obj.bookingseats(availableseatsincategory-seatswanttobook,category,theatre,timings);
		}

}
}
