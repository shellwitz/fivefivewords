import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fivefivewords {

    private List<String> Wordlist;

    public fivefivewords() throws IOException {
        BufferedReader buffi = new BufferedReader(new FileReader("wordlesFull.txt"));
        Wordlist = new ArrayList<>();
        String line;
        while ((line = buffi.readLine()) != null ) {
            if(line.length() == 5)
            Wordlist.add(line);
        }

    }
    public  ArrayList<String> SetUpSearchAndSearch() throws Exception {


        ArrayList<String> foundWords = new ArrayList<>();
        //foundWords.add("fjord");

        Search(foundWords, 0);

        return foundWords;
    }

    public boolean test(ArrayList<String> FoundWords){
        if(FoundWords.size() == 5){
            ArrayList<Character> Letters = new ArrayList<>();

            for(int i = 0; i< 5; i++){
                for(int j = 0; j< 5; j++){
                    if(Letters.contains(FoundWords.get(i).charAt(j))){
                        return false;
                    }
                    Letters.add(FoundWords.get(i).charAt(j));
                }
            }
            return true;
        }
        return false;
    }
    public void Search(ArrayList<String> FoundWords, int LeftAt) throws Exception {
        if(FoundWords.size() == 5){
            return;
        }

        for (int i = LeftAt+1; i< Wordlist.size(); i++){
         if( DoesStringHaveCharsInCommonWithStringList(Wordlist.get(i), FoundWords)){
             continue;
         } else{
             FoundWords.add(Wordlist.get(i));
           Search(FoundWords, i);

             if(FoundWords.size() == 5){
                 return;
             }

         }
        }
        FoundWords.remove(FoundWords.size()-1);
    }
    //first step
private void removeAllAnas(){
        for (int i = 0; i< Wordlist.size(); i++){
           removeAnagramm(Wordlist.get(i), i);
        }
}

    private void removeAnagramm(String possibleAnagramm, int i) {
        Wordlist.remove(possibleAnagramm);
        for (int j = i+1; i < Wordlist.size()-1; i++) {
            if (CheckAna(possibleAnagramm, Wordlist.get(j))){
                Wordlist.remove(Wordlist.get(j));
            }
        }

    }

    private boolean CheckAna(String Anavll, String OtherAnaVll) {
        char[] Anavllarray = Anavll.toCharArray();
        char[] OtherAnaVllArray = OtherAnaVll.toCharArray();
        for (int i = 0; i < Anavllarray.length; i++) {
         if(! isCharInCharArr(Anavllarray[i], OtherAnaVllArray)){
             return false;
            }
        }
        return  true;
    }
        public void removeNotUniqLetterWord(){
        //ArrayList<String> ToRemove = new ArrayList<>();
            int i = 0;
          while(i< Wordlist.size()){
                if(!hasStringUniqueLetters(Wordlist.get(i))){
                 Wordlist.remove(i);
                } else{
                    i++;
                }
            }
        }
    public boolean hasStringUniqueLetters(String potUniq){
        for(int i = 0; i< potUniq.length(); i++){
           for (int j = i+1; j< potUniq.length(); j++){
               if(potUniq.charAt(i) == potUniq.charAt(j)){
                   return false;
               }
           }
        }
        return true;
    }

    private boolean isCharInCharArr(char Yachari, char[] charArr) {
        for(int i = 0; i< charArr.length; i++){
            if(Yachari == charArr[i]){
                return true;
            }
        }
        return false;

    }
    private boolean DoesStringHaveCharsInCommonWithStringList(String stringToCheck, ArrayList<String> StringsTocheckAgainst){
        for (String str: StringsTocheckAgainst){
            if(DoStringsHaveCharsInCommon(stringToCheck, str)){
                return true;
            }
        }
        return false;
    }
    private boolean DoStringsHaveCharsInCommon(String s1, String s2){
        char [] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        for(int i = 0; i< s1.length(); ++i){
            if(isCharInCharArr(s1Arr[i], s2Arr)){
                return true;
            }
        }
        return false;

    }

    public void WriteAnaFreeListTxt() throws IOException {
        File file = new File("AnaFreeWordles.txt");
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i< Wordlist.size(); i++){
            writer.write(Wordlist.get(i));
            writer.write("\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        fivefivewords f = new fivefivewords();
    f.removeNotUniqLetterWord();
    //f.removeAllAnas();
        f.WriteAnaFreeListTxt();
        ArrayList<String> FoundWords = f.SetUpSearchAndSearch();
        System.out.println("The result is: " + f.test(FoundWords));
        for (String s: FoundWords){
            System.out.println(s);
        }

       //System.out.println(f.hasStringUniqueLetters("spoon"));
    }

}
