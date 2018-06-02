class guerrier extends personnage{
    public int point_attaque;
    guerrier(){/*non parametre constructor*/}
    guerrier(String n, int v, int a){
		super(n, v);
		point_attaque = a;
    }
}