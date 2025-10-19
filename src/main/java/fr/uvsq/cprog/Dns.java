package fr.uvsq.cprog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;

public class Dns {
  private final Map<AdresseIP, DnsItem> ipMap = new HashMap<>();
  private final Map<NomMachine, DnsItem> nomMap = new HashMap<>();
  private final Path fichier;

  public Dns(Path fichier) throws IOException {
    this.fichier = fichier;
    List<String> lignes = Files.readAllLines(fichier, StandardCharsets.UTF_8);
    for (String ligne : lignes) {
      String[] parts = ligne.trim().split(" ");
      if (parts.length == 2) {
        NomMachine nom = new NomMachine(parts[0]);
        AdresseIP ip = new AdresseIP(parts[1]);
        DnsItem item = new DnsItem(ip, nom);
        ipMap.put(ip, item);
        nomMap.put(nom, item);
      }
    }
  }

  public DnsItem getItem(AdresseIP ip) {
    DnsItem item = ipMap.get(ip);
    if (item == null) throw new NoSuchElementException("IP inconnue");
    return item;
  }

  public DnsItem getItem(NomMachine nom) {
    DnsItem item = nomMap.get(nom);
    if (item == null) throw new NoSuchElementException("Nom inconnu");
    return item;
  }

  public List<DnsItem> getItems(String domaine, Comparator<DnsItem> cmp) {
    List<DnsItem> res = new ArrayList<>();
    for (DnsItem item : nomMap.values()) {
      if (item.getNom().getDomaine().equals(domaine)) {
        res.add(item);
      }
    }
    res.sort(cmp);
    return res;
  }

  public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
    if (ipMap.containsKey(ip)) throw new IllegalArgumentException("IP existe déjà");
    if (nomMap.containsKey(nom)) throw new IllegalArgumentException("Nom existe déjà");
    DnsItem item = new DnsItem(ip, nom);
    ipMap.put(ip, item);
    nomMap.put(nom, item);
    List<String> lignes = new ArrayList<>();
    for (DnsItem i : nomMap.values()) {
      lignes.add(i.getNom().toString() + " " + i.getAdresse().toString());
    }
    Files.write(fichier, lignes, StandardCharsets.UTF_8);
  }
}
