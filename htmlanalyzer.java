import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HtmlAnalyzer {
    
    private static final int MAX_DEPTH = 3;
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("URL connection error");
            return;
        }
        
        String urlString = args[0];
        
        try {
            String htmlContent = fetchHtmlContent(urlString);
            String result = analyzeHtml(htmlContent);
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println("URL connection error");
        }
    }
    
    private static String fetchHtmlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("HTTP error code: " + responseCode);
        }
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        
        return content.toString();
    }
    
    private static String analyzeHtml(String html) {
        List<String> lines = parseHtmlToLines(html);
        
        if (lines.isEmpty()) {
            return "malformed HTML";
        }
        
        // Verificar se há HTML malformado
        if (!isWellFormed(lines)) {
            return "malformed HTML";
        }
        
        String deepestText = findDeepestText(lines);
        
        if (deepestText != null && !deepestText.isEmpty()) {
            return deepestText;
        }
        
        return "malformed HTML";
    }
    
    private static List<String> parseHtmlToLines(String html) {
        List<String> lines = new ArrayList<>();
        
        String[] parts = html.split("(?=<)|(?<=>)");
        
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                lines.add(part);
            }
        }
        
        return lines;
    }
    
    private static boolean isWellFormed(List<String> lines) {
        Stack<String> tagStack = new Stack<>();
        
        for (String line : lines) {
            if (line.startsWith("<") && line.endsWith(">")) {
                String tag = line.substring(1, line.length() - 1).trim();
                
                if (tag.endsWith("/")) {
                    continue;
                }
                
                if (tag.startsWith("/")) {
                    String tagName = tag.substring(1).trim();
                    
                    if (tagStack.isEmpty()) {
                        return false; 
                    }
                    
                    String expectedTag = tagStack.pop();
                    if (!expectedTag.equals(tagName)) {
                        return false; 
                    }
                } else {
                    String tagName = tag.split("\\s+")[0];
                    
                    if (tag.contains("=") || tag.contains("\"")) {
                        return false;
                    }
                    
                    tagStack.push(tagName);
                }
            }
        }
        
        return tagStack.isEmpty(); 
    }
    
    private static String findDeepestText(List<String> lines) {
        Stack<String> tagStack = new Stack<>();
        int currentDepth = 0;
        List<String> textsAtDepth3 = new ArrayList<>();
        
        for (String line : lines) {
            if (line.startsWith("<") && line.endsWith(">")) {
                String tag = line.substring(1, line.length() - 1).trim();
                
                if (tag.endsWith("/")) {
                    continue;
                }
                
                if (tag.startsWith("/")) {
                    if (!tagStack.isEmpty()) {
                        tagStack.pop();
                        currentDepth--;
                    }
                } else {
                    String tagName = tag.split("\\s+")[0];
                    tagStack.push(tagName);
                    currentDepth++;
                }
            } else {
                // Texto
                String text = line.trim();
                if (!text.isEmpty() && currentDepth == MAX_DEPTH) {
                    textsAtDepth3.add(text);
                }
            }
        }
        
        if (!textsAtDepth3.isEmpty()) {
            return textsAtDepth3.get(0);
        }
        
        return null;
    }
}