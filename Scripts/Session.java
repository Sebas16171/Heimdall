package Scripts;

public class Session {

    private int id, crpt_method;
    private String username, name, last_name;

    public Session(int id, String username, String name, String last_name, int crpt_method){
        this.id = id;
        this.username = username;
        this.name = name;
        this.last_name = last_name;
        this.crpt_method = crpt_method;
    }

    public int getID(){
        return this.id;
    }
    public int getCryptMethod(){
        return this.crpt_method;
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
