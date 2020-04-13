import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

class streamLog{
    public static void main(String args[]){
        File fileR;
        try{
            if (args.length == 1) {
                fileR = new File(args[0]);
            } else {
                fileR = new File("./List.txt");
            }
            if (checkBeforeReadfile(fileR)){
                BufferedReader br = new BufferedReader(new FileReader(fileR));
                String outputFile = "./out/"+fileR.getName();
                File fileW = new File(outputFile);
                FileOutputStream fos = new FileOutputStream(fileW);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                PrintWriter pw = new PrintWriter(osw);
                String str;
                List<String> structures = new ArrayList<>();
                while((str = br.readLine()) != null){
                    String regex = ",";
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(str);
                    int matchNum = 0;

                    while (m.find()) matchNum++;

                    int index = str.indexOf(regex);
                    if (matchNum > 0 && index > 0) {
                        String strName = str.substring(0, index);
                        if (structures.contains(strName)) {
                            System.out.println("contained.");
                            continue;
                        }
                        structures.add(strName);
                    }
                    pw.println(str);
                }

                br.close();
                pw.close();
                System.out.println("finished");
            } else {
                System.out.println("ファイルが見つからないか開けません");
            }
        } catch (FileNotFoundException e) {
                System.out.println(e);
        } catch(IOException e) {
                System.out.println(e);
        }
    }
    private static boolean checkBeforeReadfile(File file){
        if (file.exists()){
            if (file.isFile() && file.canRead()){
                return true;
            }
        }
        return false;
    }
}