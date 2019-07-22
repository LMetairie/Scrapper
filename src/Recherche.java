import java.util.Set;
import java.util.HashSet;

public class Recherche {
   /**
    * <pre>
    *           0..*     fait des     1..1
    * Recherche ------------------------- Utilisateur
    *           recherche        &lt;       utilisateur
    * </pre>
    */
   private Utilisateur utilisateur;
   
   public void setUtilisateur(Utilisateur value) {
      this.utilisateur = value;
   }
   
   public Utilisateur getUtilisateur() {
      return this.utilisateur;
   }
   
   /**
    * <pre>
    *           0..*     fait dans     1..*
    * Recherche ------------------------- Rubrique
    *           recherche        &gt;       rubrique
    * </pre>
    */
   private Set<Rubrique> rubrique;
   
   public Set<Rubrique> getRubrique() {
      if (this.rubrique == null) {
         this.rubrique = new HashSet<Rubrique>();
      }
      return this.rubrique;
   }
   
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   
   private String keywords;
   
   public void setKeywords(String value) {
      this.keywords = value;
   }
   
   public String getKeywords() {
      return this.keywords;
   }
   
   }