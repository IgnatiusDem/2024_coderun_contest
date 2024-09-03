package demignatius.coderun.largestsquare;

import demignatius.coderun.util.TestLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

class LargestSquareTest {

    @Test
    void findLargestSquareTest() throws Exception {
        // Загрузка тестов
        String directory = "src/test/resources/demignatius/coderun/largestsquare/tests/";
        TestLoader testLoader = new TestLoader();
        testLoader.loadTestsFromFile(directory);
        List<String> tests = testLoader.getTests();

        // Загрузка ответов
        directory = "src/test/resources/demignatius/coderun/largestsquare/answers/";
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
            System.out.print(tests.get(i) + "\nresult:\n");

            LargestSquare.findLargestSquare();
            System.out.println();

            Assertions.assertEquals(answers.get(i), LargestSquare.getLastResult());
        }
    }
}