package moviePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("moviePackage")
public class ConfigClass {
	@Bean
	public Connection conn() throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/theatreschema";
		String uname = "root";
		String pwd = "Myroot@2114";
		Connection con = DriverManager.getConnection(url, uname, pwd);
		return con;
	}

}
