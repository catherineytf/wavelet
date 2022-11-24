import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        ArrayList<String> substringList = new ArrayList<>();
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list.add(parameters[1]);
                    return parameters[1] +" " + "is added to the list";
                }
            }
            else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for(String s: list){
                        if(s.contains(parameters[1])){
                            substringList.add(s);
                        }
                    }
                    String result = substringList.toString();
                    return result;
                }
            }
            return "404 Not Found!";
        }
}


class SearchEngine {
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }

    
}
