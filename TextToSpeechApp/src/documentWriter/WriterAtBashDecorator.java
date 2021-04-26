package documentWriter;

public class WriterAtBashDecorator extends WriterDecorator implements DocumentWriter {
	
	public WriterAtBashDecorator(DocumentWriter writer) {
		super(writer);
	}
	@Override
	 public String encrypt(String word){
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

}
