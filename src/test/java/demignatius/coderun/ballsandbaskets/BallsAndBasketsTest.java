package demignatius.coderun.ballsandbaskets;

import demignatius.coderun.util.TestLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;

class BallsAndBasketsTest {

    @org.junit.jupiter.api.Test
    void findMethodsOfSelection() throws IOException {
        // Загрузка тестов
        String directory = "src/test/resources/demignatius/coderun/ballsandbaskets/tests/";
        TestLoader testLoader = new TestLoader();
        testLoader.loadTestsFromFile(directory);
        List<String> tests = testLoader.getTests();

        // Загрузка ответов
        directory = "src/test/resources/demignatius/coderun/ballsandbaskets/answers/";
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

            BallsAndBaskets.findMethodsOfSelection();


            Assertions.assertEquals(answers.get(i), BallsAndBaskets.getLastResult());
        }
    }
}