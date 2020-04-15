package visitorman;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Manage visitors. When a visitor arrives, he must check in by providing his
 * name, the organisation he belongs to, his email address, and specify who he
 * is visiting. In order to receive a visitor, the host must be registered,
 * which is done by providing her name and email address. All names and email
 * addresses must be unique. All names consist of a given name and a family
 * name. A name may be formatted as either "formal" or "informal" The formal
 * format is family name followed by comma, followed by a space, followed by the
 * given name. The informal format is given name followed by space, followed
 * family name.
 * 
 * <P>
 * SOFTENG251 2020 Assignment 2. Copyright Ewan Tempero, The University of
 * Auckland, 2020.
 */
public class VisitorMan {
    private String _organisationName;

    private VisitorLog _visitorLog;
    // hashmap allows indexing of hosts/visitors by their email. Linked forces the
    // list to
    // remain ordered in entry order
    private LinkedHashMap<String, Party> _hosts = new LinkedHashMap<String, Party>();
    private LinkedHashMap<String, LogEntry> _visitorsOnSite = new LinkedHashMap<String, LogEntry>();

    /**
     * Create a VisitorMan object for the specified organisation.
     * 
     * @param organisationName The name of the organisation whose visitors are being
     *                         managed.
     */
    public VisitorMan(String organisationName) {
        _organisationName = organisationName;
        _visitorLog = new VisitorLog(_organisationName);
        LogEntry._count = 0;
        Party._count = 0;
        Name._count = 0;
        System.out.println("A VisitorMan object was created for organisation:" + _organisationName);
    }

    /**
     * Register someone as able to host a visitor. It is assumed that there is
     * always both a given and a family name, that the host's name is unique (no
     * other hosts with the same name), and the email address is unique.
     * 
     * @param familyName The family name of the host
     * @param givenName  The given name of the host
     * @param email      The email address of the host.
     */
    public void registerHost(String familyName, String givenName, String email) {
        Name newHostName = new Name(givenName, familyName); // create Name instance
        Party newHost = new Party(newHostName, email, _organisationName); // create party instance for host
        _hosts.put(email, newHost);// add the host to the hashmap, with the host email as the key
    }

    /**
     * Provide a string that describes the host with the specified email address.
     * 
     * @param emailAddress The email address of the host to describe.
     * @return A string describing the host, with format FORMAL_NAME ". "
     *         EMAIL_ADDRESS. Returns null if there is no host with the email
     *         address.
     */
    public String showHostDetails(String emailAddress) {
        Party match = _hosts.get(emailAddress); // index the host hashmap with the email
        if (match == null) {
            return null;
        } else {
            return match.formalAndEmail();
        }
    }

    /**
     * Provide a list of strings describing all the registered hosts. Each string
     * should use the same format as {@link #showHostDetails(String)} and in the
     * order that the hosts were registered.
     * 
     * @return A list of string with host details.
     */
    public List<String> getRegisteredHosts() {
        List<String> details = new ArrayList<String>();
        for (Party entry : _hosts.values()) {
            details.add(entry.formalAndEmail()); // add every host in the hashmap to the list and format
        }
        return details;
    }

    /**
     * Record that a visitor with the specified details is visiting the host with
     * the specified email address on the date given and starting at the specified
     * time. There are always both given and family names, and names and email
     * addresses are unique.
     * 
     * @param familyName     The family name of the visitor
     * @param givenName      The given name of the visitor
     * @param organisation   The organisation the visitor is from
     * @param visitorEmail   The email address of the visitor
     * @param hostEmail      The email address of the host the visitor is visiting
     * @param visitDate      The date of the visit (ISO8601 format)
     * @param visitStartTime The time of the start of the visit (ISO8601 format)
     */
    public void checkIn(String familyName, String givenName, String organisation, String visitorEmail, String hostEmail,
            String visitDate, String visitStartTime) {

        Name newVisitorName = new Name(givenName, familyName); // get name of visitor
        Party newVisitor = new Party(newVisitorName, visitorEmail, organisation); // create party instance for visitor
        Party host = _hosts.get(hostEmail); // get party instance of the host

        // construct log instance from the visitor and host party instances
        LogEntry newLog = new LogEntry(newVisitor, host, visitDate, visitStartTime);

        // place log into visitorsOnSite hashmap, with visitorEmail as the key.
        _visitorsOnSite.put(visitorEmail, newLog);
        _visitorLog.newLog(newLog); // place the new log into the visitorlog class
    }

    /**
     * Record that the visitor with the specified email address checked out at the
     * specified time.
     * 
     * @param emailAddress The visitor's email address.
     * @param checkOutTime The checkout time (ISO8601 format)
     */
    public void checkOut(String emailAddress, String checkOutTime) {
        LogEntry logToRemove = _visitorsOnSite.get(emailAddress); // get the log of the person being checked out.
        logToRemove.checkOut(checkOutTime); // invoke the checkout method to add the checkout time to the log
        // (this change is also reflected in the VisitorLog class). Once checked out,
        // we no longer need individual access to it from this class, so can remove it
        // from the hashmap.
        _visitorsOnSite.remove(emailAddress);
    }

    /**
     * Return a report of all the visitors current on site, that is, those that have
     * checked in but not checked out. The report is a list of strings (one string
     * per visitor, with the format INFORMAL_NAME " (" ORGANISATION "). "
     * EMAIL_ADDRESS
     * 
     * @return A list of strings with the visitors on site.
     */
    public List<String> getVisitorsOnSite() {
        List<String> details = new ArrayList<String>();
        // for each log in visitorsOnSite hashmap, add details to the string list
        for (LogEntry entry : _visitorsOnSite.values()) {
            details.add(entry.visitorOnSiteFormat());
        }
        return details;
    }

    /**
     * Return a report of the complete visitor log as a list of strings (one string
     * per line in the report). The first line of the report is the company name.
     * The remaining lines are, in order of arrival, one line per visitor giving
     * full details of visitor, their check in time, and their check out time (or
     * empty if they are still on site).
     * 
     * @return The report for the visitor log.
     */
    public List<String> getVisitorLogReport() {
        return _visitorLog.getReport();
    }
}
