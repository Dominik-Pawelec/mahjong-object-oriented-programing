public class Tile {
    int nr;
    String family;

    Tile(int nr, String family){
        this.nr = nr;
        this.family = family;
    }

    public int Get_Nr(){ return nr; }
    public String Get_family(){ return family; }

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
}
