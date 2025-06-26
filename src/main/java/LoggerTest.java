import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

    // Create a logger for this class using SLF4J
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {

        logger.info(" This is an INFO log");
        logger.warn(" This is a WARNING log");
        logger.error(" This is an ERROR log");
        logger.debug(" This is a DEBUG log");  // Only appears if rootLogger is set to DEBUG

        System.out.println(" Logger ran successfully!");
    }
}
