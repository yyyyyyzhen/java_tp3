class ArcGuerrier extends guerrier /*implements Combat*/{ //certains combattant font plus de dommages lorsqu'ils sont loin
    ArcGuerrier(){/*non parametre constructor*/}
    ArcGuerrier(String n, int v, int a){
    	super(n, v, a);
    }
    public void combattre(ArcGuerrier a, personnage p, int distance){
    	try{
			if(distance < 25){    //pour distance < 25m, proche, normal dommage....
			    if(a.point_attaque >= p.vie)
				p.vie = 0;
			    else
				p.vie -= a.point_attaque;
			}
			else{               
			    a.point_attaque *= 2;   //pour distance > 25m, loin, deux fois dommage!!!
			    if(a.point_attaque >= p.vie)
				p.vie = 0;
			    else
				p.vie -= a.point_attaque;
			}
    	}
    	catch(ArithmeticException e8){
    		System.out.println("erreur dans la méthode combattre de la class ArcGuerrier");
    	}
    }
    public ArcGuerrier creerArc(void){
    	giveVie live = new giveVie();
    	System.out.println("entrer le nom pour ce guerrier: ");
    	Scanner a = new(System.in);
    	String nom = a.nextLine();
    	ArcGuerrier arc = new ArcGuerrier(nom, live.valeurVie(), 5);
    	return arc;
    }
    public void ArcShow(){
	System.out.println("Person: " + this.nom);
	System.out.println("PV: " + this.vie);
	System.out.println("Points d'attaque: " + this.point_attaque);
    }
    
}
