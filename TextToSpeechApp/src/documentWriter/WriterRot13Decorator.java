package documentWriter;

public class WriterRot13Decorator extends WriterDecorator implements DocumentWriter {
	
	public WriterRot13Decorator(DocumentWriter writer) {
		super(writer);
	}
	public String encrypt(String word){
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
