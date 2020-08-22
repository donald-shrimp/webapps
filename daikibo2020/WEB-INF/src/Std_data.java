public class Std_data{
    private int id;
    private int std_id;
    private String result;

    public void setData(int id, int std_id, String result){
        this.id = id;
        this.std_id = std_id;
        this.result = result;
    }

    public int getId(){
        return id;
    }

    public int getStd_id(){
        return std_id;
    }

    public String getResult(){
        return result;
    }

}