package fr.uvsq.cprog;

public class CommandeLister implements Commande {
  private final String domaine;
  private final boolean parIP;

  public CommandeLister(String domaine, boolean parIP) {
    this.domaine = domaine;
    this.parIP = parIP;
  }

  public String execute(Dns dns) {
    var cmp = parIP ? DnsItem.PAR_ADRESSE : DnsItem.PAR_NOM;
    var items = dns.getItems(domaine, cmp);
    if (items.isEmpty()) return "Aucune machine trouvée";
    StringBuilder sb = new StringBuilder();
    for (DnsItem item : items) {
      sb.append(item.toString()).append("\n");
    }
    return sb.toString().trim();
  }
}
