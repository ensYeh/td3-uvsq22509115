package fr.uvsq.cprog;

public class CommandeAjouter implements Commande {
  private final String ip;
  private final String nom;

  public CommandeAjouter(String ip, String nom) {
    this.ip = ip;
    this.nom = nom;
  }

  public String execute(Dns dns) {
    try {
      dns.addItem(new AdresseIP(ip), new NomMachine(nom));
      return "Ajouté";
    } catch (Exception e) {
      return "Erreur : " + e.getMessage();
    }
  }
}
