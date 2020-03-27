package lab5;

import java.sql.*;
import java.util.Scanner;

public class Villains {

    public static void main(String[] args) throws SQLException {
        //Simple method to loop so we keep repeating the main menu to the user
        while (true) {
            execute();
        }
    }

    public static void execute() {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        Statement sta = null;

        try {
            Class.forName("org.sqlite.JDBC");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.err.println(e.getMessage());

        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:comicbookvillains.db");
            sta = con.createStatement();
            System.out.println("***********************************************************************************************************");
            System.out.println("*                                     COMIC BOOK VILLAINS DATABASE                                        *");
            System.out.println("***********************************************************************************************************");
            System.out.println(" To query database, select from the following options:\n");
            System.out.println(" 1  - Which company created which villain?");
            System.out.println(" 2  - What crime did each villain commit? Show crime location & death toll too.");
            System.out.println(" 3  - Which villains who do not have supernatural abilities committed crimes on Earth? Show death toll.");
            System.out.println(" 4  - Which of the nemesis' do not have a sidekick?");
            System.out.println(" 5  - What is the total number of villains that all the creators in this DB ever created combined?");
            System.out.println(" 6  - Who is Grigori Rasputin's nemesis?");
            System.out.println(" 7  - What are the abilities of Solid Snake?");
            System.out.println(" 8  - Who is creator OBAI ALSAMADI's most popular villain?");
            System.out.println(" 9  - Which company and creator collaborated on the creation of the villain BAUKON?");
            System.out.println(" 10 - Which of the villains have SUPERSTRENGTH?");
            System.out.println(" 11 - Which villains were created between 1995 and 2020?\n");
            System.out.println(" The following options only demonstrate database modification capabilities(not needed for queries):\n");
            System.out.println(" 12 - Insert new villain to the database.");
            System.out.println(" 13 - Update year of creation of JOKER in database.");
            System.out.println(" 14 - Delete BATMAN from database.\n");
            System.out.println("*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*");
            System.out.println("*                                   TO TERMINATE SESSION, ENTER THE NUMBER: 15                              *");
            System.out.println("*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*\n");
            System.out.println("Enter command number(choose a number from list): ");
            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            scan.nextLine();

            switch (input) {

                case 1:
                    String sql;
                    sql = "SELECT name, mostPopularVillain FROM Company;";
                    ResultSet result = sta.executeQuery(sql);
                    System.out.println("List of companies and the villains they created");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String heading1 = "Company";
                    String heading2 = "Villain";
                    System.out.printf("%-23s %-23s %n", heading1, heading2);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");

                    while (result.next()) {
                        String name = result.getString("name");
                        String mostPopularVillain = result.getString("mostPopularVillain");
                        System.out.printf("%-23s %-23s %n", name, mostPopularVillain);
                    }
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;
                case 2:
                    sql = "SELECT name, crimeType, location,deathToll FROM Crime JOIN\n"
                            + "Villain on Crime.CrimeID=Villain.Id;";
                    result = sta.executeQuery(sql);
                    System.out.println("Crimes each villain committed(type, location, death toll)");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String heading3 = "Villain";
                    String heading4 = "Crime";
                    String heading5 = "Location";
                    String heading6 = "Death Toll";
                    System.out.printf("%-23s %-35s %-23s %-23s %n", heading3, heading4, heading5, heading6);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        String name = result.getString("name");
                        String crimeType = result.getString("crimeType");
                        String location = result.getString("location");
                        int deathToll = result.getInt("deathToll");
                        System.out.printf("%-23s %-35s %-23s %d %n", name, crimeType, location, deathToll);
                        System.out.println();
                    }
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;
                case 3:
                    sql = "SELECT name, location, deathToll FROM Villain JOIN Crime on\n"
                            + "Villain.Id=Crime.crimeID JOIN VillainAbilities on\n"
                            + "Villain.Id=VillainAbilities.Id WHERE location='EARTH' AND superNatural\n"
                            + "IS NULL;";
                    result = sta.executeQuery(sql);
                    System.out.println("Villains w/o SUPERNATURAL abilities that commited crimes on Earth");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String heading7 = "Villain";
                    String heading8 = "Location";
                    String heading9 = "Death Toll";
                    System.out.printf("%-23s %-23s %-23s %n", heading7, heading8, heading9);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        String name = result.getString("name");
                        String location = result.getString("location");
                        int deathToll = result.getInt("deathToll");
                        System.out.printf("%-23s %-23s %d %n", name, location, deathToll);
                    }
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 4:
                    sql = "SELECT name FROM Nemesis WHERE sideKick IS NULL;";
                    result = sta.executeQuery(sql);
                    System.out.println("Nemesis' with no sidekick");
                    System.out.println("x---x---x---x---x---x---x");
                    while (result.next()) {
                        System.out.println(result.getString("name"));
                    }
                    System.out.println("x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 5:
                    sql = "SELECT SUM (totalVillains) FROM Creator;";
                    result = sta.executeQuery(sql);
                    System.out.println("Total number of villains");
                    System.out.println("x---x---x---x---x---x---x");
                    while (result.next()) {
                        System.out.println(result.getString(1));
                    }
                    System.out.println("x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 6:
                    sql = "SELECT Nemesis.name FROM Villain JOIN Nemesis on\n"
                            + "Villain.Id=Nemesis.Id WHERE Villain.name='GRIGORI RASPUTIN';";
                    result = sta.executeQuery(sql);
                    System.out.println("Grigori Rasputin's nemesis");
                    System.out.println("x---x---x---x---x---x---x---x");
                    System.out.println(result.getString("name"));
                    System.out.println("x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 7:
                    sql = "SELECT natural, superNatural FROM Nemesis JOIN NemesisAbilities\n"
                            + "on Nemesis.Id=NemesisAbilities.Id WHERE Nemesis.name='SOLID SNAKE'";
                    result = sta.executeQuery(sql);
                    System.out.println("Solid Snake's Abilities");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String heading12 = "Natural Abilities";
                    String heading13 = "Supernatural Abilities";
                    System.out.printf("%-50s %-50s %n", heading12, heading13);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String natural = result.getString("natural");
                    String superNatural = result.getString("superNatural");
                    System.out.printf("%-50s %-50s %n", natural, superNatural);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;
                case 8:
                    sql = "SELECT mostPopularVillain FROM Creator WHERE firstName='OBAI' AND\n"
                            + "lastName='ALSAMADI';";
                    result = sta.executeQuery(sql);
                    System.out.println("Obai Alsamadi's most popular villain");
                    System.out.println("x---x---x---x---x---x---x---x");
                    System.out.println(result.getString("mostPopularVillain"));
                    System.out.println("x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 9:
                    sql = "SELECT name FROM Company WHERE mostPopularVillain='BAUKONS' UNION SELECT firstName FROM Creator WHERE mostPopularVillain= 'BAUKONS';";
                    result = sta.executeQuery(sql);
                    System.out.println("Collaborators on the creation of villain BAUKONS");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");
                    String heading14 = "Company";
                    String heading15 = "Creator";
                    System.out.printf("%-22s %-21s %n", heading14, heading15);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        String name = result.getString("name");
                        System.out.printf("%-23s", name);
                    }
                    System.out.println("\n");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;

                case 10:
                    sql = "SELECT Villain.name FROM Villain JOIN VillainAbilities on\n"
                            + "Villain.Id=VillainAbilities.Id WHERE superNatural LIKE\n"
                            + "'%SUPERSTRENGTH%';";
                    result = sta.executeQuery(sql);
                    System.out.println("Villains with SUPERSTRENGTH");
                    System.out.println("x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        System.out.println(result.getString("name"));
                    }
                    System.out.println("x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;
                case 11:
                    sql = "SELECT name,yearCreated FROM Villain WHERE yearCreated >= 1995 AND yearCreated <= 2020;";
                    result = sta.executeQuery(sql);
                    System.out.println("Villains created between 1995 and 2020");
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x");
                    String heading16 = "Villain";
                    String heading17 = "Year Created";
                    System.out.printf("%-23s %-23s %n", heading16, heading17);
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        String name = result.getString("name");
                        int yearCreated = result.getInt("yearCreated");
                        System.out.printf("%-23s %d %n", name, yearCreated);
                    }
                    System.out.println("x---x---x---x---x---x---x---x---x---x---x");
                    System.out.println("\n");
                    break;
                case 12:
                    sta = con.createStatement();
                    System.out.println("Please follow the steps to add a new villain:");
                    System.out.println("Enter name of new villain:");
                    String name = sc.nextLine();
                    System.out.println("Enter year of creation of the new villain:");
                    String yearCreated = sc.nextLine();
                    System.out.println("Enter the creator's first name of the new villain:");
                    String creatorFirstName = sc.nextLine();
                    System.out.println("Enter the creator's last name of the new villain");
                    String creatorlastName = sc.nextLine();
                    sta.executeUpdate("INSERT INTO Villain (name, yearCreated, creatorFirstName, creatorlastName) VALUES (" + "'" + name + "'" + "," + yearCreated + "," + "'" + creatorFirstName + "'" + "," + "'" + creatorlastName + "'" + ");");
                    String sql0;
                    sql0 = "SELECT name FROM Villain;";
                    result = sta.executeQuery(sql0);
                    System.out.println("\n");
                    System.out.println("Displaying updated list of Villains:");
                    System.out.println("x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        System.out.println(result.getString("name"));
                    }
                    break;
                case 13:
                    sta = con.createStatement();
                    //Before UPDATE
                    System.out.println("Displaying JOKER's data before UPDATE operation: ");
                    String sqll;
                    sqll = "SELECT name, yearCreated FROM Villain WHERE Id='1';";
                    result = sta.executeQuery(sqll);
                    String namee = result.getString("name");
                    int yearr = result.getInt("yearCreated");
                    System.out.println(namee + "'s year of creation is " + yearr);

                    //UPDATE
                    sql = "UPDATE Villain set yearCreated = 2020 where Id=1;";
                    sta.executeUpdate(sql);
                    //After UPDATE
                    String sql2;
                    sql2 = "SELECT name, yearCreated FROM Villain WHERE Id='1';";
                    result = sta.executeQuery(sql2);
                    String name1 = result.getString("name");
                    int year = result.getInt("yearCreated");
                    System.out.println("\n");
                    System.out.println("Displaying JOKER's data after UPDATE operation: ");
                    System.out.println(name1 + "'s year of creation was changed to " + year);
                    System.out.println("\n");
                    break;
                case 14:
                    sta = con.createStatement();
                    //Before delete operation
                    System.out.println("Displaying Nemesis table entries before DELETE operation: ");
                    String sql3;
                    sql3 = "SELECT Id, name FROM Nemesis";
                    result = sta.executeQuery(sql3);
                    String head1 = "Id";
                    String head2 = "Nemesis";
                    System.out.printf("%-10s %10s %n", head1, head2);
                    System.out.println("x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        int nemId1 = result.getInt("Id");
                        String nemName = result.getString("name");
                        System.out.printf("%-10d %10s %n", nemId1, nemName);
                    }
                    System.out.println("\n");
                    //Delete operation
                    sql = "DELETE from Nemesis where Id=1;";
                    sta.executeUpdate(sql);
                    //After delete operation
                    String sql4;
                    sql4 = "SELECT Id, name FROM Nemesis;";
                    result = sta.executeQuery(sql4);
                    System.out.println("The nemesis BATMAN has been deleted.\n");

                    System.out.printf("%-10s %10s %n", head1, head2);
                    System.out.println("x---x---x---x---x---x---x---x");
                    while (result.next()) {
                        String name2 = result.getString("name");
                        int nemId = result.getInt("Id");
                        System.out.printf("%-10d %10s %n", nemId, name2);
                    }
                    System.out.println("\n");
                    break;
                case 15:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection! Try a number between 1-14 or 15 to terminate.");
                    break;
            }
            con.close();

        } catch (SQLException ex) {
            System.err.println("--------SQL Exception--------");
            System.err.println("SqlState" + ex.getSQLState());
            System.err.println("Message" + ex.getMessage());
            System.err.println("Vendor" + ex.getErrorCode());
        }
    }

}
