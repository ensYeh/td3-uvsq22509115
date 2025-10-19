package fr.uvsq.cprog;

import java.util.Comparator;
import java.util.Objects;

public final class DnsItem {
  private final AdresseIP adresse;
  private final NomMachine nom;

  public DnsItem(AdresseIP adresse, NomMachine nom) {
    if (adresse == null || nom == null) throw new IllegalArgumentException("Null");
    this.adresse = adresse;
    this.nom = nom;
  }

  public AdresseIP getAdresse() {
    return adresse;
  }

  public NomMachine getNom() {
    return nom;
  }

  public String toString() {
    return adresse.toString() + " " + nom.toString();
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DnsItem)) return false;
    DnsItem that = (DnsItem) o;
    return adresse.equals(that.adresse) && nom.equals(that.nom);
  }

  public int hashCode() {
    return Objects.hash(adresse, nom);
  }

  public static final Comparator<DnsItem> PAR_NOM =
      Comparator.comparing(i -> i.getNom().getMachine());

  public static final Comparator<DnsItem> PAR_ADRESSE =
      Comparator.comparing(DnsItem::getAdresse);
}
