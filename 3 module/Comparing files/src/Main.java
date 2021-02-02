import java.io.*;

public class Main {

    public static void main(String[] args) {
        File file1 = new File("D:\\github\\src\\first.txt");
        File file2 =  new File("D:\\github\\src\\second.txt");
        try {
            BufferedReader reader1 =  new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String str1;
            String str2;
            while ((str1 = reader1.readLine()) != null && (str2 = reader2.readLine()) != null) {
                for (int i = 0; i < Math.max(str1.length(), str2.length()); i++) {
                    if (i > str1.length()-1) System.out.println(str2.charAt(i));
                    else if (i > str2.length()-1) System.out.println(str1.charAt(i));
                    else {
                        if (str1.charAt(i) != str2.charAt(i) && str2.length() > str1.length()) System.out.println(str2.charAt(i));
                        else if (str1.charAt(i) != str2.charAt(i) && str2.length() < str1.length()) System.out.println(str2.charAt(i));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
