package Hospital_Database;

public class Remedy {

    private String name;
    private String dateApplied;

    public Remedy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getDateApplied() {
        return dateApplied;
    }
}
