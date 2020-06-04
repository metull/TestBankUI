package Check;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class Check {

    public static void CheckValue(String expected, String actual) {
        assertThat(expected).isEqualTo(actual);
    }

    public static void CheckValueOnEquals(String first, String second) {
        double sum1 = Double.parseDouble(first.replace(",", "."));
        double sum2 = Double.parseDouble(second.replace(",", "."));
        assertTrue(sum1 < sum2);
    }
}
