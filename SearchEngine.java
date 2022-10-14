import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String[] list = new String[100];

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());
            
            
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list[list.length] = parameters[1];
                    return String.format(parameters[1], "is added to the list");
                }

            }
            else if (url.getPath().contains("/search")){
                String[] parameters = url.getQuery().split("=");
                //String[] searchList = new String[100];
                int count = 0;
                if (parameters[0].equals("s")) {
                    for(int i=0; i<list.length; i+=0){
                        if(list[i].contains(parameters[1])==true){
                            count +=1;
                        }
                    }

                    
                }
                String[] searchList = new String[count];
                int count2 = 0;
                if (parameters[0].equals("s")) {
                    for(int i=0; i<list.length; i+=0){
                        if(list[i].contains(parameters[1])==true){
                            searchList[count2] = list[i];
                            count2 += 1;
                        }
                String result = searchList.toString();
            }


            return "404 Not Found!";
        }
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
