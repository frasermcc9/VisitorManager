package visitorman;

/**
 * LogEntry
 */
public class LogEntry {

    private Party _visitor; // the visitor being logged
    private Party _host; // the visitor's host

    private String _visitDate; // the date the visitor checked in
    private String _visitStartTime; // the time the visitor checked in

    private String _checkOutTime = ""; // the time the visitor checked out

    public static int _count = 0; // times an instance of this class has been made

    /**
     * 
     * @param visitor        the visitor's party object being logged
     * @param host           the hosts's party object being logged
     * @param visitDate      the date of the visit
     * @param visitStartTime the start time of the visit
     */
    public LogEntry(Party visitor, Party host, String visitDate, String visitStartTime) {
        _visitor = visitor;
        _host = host;
        _visitDate = visitDate;
        _visitStartTime = visitStartTime;
        _count++;
        System.out.println(
                " --- LogEntry made for " + _visitor.getName(false) + ". LogEntry Instances: " + _count);
    }

    /**
     * sets the checkout time when the visitor leaves
     * 
     * @param checkOutTime the time the visitor checked out
     */
    public void checkOut(String checkOutTime) {
        _checkOutTime = checkOutTime;
    }

    /**
     * used for the report that shows all visitors currently on site
     * 
     * @return string of the visitor's name, org and email on the log instance
     */
    public String visitorOnSiteFormat() {
        return _visitor.nameOrgEmail(false);
    }

    /**
     * used for the making of the full visitor report
     * 
     * @return string of the information in this log properly formatted
     */
    public String reportFormat() {
        return _visitor.nameOrgEmail(true) + " visiting " + _host.getName(true) + ". Arrived:" + _visitDate + "T"
                + _visitStartTime + ". " + formatLeaveTime();
    }

    /**
     * method to help format the leaving time
     * 
     * @return string containing the appropriate formatting for the checkout time
     */
    private String formatLeaveTime() {
        return _checkOutTime.equals("") ? "On site." : "Left:" + _checkOutTime;
    }
}