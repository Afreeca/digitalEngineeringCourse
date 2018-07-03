package com.afreeca.course.IntegrationTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.hateoas.Resource;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.afreeca.course.user.User;
import com.afreeca.course.user.userController;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;

public class userControllerIntegrationTest 
{/*
	@BeforeClass 
	public static void initializeDB() throws IOException 
	{
		MongodConfig mongodConfig = new MongodConfig(Version.Main.V2_2,PORT, false);
		RuntimeConfig config = new RuntimeConfig();
		MongodStarter starter = MongodStarter.getInstance(config);
		
		config.setExecutableNaming(new UserTempNaming());
		mongoExecutable = starter.prepare(mongodConfig);
		
		mongoProcess = mongoExecutable.start();

		mongo = new MongoClient(LOCALHOST, PORT);
		mongo.getDB(DB_NAME);
	}
	
	
	@AfterClass
    public static void shutdownDB() throws InterruptedException 
	{
        mongo.close();
        mongoProcess.stop();
    }


    @BeforeMethod
    public void setUp() throws Exception 
    {
    	uController = new userController();
    	
        template = new MongoTemplate(mongo, DB_NAME);
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    	 template.dropCollection(User.class);
    }
    
  
    @Test
    public void getSaveTest()
    {
    	User user = new User("Adilson", "Mendes");
    	user.setId("1");
    	
    	uController.saveUser(user);
    	
    	int samplesInCollection = template.findAll(User.class).size();

    	assertEquals(samplesInCollection, 1, samplesInCollection);	
    }
    
    @Test
    public void getUsersTest()
    {
        List<User> userList = Arrays.asList(
                new User("1", "Marie Van", "Brown"), new User("3", "Dorothy", "Vaughan"),
                new User("2", "Edward", "Bouchet"), new User("4", "Howard", "Turman"));
        
        for (User user : userList) 
        {
            template.save(user);
        }
        
        List<User> users = uController.getUsers();
        
        assertEquals(userList, users);
    	
    }
    
    @Test
    public void getUserByIdTest()
    {

        List<User> userList = Arrays.asList(
                new User("1", "Lewis", " Howard Latimer"), new User("3", "Sigmund", "Freud"),
                new User("2", "Friedrich", "Nietzsche"), new User("4", "Ben", "Montgomery"));
        
        for (User user : userList) 
        {
            template.save(user);
        }
        
        Resource<User> expectedUser  = new Resource<User>(new User("1", "Lewis", " Howard Latimer"));
        Resource<User> user = uController.getUser("3");
        
        assertEquals(expectedUser, user);
    	
    }*/

}
