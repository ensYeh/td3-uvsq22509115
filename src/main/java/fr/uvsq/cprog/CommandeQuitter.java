package fr.uvsq.cprog;

public class CommandeQuitter implements Commande {
  public String execute(Dns dns) {
    return "Au revoir !";
  }
}
