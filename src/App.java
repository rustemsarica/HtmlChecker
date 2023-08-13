
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        String html = "<div><p>Test <strong>HTML</strong><p> test</p></div>";

        Pattern tagPattern = Pattern.compile("<[^>]+>");
        Matcher matcher = tagPattern.matcher(html);

        Stack<String> tags = new Stack<>();

        while (matcher.find()) {
            if(matcher.group().matches("</[^>]+>")){
                if(tags.size()==0){
                    System.out.println("Can't use this tag: " + matcher.group());
                }else{
                    String lastTag = tags.pop();
                    if(lastTag.substring(1, lastTag.length()-1).equals(matcher.group().substring(2, matcher.group().length()-1))){
                        System.out.println("Close tag: "+matcher.group());
                    }else{
                        System.out.println("---Html error: "+ matcher.group());
                        break;
                    }
                }
            }else if(matcher.group().matches("<[^>]+>")){
                tags.push(matcher.group());           
                System.out.println("Open tag: "+matcher.group());
            }                       
        }
    }
}
