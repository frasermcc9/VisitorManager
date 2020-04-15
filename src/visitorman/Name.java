package visitorman;

/**
 * Name Name class used for creating name objects, which contain the given and
 * family name of an individual, and allows for methods for returning various
 * name forms.
 */
public class Name {
    private String _givenName; // the first name of an individual
    private String _familyName; // the last name of an individual

    public static int _count = 0; // times an instance of this class has been made

    public Name(String given, String last) {
        _givenName = given;
        _familyName = last;
        _count++;
        System.out.println(
                " --- Name object created for: " + informalName() + ". Name Instances: " + _count);
    }

    /**
     * 
     * @return the formal name of the individual
     */
    public String formalName() {
        return _familyName + ", " + _givenName;
    }

    /**
     * 
     * @return the informal name of the individual
     */
    public String informalName() {
        return _givenName + " " + _familyName;
    }

}