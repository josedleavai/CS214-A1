/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs214_a1draft3;

/**
 *
 * @author Josed D Leavai - S11176243
 * @author Taefalaula Brown - S11188253
 * @author David Palavipaongo - S11130156
 * 
 */
public class Article implements Comparable<Article> {
  int id;
  String TITLE;
  String ABSTRACT;
  int cs, physics, maths, stats, quantBio, quantFinance;

  Article() {
    this.id = 0;
    this.TITLE = "";
    this.ABSTRACT = "";

    this.cs = 0;
    this.physics = 0;
    this.maths = 0;
    this.stats = 0;
    this.quantBio = 0;
    this.quantFinance = 0;
  }

  Article(int id, String TITLE, String ABSTRACT,
      int cs, int physics, int maths, int stats,
      int quantBio, int quantFinance) {
    this.id = id;
    this.TITLE = TITLE;
    this.ABSTRACT = ABSTRACT;
    this.cs = cs;
    this.physics = physics;
    this.maths = maths;
    this.stats = stats;
    this.quantBio = quantBio;
    this.quantFinance = quantFinance;
  }

  // --------------------------
  public int getId() {
    return id;
  }

  public void setId(int ID) {
    this.id = ID;
  }

  // --------------------------
  public String getTITLE() {
    return TITLE;
  }

  public void setTITLE(String TITLE) {
    this.TITLE = TITLE;
  }

  // --------------------------
  public String getABSTRACT() {
    return ABSTRACT;
  }

  public void setABSTRACT(String ABSTRACT) {
    this.ABSTRACT = ABSTRACT;
  }

  // --------------------------
  public int cs() {
    return cs;
  }

  public void setCS(int cs) {
    this.cs = cs;
  }

  // --------------------------
  public int physics() {
    return physics;
  }

  public void setPhysics(int physics) {
    this.physics = physics;
  }

  // --------------------------
  public int maths() {
    return maths;
  }

  public void setMaths(int maths) {
    this.maths = maths;
  }

  // --------------------------
  public int stats() {
    return stats;
  }

  public void setStats(int stats) {
    this.stats = stats;
  }

  // --------------------------
  public int quantBio() {
    return quantBio;
  }

  public void setQuantBio(int quantBio) {
    this.quantBio = quantBio;
  }

  // --------------------------
  public int quantFinance() {
    return quantFinance;
  }

  public void setQuantFinance(int quantFinance) {
    this.quantFinance = quantFinance;
  }

  @Override
  public int compareTo(Article b_obj) {
    return this.TITLE.compareToIgnoreCase(b_obj.TITLE);
  }

  @Override
  public String toString() {
    return "Article ID: " + id + ", Title: " + TITLE;
  }
}
