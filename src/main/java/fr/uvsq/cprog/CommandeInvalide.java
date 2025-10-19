package fr.uvsq.cprog;

public class CommandeInvalide implements Commande {
  public String execute(Dns dns) {
    return "Commande invalide";
  }
}
