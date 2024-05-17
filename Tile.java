import java.util.Arrays;

public class Tile implements Comparable<Tile>{
    int nr;
    String family;

    Tile(int nr, String family){
        this.nr = nr;
        this.family = family;//{"man","pin","sou","dragon","wind"}
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
