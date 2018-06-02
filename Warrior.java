class Warrior extends guerrier /*implements Combat*/{ //ils ne peuvent pas font dommages lorsqu'ils sont loin
    Warrior(){/*non parametre constructor*/}
    Warrior(String n, int v, int a){
		super(n, v, a);
    }
    public void combattre(Warrior a, personnage p, int distance){
		if(distance > 10)
		    System.out.println("MISS!!!!!!!!");
		else{
			try{
			    if(a.point_attaque >= p.vie)
				p.vie = 0;
			    else
				p.vie -= a.point_attaque;
			}
			catch(ArithmeticException e7){
				System.out.println("erreur dans la méthode combattre de la class Warrior");
			}
		}
    }
    public void WarShow(){
		System.out.println("Person: " + this.nom);
		System.out.println("PV: " + this.vie);
		System.out.println("Points d'attaque: " + this.point_attaque);
    }
}