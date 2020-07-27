package org.simpleyaml.configuration.file;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.cactoos.io.TempFile;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.simpleyaml.exceptions.InvalidConfigurationException;

class YamlFileTest {

    private static String getResource(final String file) {
        return Objects.requireNonNull(YamlFileTest.class.getClassLoader().getResource(file)).getPath();
    }

    @Test
    void load() {

    }

    @Test
    void loadWithComments() {

    }

    @Test
    void createOrLoad() {

    }

    @Test
    void createOrLoadWithComments() {

    }

    @Test
    void save() {

    }

    @Test
    void saveWithComments() {

    }

    @Test
    void fileToString() throws Exception {
        final YamlFile yamlFile = new YamlFile(YamlFileTest.getResource("test.yml"));
        final String content = "test:\n" +
            "  number: 5\n" +
            "  string: Hello world\n" +
            "  boolean: true\n" +
            "  list:\n" +
            "  - Each\n" +
            "  - word\n" +
            "  - will\n" +
            "  - be\n" +
            "  - in\n" +
            "  - a\n" +
            "  - separated\n" +
            "  - entry\n" +
            "math:\n" +
            "  pi: 3.141592653589793\n" +
            "timestamp:\n" +
            "  canonicalDate: 2020-07-04T13:18:04.458Z\n" +
            "  formattedDate: 04/07/2020 15:18:04\n";

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (fileToString)!",
            yamlFile.fileToString(),
            new IsEqual<>(content)
        );

        yamlFile.load();
        yamlFile.set("test.number", 10);

        MatcherAssert.assertThat(
            "fileToString must not change until save!",
            yamlFile.fileToString(),
            new IsEqual<>(content)
        );

        final String newContent = "test:\n" +
            "  number: 10\n" +
            "  string: Hello world\n" +
            "  boolean: true\n" +
            "  list:\n" +
            "  - Each\n" +
            "  - word\n" +
            "  - will\n" +
            "  - be\n" +
            "  - in\n" +
            "  - a\n" +
            "  - separated\n" +
            "  - entry\n" +
            "math:\n" +
            "  pi: 3.141592653589793\n" +
            "timestamp:\n" +
            "  canonicalDate: 2020-07-04T13:18:04.458Z\n" +
            "  formattedDate: 04/07/2020 15:18:04\n";

        yamlFile.setConfigurationFile(new TempFile().value().toFile());
        yamlFile.save();

        MatcherAssert.assertThat(
            "Couldn't get the content of the file after save (fileToString)!",
            yamlFile.fileToString(),
            new IsEqual<>(newContent)
        );
    }

    @Test
    void saveToString() throws Exception {
        final YamlFile yamlFile = new YamlFile(YamlFileTest.getResource("test.yml"));
        yamlFile.load();
        final String content = "test:\n" +
            "  number: 5\n" +
            "  string: Hello world\n" +
            "  boolean: true\n" +
            "  list:\n" +
            "  - Each\n" +
            "  - word\n" +
            "  - will\n" +
            "  - be\n" +
            "  - in\n" +
            "  - a\n" +
            "  - separated\n" +
            "  - entry\n" +
            "math:\n" +
            "  pi: 3.141592653589793\n" +
            "timestamp:\n" +
            "  canonicalDate: 2020-07-04T13:18:04.458Z\n" +
            "  formattedDate: 04/07/2020 15:18:04\n";

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (saveToString)!",
            yamlFile.saveToString(),
            new IsEqual<>(content));

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (toString)!",
            yamlFile.toString(),
            new IsEqual<>(content));
    }

    @Test
    void saveToStringWithComments() throws IOException, InvalidConfigurationException {
        final YamlFile yamlFile = new YamlFile(YamlFileTest.getResource("test-comments.yml"));
        yamlFile.loadWithComments();

        final String content = "#####################\n" +
            "## INITIAL COMMENT ##\n" +
            "#####################\n" +
            "\n" +
            "# Test comments\n" +
            "test:\n" +
            "  number: 5\n" +
            "  # Hello!\n" +
            "  string: Hello world\n" +
            "  boolean: true\n" +
            "  # List of words\n" +
            "  list:\n" +
            "  - Each\n" +
            "  - word\n" +
            "  - will\n" +
            "  - be\n" +
            "  - in\n" +
            "  - a\n" +
            "  - separated\n" +
            "  # Comment on a list item\n" +
            "  - entry # :)\n" +
            "\n" +
            "# Wonderful number\n" +
            "math:\n" +
            "  pi: 3.141592653589793\n" +
            "  # Comment without direct key\n" +
            "\n" +
            "# Some timestamps\n" +
            "timestamp:\n" +
            "  # ISO\n" +
            "  canonicalDate: 2020-07-04T13:18:04.458Z\n" +
            "  # Date/Time with format\n" +
            "  formattedDate: 04/07/2020 15:18:04\n" +
            "\n" +
            "# End\n";

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (saveToStringWithComments)!",
            yamlFile.saveToStringWithComments(),
            new IsEqual<>(content));

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (toString)!",
            yamlFile.toString(),
            new IsEqual<>(content));
    }

    @Test
    void saveToStringWithComments2() throws IOException, InvalidConfigurationException {
        final YamlFile yamlFile = new YamlFile(YamlFileTest.getResource("test-comments2.yml"));
        yamlFile.loadWithComments();

        final String content = "# Section\n" +
            "section:\n" +
            "  # Sub section\n" +
            "  sub-section-1:\n" +
            "    # List of numbers\n" +
            "    list:\n" +
            "    - 1\n" +
            "    - 2\n" +
            "    - 3\n" +
            "  sub-section-2: # Side comment\n" +
            "    list:\n" +
            "    - 1\n" +
            "    - 2 # Side comment on an arbitrary element\n" +
            "    - 3\n" +
            "  sub-section-3:\n" +
            "    # List of numbers\n" +
            "    list:        # Side comment with extra space\n" +
            "    - 1\n" +
            "    - 2\n" +
            "    - 3\n";

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (saveToStringWithComments)!",
            yamlFile.saveToStringWithComments(),
            new IsEqual<>(content));

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (toString)!",
            yamlFile.toString(),
            new IsEqual<>(content));
    }

    @Test
    void saveToStringWithComments3() throws IOException, InvalidConfigurationException {
        final YamlFile yamlFile = new YamlFile(YamlFileTest.getResource("test-comments3.yml"));
        yamlFile.loadWithComments();

        final String content = "backup-config:\n" +
            "\n" +
            "  #######################################################################################################################\n" +
            "  # SERVER-FILES BACKUP\n" +
            "  #######################################################################################################################\n" +
            "\n" +
            "  # Backups your server.jar and all setting files before startup to /backups/server/...zip\n" +
            "  server-files-backup:\n" +
            "    enable: false\n" +
            "\n" +
            "    # Set max-days to 0 if you want to keep your server backups forever.\n" +
            "    max-days: 7\n" +
            "\n" +
            "\n" +
            "  #######################################################################################################################\n" +
            "  # WORLDS BACKUP\n" +
            "  #######################################################################################################################\n" +
            "\n" +
            "  # Backups all folders starting with \"world\" to /backups/worlds/...zip\n" +
            "  worlds-backup:\n" +
            "    enable: false\n" +
            "\n" +
            "    # Set max-days to 0 if you want to keep your world backups forever.\n" +
            "    max-days: 7\n" +
            "\n" +
            "\n" +
            "  #######################################################################################################################\n" +
            "  # PLUGINS BACKUP\n" +
            "  #######################################################################################################################\n" +
            "\n" +
            "  # Backups your plugins folder before startup to /backups/plugins/...zip\n" +
            "  plugins-backup:\n" +
            "    enable: true\n" +
            "\n" +
            "    # Set max-days to 0 if you want to keep your plugin backups forever.\n" +
            "    max-days: 7\n" +
            "\n";

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (saveToStringWithComments)!",
            yamlFile.saveToStringWithComments(),
            new IsEqual<>(content));

        MatcherAssert.assertThat(
            "Couldn't get the content of the file (toString)!",
            yamlFile.toString(),
            new IsEqual<>(content));
    }

    @Test
    void getComment() {

    }

    @Test
    void setComment() {

    }

    @Test
    void exists() {

    }

    @Test
    void createNewFile() {

    }

    @Test
    void deleteFile() {

    }

    @Test
    void getSize() {

    }

    @Test
    void getFilePath() {
        final File file = new File(YamlFileTest.getResource("test.yml"));
        final YamlFile yamlFile = new YamlFile(file);

        MatcherAssert.assertThat(
            "Configuration file is not the same!",
            yamlFile.getFilePath(),
            new IsEqual<>(file.getAbsolutePath()));
    }

    @Test
    void getConfigurationFile() {
        final File file = new File(YamlFileTest.getResource("test.yml"));
        final YamlFile yamlFile = new YamlFile(file);

        MatcherAssert.assertThat(
            "Configuration file is not the same!",
            yamlFile.getConfigurationFile(),
            new IsEqual<>(file));
    }

    @Test
    void setConfigurationFile() {
        final File file = new File(YamlFileTest.getResource("test.yml"));
        final YamlFile yamlFile = new YamlFile();

        yamlFile.setConfigurationFile(file);

        MatcherAssert.assertThat(
            "Configuration file has not changed!",
            yamlFile.getConfigurationFile(),
            new IsEqual<>(file));
    }

    @Test
    void copyTo() {

    }

}