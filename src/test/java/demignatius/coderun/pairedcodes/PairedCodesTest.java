package demignatius.coderun.pairedcodes;

import demignatius.coderun.util.TestLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.util.List;

class PairedCodesTest {

    @Test
    void solutionTest() throws Exception{
        // Загрузка тестов
        String directory = "src/test/resources/demignatius/coderun/pairedcodes/tests/";
        TestLoader testLoader = new TestLoader();
        testLoader.loadTestsFromFile(directory);
        List<String> tests = testLoader.getTests();

        // Загрузка ответов
        directory = "src/test/resources/demignatius/coderun/pairedcodes/answers/";
        testLoader.loadAnswersFromFile(directory);
        List<String> answers = testLoader.getAnswers();

        if (tests.size() != answers.size()) {
            System.out.println("Check test files");
            Assertions.assertEquals(tests.size(), answers.size());
        }

        // Прохождение тестов
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("Test #" + (i + 1));
            ByteArrayInputStream s = new ByteArrayInputStream(tests.get(i).getBytes());
            System.setIn(s);
            System.out.print(tests.get(i) + "\nresult: ");

            PairedCodes.solution();

            Assertions.assertEquals(answers.get(i), PairedCodes.getLastResult());
        }
    }
}