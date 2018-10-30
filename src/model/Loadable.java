package model;

import java.io.IOException;

public interface Loadable {
    void loadItem(String fileName) throws IOException;

    void loadUserSystem(String fileName) throws IOException;
}
