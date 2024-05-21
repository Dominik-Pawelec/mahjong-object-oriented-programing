import java.util.Arrays;

public final class Tile implements Comparable<Tile>{
    int nr; //make private
    String family;


    Tile(int nr, String family){
        this.nr = nr;
        this.family = family;//{"man","pin","sou","dragon","wind"}
    }
    Tile(String xs){
        if(xs.length() == 1){
            switch(xs){
                case "E":{
                    this.nr = 1;
                    family = "wind";break;
                }
                case "S":{
                    this.nr = 2;
                    family = "wind";break;
                }
                case "W":{
                    this.nr = 3;
                    family = "wind";break;
                }
                case "N":{
                    this.nr = 4;
                    family = "wind";break;
                }
                case "R":{
                    this.nr = 1;
                    family = "dragon";break;
                }
                case "B":{
                    this.nr = 2;
                    family = "dragon";break;
                }
                case "G":{
                    this.nr = 3;
                    family = "dragon";break;
                }
            }
            return;
        }
        this.nr = xs.charAt(0)-'0';
        
        char temp = xs.charAt(1);

        switch(temp){
            case 'm':
                this.family = "man";break;
            case 'p':
                this.family = "pin";break;
            case 's':
                this.family = "sou";break;
            default:
                this.family = "wont hapen";break;
        }
    }

    public int getNr(){ return nr; }
    public String getFamily(){ return family; }

    @Override
    public String toString(){

        if(family == "wind"){
            String[] wind_list = new String[]{"E","S","W","N"};
            return wind_list[nr-1];
        }
        if(family == "dragon"){
            String[] dragon_list = new String[]{"R","B","G"};
            return dragon_list[nr-1];
        }
        return Integer.toString(nr) + family.charAt(0);
    }
    public int compareTo(Tile t){
        String[] types = {"man","pin","sou","dragon","wind"};
        int this_val = this.nr + 10*(Arrays.asList(types).indexOf(this.getFamily()));
        int t_val = t.nr + 10*(Arrays.asList(types).indexOf(t.getFamily()));

        return this_val - t_val;
    }
}
