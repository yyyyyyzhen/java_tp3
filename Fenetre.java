import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.text.DecimalFormat;

public class Fenetre extends JFrame{
    private JFrame jf;
    private JList rougeList; //pour afficher tous les personnes dans le groupe rouge
    private JList bleuList; //pour afficher tous les personnes dans le groupe bleu
    private JList rougePerson; //pour afficher l'information d'un personne
    private JList bleuPerson; //comme dessus
    private JButton Combat; //"Combat"
    private JButton Soigne; //"Soigne"
    public personnage[] Rouge; //la groupe rouge
    public personnage[] Bleu; //la groupe bleu
    public personnage[] personSelect;
    public int indiceRouge = 0, indiceBleu = 0; //pour indique l'indice de personSelect[]
    public Fenetre(){
	jf = new JFrame("jeu de carte");
	Container container = jf.getContentPane();
	jf.setSize(500, 200); //configure la size 500x200
	jf.setResizable(false);
	jf.setLayout(new GridLayout(2, 1, 10, 10)); //2x1 layout

	Rouge = new personnage[5];
	Bleu = new personnage[5];
	personSelect = new personnage[2];
	giveVie life = new giveVie();
	/*Groupe Rouge*/
	Rouge[0] = new ArcGuerrier("Medicus", life.valeurVie(), 5);  //1eme rouge team guerrier: arc...
	Rouge[1] = new Warrior("Infirmix", life.valeurVie(), 10);     //2er rouge team guerrier: warrior
	Rouge[2] = new Warrior("Escrimus", life.valeurVie(), 10);         //3er rouge team guerrier: warrior
	Rouge[3] = new soigneur("Archeus", life.valeurVie(), 5);      //4er rouge team soigneur
	Rouge[4] = new paladin("Paladus", life.valeurVie(), 5, 5);     //5er rouge team paladin	
	/*Groupe Bleu*/
	Bleu[0] = new ArcGuerrier("Medicus", life.valeurVie(), 5);      //1eme bleu team guerrier: arc...
	Bleu[1] = new Warrior("Infirmix", life.valeurVie(), 10);         //2er bleu team guerrier: warrior
	Bleu[2] = new Warrior("Escrimus", life.valeurVie(), 10);        //3er bleu team guerrier: warrior
	Bleu[3] = new soigneur("Archeus", life.valeurVie(), 5);         //4er bleu team soigneur
	Bleu[4] = new paladin("Paladus", life.valeurVie(), 5, 5);       //5er bleu team paladin
	/*1er panel: 1x4 layout*/
	JPanel jp1 = new JPanel(new GridLayout(1, 4)); 
	rougeList = new JList();
	ListModel rougeModel = new DefaultComboBoxModel(new String[]{
		Rouge[0].nom,
		Rouge[1].nom,
		Rouge[2].nom,
		Rouge[3].nom,
		Rouge[4].nom
	    });
	rougeList.setModel(rougeModel);
	rougeList.addListSelectionListener(new rlHandler()); //ajouter ListSelectionListener pour rougeList
	rougeList.setPreferredSize(new java.awt.Dimension(50, 100)); //configure la size 50x100 de la liste
	//rougeList.setSelectedIndex(1); //configurer tacite selection
	Combat = new JButton("Combat");
	Combat.addActionListener(new CombatHandler()); //ajouter listener pour bouton Combat
	Soigne = new JButton("Soigne");
	Soigne.addActionListener(new SoigneHandler()); //ajouter listener pour bouton Soigne
	bleuList = new JList();
	ListModel bleuModel = new DefaultComboBoxModel(new String[]{
		Bleu[0].nom,
		Bleu[1].nom,
		Bleu[2].nom,
		Bleu[3].nom,
		Bleu[4].nom
	    });
	bleuList.setModel(bleuModel);
	bleuList.addListSelectionListener(new blHandler()); //ajouter ListSelectionListener pour bleuList
	bleuList.setPreferredSize(new java.awt.Dimension(50, 100)); //configure la size 50x100 de la liste
	//bleuList.setSelectedIndex(1); //configurer tacite selection
	jp1.add(rougeList); //ajouter rouge list dans le 1er panel
	jp1.add(Combat); //ajouter bouton "Combat"
	jp1.add(Soigne); //ajouter bouton "Soigne"
	jp1.add(bleuList); //ajouter bleu list dans le 1er panel
	container.add(jp1); //ajouter 1er panel dans container
	/*2eme panel: 1x2 layout*/
	JPanel jp2 = new JPanel(new GridLayout(1, 2));
	rougePerson = new JList();
	ListModel rpModel = new DefaultComboBoxModel(new String[]{
		"Medicus(ArcGuerrier)",
		"PV: 61",
		"point d'attaque: 5"
	    });
	rougePerson.setModel(rpModel);
	rougePerson.setPreferredSize(new java.awt.Dimension(125, 75)); //configure la size 125x75 de la liste
	bleuPerson = new JList();
	ListModel bpModel = new DefaultComboBoxModel(new String[]{
		"Medicus(ArcGuerrier)",
		"PV: 61",
		"point d'attaque: 5"
	    });
	bleuPerson.setModel(bpModel);
	bleuPerson.setPreferredSize(new java.awt.Dimension(125, 75)); //configure la size 125x75 de la liste
	jp2.add(rougePerson); //ajouter rouge personne liste dans le 2eme panel
	jp2.add(bleuPerson); //ajouter bleu personne liste dans le 2eme panel
	container.add(jp2); //ajouter 2eme panel dans container
	jf.setVisible(true);
	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void setSpecial(personnage p, String AouS, String supplementaire){
	if(p.getClass() == ArcGuerrier.class){
	    AouS = "points d'attaque: " + ((ArcGuerrier)p).point_attaque;
	    supplementaire = "";
	}
	else if(p.getClass() == Warrior.class){
	    AouS = "points d'attaque: " + ((Warrior)p).point_attaque;
	    supplementaire = "";
	}
	else if(p.getClass() == soigneur.class){
	    AouS = "points  de soin: 5";
	    supplementaire = "";
	}
	else{
	    AouS = "points  de soin: 5";
	    supplementaire = "points d'attaque: " + ((paladin)p).p_attaque;
	}
    }
    public void afficherRougeList(personnage p, String AouS, String supplementaire){
	getInfo i = new getInfo();
	if(AouS == null || supplementaire == null){
	    System.out.println("Erreur");
	}
	else{
	    rougePerson.setListData(new String[]{
		    p.nom + "(" + i.allInfo(p) + ")",
		    "PV: " + p.vie,
		    AouS,
		    supplementaire
		});
	}
    }
    public void afficherBleuList(personnage p, String AouS, String supplementaire){
	getInfo i = new getInfo();
	if(AouS == null || supplementaire == null){
	    System.out.println("Erreur");
	}
	else{
	    bleuPerson.setListData(new String[]{
		    p.nom + "(" + i.allInfo(p) + ")",
		    "PV: " + p.vie,
		    AouS,
		    supplementaire
		});
	}
    }
    private class rlHandler implements ListSelectionListener{
	@Override
	public void valueChanged(ListSelectionEvent e){
	    String AouS = new String("");
	    String supplementaire = new String("");
	    int indices = rougeList.getSelectedIndex();
	    if(personSelect[0] == null){
		personSelect[0] = Rouge[indices];
		indiceRouge = 0;
	    }
	    else{
		personSelect[1] = Rouge[indices];
		indiceRouge = 1;
	    }
	    setSpecial(Rouge[indices], AouS, supplementaire);
	    afficherRougeList(Rouge[indices], AouS, supplementaire);
	}
    }
    private class blHandler implements ListSelectionListener{
	@Override
	public void valueChanged(ListSelectionEvent e){
	    String AouS = new String("");
	    String supplementaire = new String("");
	    int indices = bleuList.getSelectedIndex();
	    if(personSelect[0] == null){
		personSelect[0] = Bleu[indices];
		indiceBleu = 0;
	    }
	    else{
		personSelect[1] = Bleu[indices];
		indiceBleu = 1;
	    }
	    setSpecial(Bleu[indices], AouS, supplementaire);
	    afficherBleuList(Bleu[indices], AouS, supplementaire);
	}
    }

    private class CombatHandler implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
	    String AouS = new String("");
	    String supplementaire = new String("");
	    if(personSelect[0].getClass() == soigneur.class)
		JOptionPane.showMessageDialog(jf, "soigneur ne peut pas faire du combat!");
	    else{
		if(personSelect[0].getClass() == ArcGuerrier.class){
		    ((ArcGuerrier)personSelect[0]).combattre((ArcGuerrier)personSelect[0], personSelect[1], 10); //ici, distance soit 10m
		}
		else if(personSelect[0].getClass() == Warrior.class){
		    ((Warrior)personSelect[0]).combattre((Warrior)personSelect[0], personSelect[1], 10);
		}
		else{
		    ((paladin)personSelect[0]).combattre((paladin)personSelect[0], personSelect[1]);
		}	
	    }
	    setSpecial(personSelect[indiceRouge], AouS, supplementaire);
	    afficherRougeList(personSelect[indiceRouge], AouS, supplementaire);
	    setSpecial(personSelect[indiceBleu], AouS, supplementaire);
	    afficherBleuList(personSelect[indiceBleu], AouS, supplementaire);
	}
    }
    private class SoigneHandler implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
	    String AouS = new String("");
	    String supplementaire = new String("");
	    if(personSelect[0].getClass() == soigneur.class){
		((soigneur)personSelect[0]).soigner((soigneur)personSelect[0], personSelect[1]);
	    }
	    else if(personSelect[0].getClass() == paladin.class){
		((paladin)personSelect[0]).soigner((paladin)personSelect[0], personSelect[1]);
	    }
	    else{
		JOptionPane.showMessageDialog(jf, "seulement soigneur ou paladin peut soigner!");
	    }
	    setSpecial(personSelect[indiceRouge], AouS, supplementaire);
	    afficherRougeList(personSelect[indiceRouge], AouS, supplementaire);
	    setSpecial(personSelect[indiceBleu], AouS, supplementaire);
	    afficherBleuList(personSelect[indiceBleu], AouS, supplementaire);
	}
    }
    public static void main(String[] args){
	// TODO Auto-generated method stub
	new Fenetre();
    }
}
