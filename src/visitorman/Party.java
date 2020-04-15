package visitorman;

/**
 * Party class stores the name and email and organisation (if applicable) of any
 * visitor and host, and contains methods used for returning general information
 * about the individual.
 */
public class Party {

    private Name _name; // the name object representing the individual
    private String _email; // the individual's email

    private String _organisation; // the individuals's organisation. For hosts, this field is set equal to the
                                  // initially declared organisation (ie the one this manager is being used by).

    public static int _count = 0; // times an instance of this class has been made

    /**
     * Creates a new party instance for a visitor
     * 
     * @param name  name object for the individual
     * @param email the email of the individual
     * @param org   the visitor's email
     */
    public Party(Name name, String email, String org) {
        _name = name;
        _email = email;
        _organisation = org;
        _count++;
        System.out.println(" --- Party made for " + _name.informalName() + ". Party Instances: " + _count);
    }

    /**
     * gets the name of this party instance.
     * 
     * @param returnFormal returns formal name if true, informal if false
     * @return the name, organisation, and email of the individual.
     */
    public String getName(boolean returnFormal) {
        return returnFormal == true ? _name.formalName() : _name.informalName();
    }

    /**
     * @return the formal name and email of the individual
     */
    public String formalAndEmail() {
        return getName(true) + ". " + _email;
    }

    /**
     * Returns the name, organisation and email of the individual this instance
     * belongs to.
     * 
     * @param returnFormal returns formal name if true, informal if false
     * @return the name, organisation, and email of the individual.
     */
    public String nameOrgEmail(boolean returnFormal) {
        return getName(returnFormal) + " (" + _organisation + "). " + _email;

    }
}
