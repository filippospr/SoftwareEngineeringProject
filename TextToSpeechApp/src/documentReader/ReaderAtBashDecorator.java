package documentReader;

public class ReaderAtBashDecorator extends ReaderDecorator {

    public ReaderAtBashDecorator(DocumentReader reader)    {
        super(reader);
    }

    public String decrypt(String word){
        String result="";
        for(char c:word.toCharArray()){
            if(Character.isLowerCase(c)){
                result+= (char) ('a' + ('z' - c));
            }
            else if(Character.isUpperCase(c)){
                result+= (char) ('A' + ('Z' - c));
            }
            else{
                result+=c;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
		ReaderAtBashDecorator dec = new ReaderAtBashDecorator(new WordReader("bankai.docx"));
		System.out.println(dec.read());
	}
}
