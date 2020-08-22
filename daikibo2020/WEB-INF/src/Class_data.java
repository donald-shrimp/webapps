public class Class_data{
    private int std_id;
    private String[] class_name = new String[6];

    public void setData(int std_id, String[] class_name){
        this.std_id = std_id;
        this.class_name = class_name;
    }


    public int getStd_id(){
        return std_id;
    }

    public String[] getNames(){
        return class_name;
    }

}