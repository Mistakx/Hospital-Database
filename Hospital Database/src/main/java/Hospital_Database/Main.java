package Hospital_Database;

public class Main {

    public static void main(String[] args) {

        String hospitalDatabaseFilePath = "HospitalDB.txt";
        Hospital.getInstance().populate(hospitalDatabaseFilePath);

        Menu.mainMenuUserInterface(Hospital.getInstance());

    }

}
