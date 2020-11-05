package jdbc;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.testng.Assert.*;

public class UserInfoRepositoryTest {

    private static Properties prop=new Properties();

    @BeforeClass
    public static void beforeClass() {
        try (InputStream input = new FileInputStream("src/test/resources/forProfile/testDataUserInfo.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testGetInfoByEmail() {
        String str=UserInfoRepository.getColumnByEmail(prop.getProperty("email"),"Email");
        assertNotNull(str);
    }

}