import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import me.sieric.webdriver.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WebTest {

    private ChromeDriver driver;
    private WebDriverWait wait;

    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final int TIMEOUT = 300;

    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
        driver.get(LOGIN_URL);
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void simpleTextTest() {
        testIssue(new Issue("kek", "lol"));
    }

    @Test
    void simpleNumbersTest() {
        testIssue(new Issue("123456", "123456"));
    }


    @Test
    void strangeSymbolsTest() {
        String strangeString = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890`~!@#$%^&*()-_=+\\|[{]};:',<.>/?";
        testIssue(new Issue(strangeString, strangeString));
    }

    @Test
    void russianSymbolsTest() {
        String string = "ёйцукенгшщзхъфывапролджэячсмитьбюЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
        testIssue(new Issue(string, string));
    }

    @Test
    void longTest() {
        String string = "Twas brillig, and the slithy toves\n" +
                "Did gyre and gimble in the wabe;\n" +
                "All mimsy were the borogoves,\n" +
                "And the mome raths outgrabe.\n" +
                "\n" +
                "Beware the Jabberwock, my son!\n" +
                "The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "The frumious Bandersnatch!\n" +
                "\n" +
                "He took his vorpal sword in hand:\n" +
                "Long time the manxome foe he sought —\n" +
                "So rested he by the Tumtum tree\n" +
                "And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "The Jabberwock, with eyes of flame,\n" +
                "Came wiffling through the tulgey wood,\n" +
                "And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through, and through\n" +
                "The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "He went galumphing back.\n" +
                "\n" +
                "And hast thou slain the Jabberwock?\n" +
                "Come to my arms, my beamish boy!\n" +
                "A frabjous day! Callooh! Callay!\n" +
                "He chortled in his joy.\n" +
                "\n" +
                "Twas brillig, and the slithy toves\n" +
                "Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "And the mome raths outgrabe.";
        testIssue(new Issue("keksik", string));
    }

    private void testIssue(Issue issue) {
        login();
        createNewIssue(issue);
//        openIssues();
        openLastIssue();
        Issue realIssue = getIssue();
        System.out.println(realIssue.getSummary());
        System.out.println(realIssue.getDescription());
        assertEquals(issue, realIssue);
    }

    private void testNotIssue(Issue issue) {
        login();
        createNewIssue(issue);
//        openIssues();
        openLastIssue();
        Issue realIssue = getIssue();
        assertNotEquals(issue, realIssue);
    }

    private void login() {
        final LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("root", "root");
    }

    private void createNewIssue(Issue issue) {
        final DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        dashboardPage.createIssue(wait);
        final NewIssuePage newIssuePage = PageFactory.initElements(driver, NewIssuePage.class);
        newIssuePage.createIssue(issue, wait);
    }

    private void openIssues() {
        final DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        dashboardPage.openIssues(wait);
    }

    private void openLastIssue() {
        final IssuesPage issuesPage = PageFactory.initElements(driver, IssuesPage.class);
        issuesPage.openLastIssue(wait);
    }

    private Issue getIssue() {
        final IssuePage issuePage = PageFactory.initElements(driver, IssuePage.class);
        return issuePage.getIssue(wait);
    }
}
