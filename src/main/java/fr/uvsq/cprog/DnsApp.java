package fr.uvsq.cprog;

import java.nio.file.Paths;
import java.util.Properties;
import java.io.FileInputStream;

public class DnsApp {
  public static void main(String[] args) throws Exception {
    Properties props = new Properties();
    props.load(new FileInputStream("config.properties"));
    String chemin = props.getProperty("dns.file");
    Dns dns = new Dns(Paths.get(chemin));
    DnsTUI tui = new DnsTUI();
    boolean fini = false;
    while (!fini) {
      Commande cmd = tui.nextCommande();
      String res = cmd.execute(dns);
      tui.affiche(res);
      if (cmd instanceof CommandeQuitter) {
        fini = true;
      }
    }
  }
}
