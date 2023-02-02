import java.util.*;
import java.io.*;

public class Main {
    public static void main (String args[])
    {
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"ID", "USERNAME", "EMAIL", "PASSWORD", "HASH_PASSWORD", "IP_ADDRESS"});

        try{    
                //reading user_email_hash file 
                BufferedReader br1 = new BufferedReader(new FileReader("user_email_hash.1m.tsv"));
                String line1;
                br1.readLine();
                while ((line1 = br1.readLine()) != null)   
                {
                    String[] token = line1.split("\t");
                    data.add(new String[] {token[0], token[1], token[2], token[3], null, null});
                } 
                
                //reading Ip file and compairing id 
                BufferedReader br2 = new BufferedReader(new FileReader("ip_1m.tsv"));
                String line2;
                br2.readLine();
                while ((line2 = br2.readLine()) != null)   
                {
                    String[] token = line2.split("\t");
                    for (String[] s : data)
                    {
                        if(s[0].equals(token[0]))
                        {
                            s[5] = token[1];
                            break;
                        }
                    }
                }
                
                //compairing email with plain_32m  file 
                BufferedReader br3 = new BufferedReader(new FileReader("plain_32m.tsv"));
                String line3;
                while ((line3 = br3.readLine()) != null)   
                {
                    String[] token = line3.split("\t");
                    for (String[] s : data)
                    {
                        
                        if(s[2].equals(token[0]))
                        {
                            //System.out.println(s[2] +"======\n" +token[0]);
                            s[4] = token[1];
                            break;
                        }
                    }
                } 
            } catch (IOException e) {
                System.out.println("Error : Error reading file name: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }

            //write data arrylist to output file 
        try (FileWriter fw = new FileWriter("output.tsv"))
            {
                for (String[] d : data) 
                {
                    System.out.print(String.join("\t", d) + System.lineSeparator()); 
                    //fw.write(String.join("\t", d) + System.lineSeparator());

                }
            } catch (IOException e) {
                System.out.println("Error : Error reading file name: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }


            
    }
    
}
