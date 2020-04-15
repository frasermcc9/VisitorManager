package visitorman;

import java.util.ArrayList;

import java.util.List;

/**
 * VisitorLog class stores all created LogEntrys, and has methods related to
 * formatting the logs.
 */
public class VisitorLog {

    private String _orgName;
    private List<LogEntry> _visitorLogs = new ArrayList<LogEntry>();

    /**
     * creates a VisitorLog
     * 
     * @param orgName the organisation the log belongs to
     */
    public VisitorLog(String orgName) {
        _orgName = orgName;
        System.out.println("VisitorLog has been created");
    }

    /**
     * adds a new log to the class's arraylist
     * 
     * @param newLog the log to be added
     */
    public void newLog(LogEntry newLog) {
        _visitorLogs.add(newLog);
    }

    /**
     * creates a report of all stored LogEntries.
     * 
     * @return a string list of all stored LogEntries.
     */
    public List<String> getReport() {
        List<String> logList = new ArrayList<String>();
        logList.add(_orgName);
        // for each log in the visitorLogs list, add its formatted details to the list.
        for (LogEntry log : _visitorLogs) {
            logList.add(log.reportFormat());
        }
        return logList;
    }

}