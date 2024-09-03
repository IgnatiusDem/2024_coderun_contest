package demignatius.coderun.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestLoader {
    private List<String> tests = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private Path directoryTestsPath;
    private Path directoryAnswersPath;


    public void setDirectoryTestsPath(String directory) {
        Path newDirectory = Path.of(directory);
        if(Files.isDirectory(newDirectory)){
            this.directoryTestsPath = newDirectory;
            return;
        }
        System.out.println(newDirectory.toAbsolutePath());
        throw new RuntimeException("This is not directory");
    }

    public void setDirectoryAnswersPath(String directory) {
        Path newDirectory = Path.of(directory);
        if(Files.isDirectory(newDirectory)){
            this.directoryAnswersPath = newDirectory;
            return;
        }
        System.out.println(newDirectory.toAbsolutePath());
        throw new RuntimeException("This is not directory");
    }

    private void loadFromFile(Path directoryPath, List<String> content){
        try (Stream<Path> paths =  Files.walk(directoryPath)){
            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    List<String> in = Files.readAllLines(path);
                    String test = String.join("\n", in);
                    content.add(test);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTestsFromFile(String directory){
        setDirectoryTestsPath(directory);
        loadFromFile(this.directoryTestsPath, this.tests);
    }

    public void loadAnswersFromFile(String directory){
        setDirectoryAnswersPath(directory);
        loadFromFile(this.directoryAnswersPath, this.answers);
    }

    public List<String> getTests() {
        return tests;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
