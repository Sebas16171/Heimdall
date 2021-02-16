package Scripts;

public class Session {

    private int id;
    private String username, name, last_name;

    public Session(int id, String username, String name, String last_name){
        this.id = id;
        this.username = username;
        this.name = name;
        this.last_name = last_name;
    }

    public int getID(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return last_name;
    }
    
}
