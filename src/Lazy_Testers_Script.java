import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lazy_Testers_Script {
    public static String FULL_FILENAME;
    public static String FILENAME;
    public static String ORIGINAL_FILENAME;


    public static void main(String[] args) throws IOException {
        createfile();
        write_tester();
        setup();
    }



    public static void createfile(){
        Scanner original_file_name_input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the filename from the java class you want to test (ex: LAB04P3Wrapper)");

        String filename = original_file_name_input.nextLine();  // Read user input
        System.out.println("[+] Filename is: " + filename.replaceAll(" ",""));  // Output user input
        FILENAME = filename;
        ORIGINAL_FILENAME = filename + "_Tester";
        FULL_FILENAME = filename + "_Tester.java";
        try {
            File tester_file = new File(FULL_FILENAME);
            if (tester_file.createNewFile()) {
                System.out.println("[+] File created: " + tester_file.getName());
            } else {
                System.out.println("[+] File already exists.");
            }
        } catch (IOException e) {
            System.out.println("[+] An error occurred.");
            e.printStackTrace();
        }
    }


    public static void write_tester(){
        String general_template = "import org.junit.Test;\n" + "\n" + "public class " + ORIGINAL_FILENAME +" {\n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "}" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n";
        try {
            FileWriter Writer = new FileWriter(FULL_FILENAME);
            Writer.write(general_template);
            Writer.close();
            System.out.println("[+] Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("[+] An error occurred.");
            e.printStackTrace();
        }
    }


    public static void Write2SpecificLine(int line_number, String s) throws IOException {
        Path path = Paths.get(FULL_FILENAME);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        lines.add(line_number - 1, s);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void setup() throws IOException {
        //Lets write what the class structure is!

        //Adding testcases
        Write2SpecificLine(4, "    @Test\n" + "   public void testcase1(){\n" +" \n"  + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + " \n" + "    \n" +"       //Test code here\n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "   }");
        System.out.println("[+] Successfully Added TestCase\n");

        //Reads Structure
        System.out.println("Enter the structure of the class (ex: DynamicBag)");
        BufferedReader structure_scanner = new BufferedReader(new InputStreamReader(System.in));
        String structure_txt = structure_scanner.readLine();

        //Writing variable
        Write2SpecificLine(8,"      " + FILENAME + "." + structure_txt.toString() + " list = new " + FILENAME + "." + structure_txt.toString() + "();");

        //List?
        System.out.println("Want to make a list from the input (yes or no)");
        BufferedReader list_scanner = new BufferedReader(new InputStreamReader(System.in));
        String list_txt = list_scanner.readLine();

        if (list_txt.toLowerCase().equals("y") || list_txt.toLowerCase().equals("yes")){
            System.out.println("Want type of object you want to add to the list? (ex: Int,String,Double)");
            BufferedReader type_scanner = new BufferedReader(new InputStreamReader(System.in));
            String type_of_object = type_scanner.readLine();

            if (type_of_object.replaceAll(" ","").toLowerCase().equals("string")){
                System.out.println("Add the elements to the list (ex: Ken,Jil,Ron)\nNOTE: DIVIDED BY COMMAS");
                BufferedReader elements_scanner = new BufferedReader(new InputStreamReader(System.in));
                String elements_txt = elements_scanner.readLine();
                String[] elements = elements_txt.split(",");

                for (int i = 0; i < elements.length; i++) {
                    Write2SpecificLine(9+i, "       list.add(" + '"'  + elements[i] + '"' +");\n");
                }
            }else {
                System.out.println("Add the elements to the list (ex: 1,2,3 or whatever you choose to add)\nNOTE: DIVIDED BY COMMAS ");
                BufferedReader elements_scanner = new BufferedReader(new InputStreamReader(System.in));
                String elements_txt = elements_scanner.readLine();
                String[] elements = elements_txt.split(",");

                for (int i = 0; i < elements.length; i++) {
                    Write2SpecificLine(9 + i, "       list.add(" + elements[i] + ");\n");
                }
            }

            System.out.println("[+] List Created\n");

        }
        System.out.println("NOTE: DRAG AND DROP THE TESTER FILE TO A TEST SOURCE ROOT FOLDER!, IF YOU DON'T WANT THIS MEANS GOD BLESS YOU IN THIS CLASS\n");
        System.out.println("HAPPY CODING :)");

    }



    }


