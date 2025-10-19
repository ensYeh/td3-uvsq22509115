package fr.uvsq.cprog;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {
  @Test
  public void testToString() {
    AdresseIP ip = new AdresseIP("193.51.31.90");
    NomMachine nom = new NomMachine("www.uvsq.fr");
    DnsItem item = new DnsItem(ip, nom);
    assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AdresseIP ip1 = new AdresseIP("193.51.31.90");
    NomMachine nom1 = new NomMachine("www.uvsq.fr");
    DnsItem item1 = new DnsItem(ip1, nom1);
    DnsItem item2 = new DnsItem(new AdresseIP("193.51.31.90"), new NomMachine("www.uvsq.fr"));
    DnsItem item3 = new DnsItem(new AdresseIP("193.51.31.91"), nom1);
    assertEquals(item1, item2);
    assertEquals(item1.hashCode(), item2.hashCode());
    assertNotEquals(item1, item3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAdresse() {
    new DnsItem(null, new NomMachine("www.uvsq.fr"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNom() {
    new DnsItem(new AdresseIP("193.51.31.90"), null);
  }

  @Test
  public void testComparators() {
    DnsItem i1 = new DnsItem(new AdresseIP("193.51.31.90"), new NomMachine("poste.uvsq.fr"));
    DnsItem i2 = new DnsItem(new AdresseIP("193.51.31.91"), new NomMachine("ecampus.uvsq.fr"));
    assertTrue(DnsItem.PAR_NOM.compare(i2, i1) < 0);
    assertTrue(DnsItem.PAR_ADRESSE.compare(i1, i2) < 0);
  }
}
