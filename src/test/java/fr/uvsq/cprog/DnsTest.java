package fr.uvsq.cprog;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class DnsTest {
  @Test
  public void testChargementEtRecherche() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("www.uvsq.fr 193.51.31.90", "poste.uvsq.fr 193.51.31.154"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    DnsItem item = dns.getItem(new AdresseIP("193.51.31.90"));
    assertEquals("www.uvsq.fr", item.getNom().toString());
    assertEquals("193.51.31.90", item.getAdresse().toString());
    DnsItem item2 = dns.getItem(new NomMachine("poste.uvsq.fr"));
    assertEquals("193.51.31.154", item2.getAdresse().toString());
  }

  @Test
  public void testGetItemsTriNom() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("poste.uvsq.fr 193.51.31.154", "ecampus.uvsq.fr 193.51.25.12"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    List<DnsItem> items = dns.getItems("uvsq.fr", DnsItem.PAR_NOM);
    assertEquals(2, items.size());
    assertEquals("ecampus.uvsq.fr", items.get(0).getNom().toString());
  }

  @Test
  public void testGetItemsTriIP() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("poste.uvsq.fr 193.51.31.154", "ecampus.uvsq.fr 193.51.25.12"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    List<DnsItem> items = dns.getItems("uvsq.fr", DnsItem.PAR_ADRESSE);
    assertEquals(2, items.size());
    assertEquals("ecampus.uvsq.fr", items.get(0).getNom().toString());
  }

  @Test
  public void testAddItem() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("www.uvsq.fr 193.51.31.90"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    dns.addItem(new AdresseIP("193.51.31.154"), new NomMachine("poste.uvsq.fr"));
    assertEquals("poste.uvsq.fr", dns.getItem(new AdresseIP("193.51.31.154")).getNom().toString());
    List<String> lignes = Files.readAllLines(temp, StandardCharsets.UTF_8);
    assertTrue(lignes.contains("poste.uvsq.fr 193.51.31.154"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemDoublonIP() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("www.uvsq.fr 193.51.31.90"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    dns.addItem(new AdresseIP("193.51.31.90"), new NomMachine("autre.uvsq.fr"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemDoublonNom() throws IOException {
    Path temp = Files.createTempFile("dns", ".txt");
    Files.write(temp, List.of("www.uvsq.fr 193.51.31.90"), StandardCharsets.UTF_8);
    Dns dns = new Dns(temp);
    dns.addItem(new AdresseIP("193.51.31.154"), new NomMachine("www.uvsq.fr"));
  }
}
