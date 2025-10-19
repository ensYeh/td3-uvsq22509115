package fr.uvsq.cprog;

public class CommandeRechercheIP implements Commande {
  private final String nom;

  public CommandeRechercheIP(String nom) {
    this.nom = nom;
  }

  public String execute(Dns dns) {
    try {
      DnsItem item = dns.getItem(new NomMachine(nom));
      return item.getAdresse().toString();
    } catch (Exception e) {
      return "Nom inconnu";
    }
  }
}
