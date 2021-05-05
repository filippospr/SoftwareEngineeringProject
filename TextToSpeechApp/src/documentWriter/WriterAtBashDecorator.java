package documentWriter;

public class WriterAtBashDecorator extends WriterDecorator implements DocumentWriter {
	
	public WriterAtBashDecorator(DocumentWriter writer) {
		super(writer);
	}
	@Override
	 public String encrypt(String word){
        String result="";
        for(char c:word.toCharArray()){
        	if(c >= 'a' && c <= 'z'){
                result+= (char) ('a' + ('z' - c));
            }
            else if(c >= 'A' && c <= 'Z'){
                result+= (char) ('A' + ('Z' - c));
            }
            else if(c >= 'á' && c <= 'ù'){
            	result+= (char) ('á' + ('ù' - c));
            }
            else if(c >= 'Á' && c <= 'Ù'){
            	result+= (char) ('Á' + ('Ù' - c));
            }
            else{
                result+=c;
            }
        }
        return result;
    }

}
