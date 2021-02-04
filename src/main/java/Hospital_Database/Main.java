package Hospital_Database;

public class Main {

    public static int main(String[] args) {

        // TODO: Check references
        // TODO: Check encapsulation
        // TODO: Add override methods like toString
        // TODO: UML
        
        String hospitalDatabaseFilePath = "HospitalDB.txt";
        Hospital.getInstance().populate(hospitalDatabaseFilePath);
        
        Menu.mainMenuUserInterface(Hospital.getInstance());

        return 0;
    }

}
