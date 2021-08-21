package com.home.example.dbjunittest.dao;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.dbcp.BasicDataSource;

public abstract class AbstractDBTest {
    private static String DIR = "./src/test/resources/h2/";
    private static String DATABASE = "db";
//    public static String URL = "jdbc:h2:file:" + DIR + DATABASE + ";PASSWORD=SA;USER=SA;MODE=Oracle";
    public static String URL = "jdbc:h2:mem:test:sample;DB_CLOSE_ON_EXIT=FALSE;INIT=RUNSCRIPT FROM 'classpath:myscript.sql';";
    public static String JNDI_DS = "datasource/maestras";

    public static void destroy() throws NamingException, SQLException {
//	DeleteDbFiles.execute(DIR, DATABASE, false);
//	InitialContext initContext = new InitialContext();
//	initContext.unbind("java:comp/env/jdbc/myds");

	InitialContext initContext = new InitialContext();
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl(URL);
	initContext.bind("java:comp/env/datasource/maestras", dataSource);
    }

    public static void setup() throws NamingException, SQLException {
	InitialContext initContext = new InitialContext();
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl(URL);
//	dataSource.setUrl("jdbc:h2:mem:test:sample;DB_CLOSE_ON_EXIT=FALSE;");
	initContext.bind("java:comp/env/jdbc/myds", dataSource);
    }

}
