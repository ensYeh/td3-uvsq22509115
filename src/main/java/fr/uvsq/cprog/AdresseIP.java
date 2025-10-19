package fr.uvsq.cprog;

import java.util.Objects;

public final class AdresseIP implements Comparable<AdresseIP> {
  private final int a;
  private final int b;
  private final int c;
  private final int d;

  public AdresseIP(String ip) {
    String[] parts = ip == null ? new String[0] : ip.trim().split("\\.");
    if (parts.length != 4) throw new IllegalArgumentException("IP invalide");
    this.a = parseOctet(parts[0]);
    this.b = parseOctet(parts[1]);
    this.c = parseOctet(parts[2]);
    this.d = parseOctet(parts[3]);
  }

  private static int parseOctet(String s) {
    int v = Integer.parseInt(s);
    if (v < 0 || v > 255) throw new IllegalArgumentException("Octet hors plage");
    return v;
  }

  public String toString() {
    return a + "." + b + "." + c + "." + d;
  }

  public int compareTo(AdresseIP other) {
    if (a != other.a) return Integer.compare(a, other.a);
    if (b != other.b) return Integer.compare(b, other.b);
    if (c != other.c) return Integer.compare(c, other.c);
    return Integer.compare(d, other.d);
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AdresseIP)) return false;
    AdresseIP that = (AdresseIP) o;
    return a == that.a && b == that.b && c == that.c && d == that.d;
  }

  public int hashCode() {
    return Objects.hash(a, b, c, d);
  }
}
