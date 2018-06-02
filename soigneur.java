class soigneur extends personnage /*implements Soin*/{
    public int point_soigner;
    soigneur(){/*non parametre constructor*/}
    soigneur(String n, int v, int s){
		super(n, v);
		point_soigner = s;
    }
    public void soigner(soigneur s, personnage b){
    	try{
			if(s.vie > s.point_soigner){
			    b.vie += s.point_soigner;
			    s.vie -= s.point_soigner;
			}
			else{
			    b.vie += s.vie;
			    s.vie = 0;
			}
    	}
    	catch(ArithmeticException e6){
    		System.out.println("erreur dans la méthode soigner de la class soigneur");
    	}
    }
    public void SoinShow(){
		System.out.println("Person: " + this.nom);
		System.out.println("PV: " + this.vie);
		System.out.println("Points de soin: " + point_soigner);
    }
}
