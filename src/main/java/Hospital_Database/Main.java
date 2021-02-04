package Hospital_Database;

public class Main {

    public static void main(String[] args) {

        // TODO: Check references
        // TODO: Check encapsulation
        // TODO: Add override methods like toString

        String hospitalDatabaseFilePath = "HospitalDB.txt";
        Hospital.getInstance().populate(hospitalDatabaseFilePath);
        
        Menu.mainMenuUserInterface(Hospital.getInstance());

    }

}
