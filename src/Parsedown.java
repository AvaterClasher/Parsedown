import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/*
 * The edge cases that are currently being handled 
 * 1) Handling Images: It checks for the presence of ! before the opening bracket, indicating an image link. The code skips such cases.
 * 2) Correctly Skips Invalid Patterns: If there is an incomplete link (missing closing bracket or opening parenthesis), the code skips the incomplete pattern and continues searching for the next link.
 * 3) Handles Files with No Links: If the Markdown content doesn't contain any links, the code won't encounter any issues and will return an empty list.
 */

public class Parsedown {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1) {
                break;
            }
            if(nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '!') {
                break;
            }
            int nextCloseBracket = markdown.indexOf("](", nextOpenBracket);
            if(nextCloseBracket == -1) {
                break;
            }
            int closeParen = markdown.indexOf(")", nextCloseBracket);
            toReturn.add(markdown.substring(nextCloseBracket + 2, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}