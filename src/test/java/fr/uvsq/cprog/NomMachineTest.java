package fr.uvsq.cprog;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {
  @Test
  public void testToString() {
    NomMachine nom = new NomMachine("www.uvsq.fr");
    assertEquals("www.uvsq.fr", nom.toString());
  }

  @Test
  public void testGetMachineAndDomaine() {
    NomMachine nom = new NomMachine("poste.uvsq.fr");
    assertEquals("poste", nom.getMachine());
    assertEquals("uvsq.fr", nom.getDomaine());
  }

  @Test
  public void testEqualsAndHashCode() {
    NomMachine n1 = new NomMachine("www.uvsq.fr");
    NomMachine n2 = new NomMachine("www.uvsq.fr");
    NomMachine n3 = new NomMachine("ecampus.uvsq.fr");
    assertEquals(n1, n2);
    assertEquals(n1.hashCode(), n2.hashCode());
    assertNotEquals(n1, n3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFormatNoDot() {
    new NomMachine("uvsqfr");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFormatDotAtStart() {
    new NomMachine(".uvsq.fr");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFormatDotAtEnd() {
    new NomMachine("uvsq.fr.");
  }

  @Test
  public void testCompareTo() {
    NomMachine n1 = new NomMachine("poste.uvsq.fr");
    NomMachine n2 = new NomMachine("ecampus.uvsq.fr");
    assertTrue(n2.compareTo(n1) < 0);
    assertTrue(n1.compareTo(n2) > 0);
  }
}
