import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class RechercheAvancee extends Recherche {
	private int prixMin;

	public void setPrixMin(int value) {
		this.prixMin = value;
	}

	public int getPrixMin() {
		return this.prixMin;
	}

	private int prixMax;

	public void setPrixMax(int value) {
		this.prixMax = value;
	}

	public int getPrixMax() {
		return this.prixMax;
	}

	public RechercheAvancee(SiteWeb site, ArrayList<Rubrique> rubrique,ArrayList<String> keywords,int prixMin,int prixMax) {

		this.id = UUID.randomUUID().toString();
		this.keywords=keywords;
		this.site=site;
		this.listeRubrique=rubrique;
		this.prixMin=prixMin;
		this.prixMax=prixMax;
	}
	
	
	public HashMap<Integer,Annonce> run() throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException{
		HashMap<Integer,Annonce> resultats = new HashMap<Integer,Annonce>();
		LocalDate today = LocalDate.now();
		for(int rubrique = 0 ; rubrique<listeRubrique.size();rubrique++) {
			if(listeRubrique.get(rubrique).getDateLastUpdate().until(today, ChronoUnit.DAYS)>1) {
				listeRubrique.get(rubrique).majAnnonce();
			}
			for(int annonce : listeRubrique.get(rubrique).listeAnnonce.keySet()) {
				for(int keyword = 0; keyword<keywords.size();keyword++) {
					if((listeRubrique.get(rubrique).listeAnnonce.get(annonce).getTitre().contains(keywords.get(keyword)) || listeRubrique.get(rubrique).listeAnnonce.get(annonce).getDescription().contains(keywords.get(keyword))) && ( prixMin <= listeRubrique.get(rubrique).listeAnnonce.get(annonce).getPrix() &&  listeRubrique.get(rubrique).listeAnnonce.get(annonce).getPrix() <= prixMax) ) {
						resultats.put(listeRubrique.get(rubrique).listeAnnonce.get(annonce).getId(), listeRubrique.get(rubrique).listeAnnonce.get(annonce));
					}
				}

			}
		}
		
		return resultats;

	}
}
