//Phase 5
import java.util.*;
import java.sql.*;
class BookStore{
Connection connection = null;
public static String[] relation_list={
"customer","employee","book","store","supplier","dependents","supply","purchase","return","sales_person","manager","purchase_details","supply_details"
};	
public void display_relations(int relation_choice){
String relation_name="F16_T11_"+BookStore.relation_list[relation_choice-1];
  try {
            Statement stmt = connection.createStatement();
	       	ResultSet rs = stmt.executeQuery("select * from "+relation_name);
	       	ResultSetMetaData rsmd = rs.getMetaData();
	        int columnsNumber = rsmd.getColumnCount();
	       for(int i=1;i<=columnsNumber;i++) System.out.print(rsmd.getColumnName(i)+"\t\t ");
	       	System.out.println();
	       while (rs.next()){
	       
	         for(int i=1;i<=columnsNumber;i++) System.out.print(rs.getString(i)+"\t\t");
	         	System.out.println();
	         	}
	         	System.out.println();
	      
        }
        catch (SQLException e) {
 
			System.out.println("Error in accessing the relation!");
			e.printStackTrace();
			return;
 
		}

}
public void insert_book_relation(){
	
try{
	display_relations(3);
	Scanner scanner=new Scanner(System.in);
	System.out.println("Enter the data for insertion into "+BookStore.relation_list[2]);
	Statement stmt = connection.createStatement();
	String relation_name="F16_T11_"+BookStore.relation_list[2];
	System.out.println("Bookid: ");String bookid=scanner.next();
	System.out.println("no of pages: ");String nopages=scanner.next();
	System.out.println("isbn: ");String isbn=scanner.next();
	System.out.println("edition: ");String edition=scanner.next();
	System.out.println("Name: ");String name=scanner.next();
	System.out.println("Author: ");String author=scanner.next();
	System.out.println("Publisher: ");String publisher=scanner.next();
	System.out.println("price: ");String price=scanner.next();
	System.out.println("Genre: ");String genre=scanner.next();
	String ins_statement="insert into "+relation_name+" values('"+bookid+"',"+nopages+","+isbn+","+edition+",'"+name+"','"+author+"','"+publisher+"',"+price+",'"+genre+"')";
	stmt.executeUpdate(ins_statement);
 	System.out.println("Data inserted into book successfully!");
	       
         
}catch(Exception e){
	e.printStackTrace();
}
}
public void createConnection(){
	try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}

		try {
 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:cse1", "nxr0659", "Apple2016");
 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (connection == null) {
			System.out.println("Failed to make connection!");
		}
 
}
public void update_book_relation(){

	System.out.println("Discount what percent(%) based on genre");
	String relation_name="F16_T11_"+BookStore.relation_list[2];
	Scanner scanner=new Scanner(System.in);
	try{
	Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	ResultSet rs = stmt.executeQuery("select distinct genre from "+relation_name+" order by genre");
	 int i=1;
	rs.last();
	int size = rs.getRow();
	rs.beforeFirst();
	String[] genre=new String[size];
	while (rs.next()){

		genre[i-1]=rs.getString(1);
		System.out.println(i +"."+rs.getString(1));
		i++;
	}
	System.out.println("Enter yout choice:");
	int uchoice=scanner.nextInt();
	System.out.println("Enter discount percentage:");
	double dpercent=scanner.nextDouble()/100.0;
	String upd_statement="update F16_T11_book set price=(price-(price*"+dpercent+")) where genre='"+genre[uchoice-1]+"'";
	stmt = connection.createStatement();
	stmt.executeUpdate(upd_statement);
}catch(Exception e){
e.printStackTrace();
}
System.out.println("Successfully updated discounts!!");

}
public static void main(String[] ar){
	System.out.println("MENU:");
	System.out.println("1.Display Relation");
	System.out.println("2.Insert into Book Relation");
	System.out.println("3.Update Book Relation");
	
	
	System.out.println("Enter your choice:");
	Scanner scanner=new Scanner(System.in);
	int choice=scanner.nextInt();
	BookStore b=new BookStore();
	b.createConnection();
	int rchoice;
	switch(choice)
	{
	
		case 1: 
		System.out.println("The relations availabe are: ");

		for(int i=0;i<relation_list.length;i++){
			System.out.println(i+1+"."+BookStore.relation_list[i]);
		}
		System.out.println("Enter your choice");
		rchoice=scanner.nextInt();
		b.display_relations(rchoice);
		break;
		case 2:
		b.insert_book_relation();
		break;
		case 3:
		b.update_book_relation();
		break;
		default: System.out.println("Enter correct choice!");


	}


}


}