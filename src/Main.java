import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        String url="";
        String user="";
        String pass="";
        Scanner sc=new Scanner(System.in);

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con=DriverManager.getConnection(url, user, pass);
            if(con!=null){
                System.out.println("Connection established successfully!");
            }

            int choice;
            do{
                System.out.println("********* MENU **************");
                System.out.println("1. Insert data");
                System.out.println("2. Update data");
                System.out.println("3. Delete data");
                System.out.println("4. View data");
                System.out.println("5. Exit");

                choice = sc.nextInt();

                switch(choice){
                    case 1:
                        System.out.println("Enter id: ");
                        int id=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter name: ");
                        String name=sc.nextLine();
                        System.out.println("Enter age: ");
                        int age=sc.nextInt();
                        System.out.println("Enter marks: ");
                        int marks=sc.nextInt();

                        PreparedStatement ps1 = con.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");                                    //here
                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setInt(3, age);
                        ps1.setInt(4, marks);
                        int row = ps1.executeUpdate();
                        if(row>0){
                            System.out.println("Data inserted successfully!");
                        }
                        else{
                            System.out.println("Data insertion failed!");
                        }
                        break;

                    case 2:
                        System.out.println("Enter id to update: ");
                        int updId=sc.nextInt();
                        System.out.println("Enter marks to update: ");
                        int updMarks=sc.nextInt();

                        PreparedStatement ps2 = con.prepareStatement("UPDATE student SET marks=? WHERE id=?");                                    //here
                        ps2.setInt(1, updMarks);
                        ps2.setInt(2, updId);
                        int row1 = ps2.executeUpdate();
                        if(row1>0){
                            System.out.println("Data updated successfully!");
                        }
                        else{
                            System.out.println("Data update failed!");
                        }
                        break;

                    case 3:
                        System.out.println("Enter id to delete: ");
                        int delId=sc.nextInt();

                        PreparedStatement ps3 = con.prepareStatement("DELETE FROM student WHERE id=?");                                    //here
                        ps3.setInt(1, delId);
                        int row2 = ps3.executeUpdate();
                        if(row2>0){
                            System.out.println("Data deleted successfully!");
                        }
                        else{
                            System.out.println("Data deletion failed!");
                        }
                        break;

                    case 4:
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM student");
                        while(rs.next()){
                            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Age: " + rs.getInt("age") + ", Marks: " + rs.getInt("marks"));
                        }
                        break;
                    
                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");

                }
            } while(choice != 5);

            con.close();

        } catch (Exception e){ 
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}








// [3:02 pm, 10/05/2026] Parag Dharamkar: wget https://downloads.mariadb.com/Connectors/java/connector-java-3.5.3/mariadb-java-client-3.5.3.jar
//[3:03 pm, 10/05/2026] Parag Dharamkar: javac -cp mariadb-java-client-3.5.3.jar Main.java
//[3:03 pm, 10/05/2026] Parag Dharamkar: java -cp .:mariadb-java-client-3.5.3.jar Main