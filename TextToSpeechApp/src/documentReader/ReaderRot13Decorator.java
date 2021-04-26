package documentReader;
import java.util.ArrayList;

public class ReaderRot13Decorator extends ReaderDecorator {

	 public ReaderRot13Decorator(DocumentReader reader)    {
	        super(reader);
	    }
	@Override
	public String decrypt(String word){
        String result="";
        for(char c:word.toCharArray()){
        	char t = c;
            if(Character.isLowerCase(c)){
            	t = (char) ('a' + (c-'a'+ 13)%26);
            }else if(Character.isUpperCase(c)){
            	t = (char) ('A' + (c-'A'+ 13)%26);
            }                  
            result+=t;
        }
        
        return result;
    }
    public static void main(String[] args) {
		String test = "Mpekaltsa";
		ReaderRot13Decorator dec = new ReaderRot13Decorator(new WordReader("bankai.docx"));
		ArrayList<String> momo = dec.read();
		//for(String i: momo) {
		//	System.out.println(dec.decrypt(i));
		//}
	}

}
