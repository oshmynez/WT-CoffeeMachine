package entities;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class Logging {
    static Logger log = Logger.getLogger(Logging.class.getName());

    public void DataRecording(String infoEvent) throws FileNotFoundException {
        log.info(infoEvent);
    }
}
