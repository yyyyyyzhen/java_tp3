class paladin extends personnage /*implements CetS*/{ 
    public int p_attaque, p_soigner;
    paladin(){/*non parametre constructor*/}
    paladin(String n, int v, int a, int s){
		super(n, v);
		p_attaque = a;
		p_soigner = s;
    }
    public void combattre(paladin a, personnage p){
    	try{
			if(a.p_attaque >= p.vie)
			    p.vie = 0;
			else
			    p.vie -= a.p_attaque;
    	}
    	catch(ArithmeticException e3){
    		System.out.println("erreur dans la méthode combattre de la class paladin");
    	}
    }
    public void soigner(paladin s, personnage b){
    	try{
			if(s.vie > s.p_soigner){
			    b.vie += s.p_soigner;
			    s.vie -= s.p_soigner;
			}
			else{
			    b.vie += s.vie;
			    s.vie = 0;
			}
    	}
    	catch(ArithmeticException e5){
    		System.out.println("erreur dans la méthode soigner de la class paladin");
    	}
    }
    public void PalaShow(){
		System.out.println("Person: " + this.nom);
		System.out.println("PV: " + this.vie);
		System.out.println("Points d'attaque: " + p_attaque);
		System.out.println("Points de soin: " + p_soigner);
    }
}