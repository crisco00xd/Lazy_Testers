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
    public static int INITIAL_LINE_POS = 4;


    public static void main(String[] args) throws IOException {
        createfile();
        write_tester();
        setup(INITIAL_LINE_POS);
        while(true){
            BufferedReader testcases_num_scanner = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Want to add another testcase? (yes or no)");
            String test_cases_num = testcases_num_scanner.readLine();
            if (test_cases_num.toLowerCase().replaceAll(" ","").equals("yes") || test_cases_num.toLowerCase().replaceAll(" ","").equals("y")){
                setup(INITIAL_LINE_POS + 29);
            }else{
                System.out.println("NOTE: DRAG AND DROP THE TESTER FILE TO A TEST SOURCE ROOT FOLDER!, IF YOU DON'T KNOW WHAT THIS MEANS GOD BLESS YOU IN THIS CLASS\n");
                System.out.println("HAPPY CODING :)");
                break;
            }
        }

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

    public static void setup(int line_pos) throws IOException {
        //Lets write what the class structure is!

        //Adding testcases
        Write2SpecificLine(line_pos, "    @Test\n" + "   public void testcase1(){\n" +" \n"  + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + " \n" + "    \n" +"       //Test code here\n" + "    \n" + "    \n" + "    \n" + "    \n" + "    \n" + "   }");
        System.out.println("[+] Successfully Added TestCase\n");

        //Reads Structure
        System.out.println("Enter the structure of the class (ex: DynamicBag)");
        BufferedReader structure_scanner = new BufferedReader(new InputStreamReader(System.in));
        String structure_txt = structure_scanner.readLine();

        //Writing variable
        Write2SpecificLine(line_pos + 4,"      " + FILENAME + "." + structure_txt.toString() + " list = new " + FILENAME + "." + structure_txt.toString() + "();");

        //List?
        System.out.println("Want to make a list from the input (yes or no)");
        BufferedReader list_scanner = new BufferedReader(new InputStreamReader(System.in));
        String list_txt = list_scanner.readLine();

        if (list_txt.toLowerCase().equals("y") || list_txt.toLowerCase().equals("yes")){
            System.out.println("Want type of object you want to add to the list? (ex: Int,String,Double)");
            BufferedReader type_scanner = new BufferedReader(new InputStreamReader(System.in));
            String type_of_object = type_scanner.readLine();
            line_pos += 5;
            if (type_of_object.replaceAll(" ","").toLowerCase().equals("string")){
                System.out.println("Add the elements to the list (ex: Ken,Jil,Ron)\nNOTE: DIVIDED BY COMMAS");
                BufferedReader elements_scanner = new BufferedReader(new InputStreamReader(System.in));
                String elements_txt = elements_scanner.readLine();
                String[] elements = elements_txt.replaceAll(" ","").split(",");
                for (int i = 0; i < elements.length; i++) {
                    Write2SpecificLine(line_pos + i, "       list.add(" + '"'  + elements[i] + '"' +");\n");
                }
                line_pos+=elements.length;
            }else {
                System.out.println("Add the elements to the list (ex: 1,2,3 or whatever you choose to add)\nNOTE: DIVIDED BY COMMAS ");
                BufferedReader elements_scanner = new BufferedReader(new InputStreamReader(System.in));
                String elements_txt = elements_scanner.readLine();
                String[] elements = elements_txt.split(",");

                for (int i = 0; i < elements.length; i++) {
                    Write2SpecificLine(line_pos + i, "       list.add(" + elements[i] + ");\n");
                }
                line_pos+=elements.length;
            }

            System.out.println("\n[+] List Created\n");

            //test presets non-member or member
            System.out.println("Member or Non-Member Method? ( 0 = Non-Member and 1 = Member) \n");
            BufferedReader preset_scanner = new BufferedReader(new InputStreamReader(System.in));
            String preset_txt = preset_scanner.readLine();

            if (preset_txt.replaceAll(" ","").equals("1")) { //Member
                System.out.println("What type of object does the method return? If it's the same type as " + structure_txt.toString() + " type '0'\n");
                BufferedReader structure2_scanner = new BufferedReader(new InputStreamReader(System.in));
                String structure2_txt = structure2_scanner.readLine();

                if (structure2_txt.toString().replaceAll(" ", "").equals("0")) { //same type as list?
                    Write2SpecificLine(line_pos += 1, "      " + FILENAME + "." + structure_txt.toString() + " new_list = new " + FILENAME + "." + structure_txt.toString() + "();");
                    System.out.println("Name of Member Method (ex: totalcount(list,1)) \n\nNOTE: WRITE THE WHOLE METHOD WITH THE PARAMETERS EX: totalcount(list,'Ned')\n");

                    //Writing Method
                    BufferedReader method_scanner = new BufferedReader(new InputStreamReader(System.in));
                    String method_txt = method_scanner.readLine();
                    Write2SpecificLine(line_pos += 1, "       new_list = "+ FILENAME + "." + method_txt + ";" + "\n" + "     for (int i = 0; i < new_list.size(); i++) {\n" + "            System.out.println(new_list.get(i));\n" + "        }");

                } else {
                    Write2SpecificLine(line_pos += 1, "      " + structure2_txt + " new_list;");
                    System.out.println("Name of Member Method (ex: totalcount(list,1)) \n\nNOTE: WRITE THE WHOLE METHOD WITH THE PARAMETERS EX: totalcount(list,'Ned')\n");

                    //Writing Method
                    BufferedReader method_scanner = new BufferedReader(new InputStreamReader(System.in));
                    String method_txt = method_scanner.readLine();
                    Write2SpecificLine(line_pos += 1, "       new_list = " + FILENAME + "." + method_txt + ";" + "\n" + "       System.out.println(new_list);");
                }
            }
            else{ //Non-member
                System.out.println("What type of object does the method return? If it's the same type as " + structure_txt.toString() + " type '0'\n");
                BufferedReader type2_scanner = new BufferedReader(new InputStreamReader(System.in));
                String type2_txt = type2_scanner.readLine();

                if (!(type2_txt.replaceAll(" ","").equals("0"))){
                    System.out.println("Name of Non - Member Method (ex: addBefore(jil,cris)) \n\nNOTE: WRITE THE WHOLE METHOD WITH THE PARAMETERS EX: addBefore(\"jil\",\"cris\")\n");
                    BufferedReader method_scanner = new BufferedReader(new InputStreamReader(System.in));
                    String method_txt = method_scanner.readLine();
                    Write2SpecificLine(line_pos += 1, "       " + type2_txt +" = list." + method_txt + ";\n" + "        System.out.println(list.get(i));\n");

                }
                else {
                    // Writing Method
                    System.out.println("Name of Non - Member Method (ex: addBefore(jil,cris)) \n\nNOTE: WRITE THE WHOLE METHOD WITH THE PARAMETERS EX: addBefore(\"jil\",\"cris\")\n");
                    BufferedReader method_scanner = new BufferedReader(new InputStreamReader(System.in));
                    String method_txt = method_scanner.readLine();
                    Write2SpecificLine(line_pos += 1, "       list." + method_txt + ";\n" + "        for (int i = 0; i < list.size(); i++) {\n" + "            System.out.println(list.get(i));\n" + "        }");
                }
            }

        }

    }



}


