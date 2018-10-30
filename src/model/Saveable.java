package model;

import java.io.IOException;

public interface Saveable {
    void saveItem(String fileName) throws IOException;

    void saveUserSystem(String fileName) throws IOException;

    void emptyFile(String fileName) throws IOException;
}