package fr.uvsq.cprog;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {
  @Test
  public void testToString() {
    AdresseIP ip = new AdresseIP("192.168.0.1");
    assertEquals("192.168.0.1", ip.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AdresseIP ip1 = new AdresseIP("10.0.0.1");
    AdresseIP ip2 = new AdresseIP("10.0.0.1");
    AdresseIP ip3 = new AdresseIP("10.0.0.2");
    assertEquals(ip1, ip2);
    assertEquals(ip1.hashCode(), ip2.hashCode());
    assertNotEquals(ip1, ip3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFormat() {
    new AdresseIP("192.168.1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOctet() {
    new AdresseIP("256.0.0.1");
  }

  @Test
  public void testCompareTo() {
    AdresseIP ip1 = new AdresseIP("10.0.0.1");
    AdresseIP ip2 = new AdresseIP("10.0.0.2");
    AdresseIP ip3 = new AdresseIP("10.0.1.1");
    assertTrue(ip1.compareTo(ip2) < 0);
    assertTrue(ip2.compareTo(ip1) > 0);
    assertTrue(ip3.compareTo(ip2) > 0);
  }
}
