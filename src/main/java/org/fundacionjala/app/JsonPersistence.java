package org.fundacionjala.app;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;

public class JsonPersistence {
    public static void main(String[] args) {
        Quest quest = new Quest("Name?", "Salvador");
        writeJsonFile(quest);

        Quest parsedPerson = readJsonFile();
        System.out.println(parsedPerson);
    }

    private static Quest readJsonFile() {
        Gson gson = new Gson();
        Quest quest = null;
        try (JsonReader reader = new JsonReader(new FileReader("./myForm.json"))) {
            quest = gson.fromJson(reader, Quest.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return quest;
    }

    public static void writeJsonFile(Quest quest) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter("./myForm.json")) {
            gson.toJson(quest, writer);
        } catch (JsonIOException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static class Quest {
        private final String question;
        private final String answer;

        public Quest(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getTittle() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public String toString() {
            return getTittle() + " " + getAnswer();
        }
    }
}
