package fr.uvsq.cprog;

import java.util.Scanner;

public class DnsTUI {
  private final Scanner scanner;

  public DnsTUI() {
    this.scanner = new Scanner(System.in);
  }

  public String lireLigne() {
    System.out.print("> ");
    return scanner.nextLine();
  }

  public Commande nextCommande() {
    String ligne = lireLigne().trim();
    if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
      return new CommandeQuitter();
    }
    if (ligne.startsWith("add ")) {
      String[] parts = ligne.split(" ", 3);
      if (parts.length == 3) {
        return new CommandeAjouter(parts[1], parts[2]);
      }
    }
    if (ligne.startsWith("ls")) {
      boolean parIP = ligne.contains("-a");
      String[] parts = ligne.split(" ");
      String domaine = parts[parts.length - 1];
      return new CommandeLister(domaine, parIP);
    }
    if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
      return new CommandeRechercheNom(ligne);
    }
    if (ligne.contains(".")) {
      return new CommandeRechercheIP(ligne);
    }
    return new CommandeInvalide();
  }

  public void affiche(String message) {
    System.out.println(message);
  }
}
