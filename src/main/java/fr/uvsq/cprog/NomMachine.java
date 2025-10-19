package fr.uvsq.cprog;

import java.util.Objects;

public final class NomMachine implements Comparable<NomMachine> {
  private final String fqdn;

    public NomMachine(String fqdn) {
    if (fqdn == null) throw new IllegalArgumentException("Nom nul");
    String v = fqdn.trim().toLowerCase();
    int idx = v.indexOf('.');
    if (idx <= 0 || idx == v.length() - 1 || v.endsWith(".")) throw new IllegalArgumentException("FQDN invalide");
    this.fqdn = v;
}

  public String getMachine() {
    int idx = fqdn.indexOf('.');
    return fqdn.substring(0, idx);
  }

  public String getDomaine() {
    int idx = fqdn.indexOf('.');
    return fqdn.substring(idx + 1);
  }

  public String toString() {
    return fqdn;
  }

  public int compareTo(NomMachine other) {
    return this.getMachine().compareTo(other.getMachine());
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NomMachine)) return false;
    NomMachine that = (NomMachine) o;
    return fqdn.equals(that.fqdn);
  }

  public int hashCode() {
    return Objects.hash(fqdn);
  }
}
