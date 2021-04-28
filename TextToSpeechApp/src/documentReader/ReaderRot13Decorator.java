package documentReader;

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
}
