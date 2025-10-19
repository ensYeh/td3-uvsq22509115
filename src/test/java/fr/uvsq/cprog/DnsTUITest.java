package fr.uvsq.cprog;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;

public class DnsTUITest {
  @Test
  public void testCommandeQuitter() {
    System.setIn(new ByteArrayInputStream("quit\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeQuitter);
  }

  @Test
  public void testCommandeAjouter() {
    System.setIn(new ByteArrayInputStream("add 192.168.0.1 test.uvsq.fr\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeAjouter);
  }

  @Test
  public void testCommandeLister() {
    System.setIn(new ByteArrayInputStream("ls uvsq.fr\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeLister);
  }

  @Test
  public void testCommandeRechercheNom() {
    System.setIn(new ByteArrayInputStream("193.51.31.90\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeRechercheNom);
  }

  @Test
  public void testCommandeRechercheIP() {
    System.setIn(new ByteArrayInputStream("www.uvsq.fr\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeRechercheIP);
  }

  @Test
  public void testCommandeInvalide() {
    System.setIn(new ByteArrayInputStream("foobar\n".getBytes()));
    DnsTUI tui = new DnsTUI();
    Commande cmd = tui.nextCommande();
    assertTrue(cmd instanceof CommandeInvalide);
  }
}
