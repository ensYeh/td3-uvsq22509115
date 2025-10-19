package fr.uvsq.cprog;

public class CommandeRechercheNom implements Commande {
  private final String ip;

  public CommandeRechercheNom(String ip) {
    this.ip = ip;
  }

  public String execute(Dns dns) {
    try {
      DnsItem item = dns.getItem(new AdresseIP(ip));
      return item.getNom().toString();
    } catch (Exception e) {
      return "IP inconnue";
    }
  }
}
