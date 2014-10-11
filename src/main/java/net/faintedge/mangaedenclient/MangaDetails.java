package net.faintedge.mangaedenclient;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;
import net.faintedge.mangaedenclient.internal.Cast;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class MangaDetails {

  private final String alias;
  private final String[] categories;
  private final Object[][] chapters;
  private final long created;
  private final String description;
  private final long hits;
  private final String image;
  private final String title;
  @SerializedName("title_kw")
  private final String[] titleKeywords;
  private final int status;
  private final String startsWith;

  public MangaDetails(String alias, String[] categories, Chapter[] chapters, long created, String description,
                      long hits, String image, String title, String[] titleKeywords, int status, String startsWith) {
    this.alias = alias;
    this.categories = categories;
    this.chapters = chapterArrayToObjectArrays(chapters);
    this.created = created;
    this.description = description;
    this.hits = hits;
    this.image = image;
    this.title = title;
    this.titleKeywords = titleKeywords;
    this.status = status;
    this.startsWith = startsWith;
  }

  public String getAlias() {
    return alias;
  }

  public String[] getCategories() {
    return categories;
  }

  public Chapter[] getChapters() {
    return objectArraysToChapterArray(chapters);
  }

  public long getCreated() {
    return created;
  }

  public String getDescription() {
    return description;
  }

  public long getHits() {
    return hits;
  }

  public String getImage() {
    return image;
  }

  public String getTitle() {
    return title;
  }

  public String[] getTitleKeywords() {
    return titleKeywords;
  }

  public int getStatus() {
    return status;
  }

  public String getStartsWith() {
    return startsWith;
  }

  private Object[][] chapterArrayToObjectArrays(Chapter[] input) {
    Object[][] result = new Object[input.length][4];
    for (int i = 0; i < input.length; i++) {
      Chapter chapter = input[i];
      result[i][0] = chapter.getNumber();
      result[i][1] = new Long(chapter.getDate()).doubleValue();
      result[i][2] = chapter.getTitle();
      result[i][3] = chapter.getId();
    }
    return result;
  }

  private Chapter[] objectArraysToChapterArray(Object[][] input) {
    Chapter[] result = new Chapter[input.length];
    for (int i = 0; i < input.length; i++) {
      Object[] chapter = input[i];
      result[i] = new Chapter(Cast.double2int(chapter[0]),
        Cast.double2long(chapter[1]), (String) chapter[2], (String) chapter[3]);
    }

    Arrays.sort(result, new Comparator<Chapter>() {
      @Override
      public int compare(Chapter o1, Chapter o2) {
        return o1.getNumber() - o2.getNumber();
      }
    });

    return result;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("alias", alias)
      .add("categories", Arrays.toString(categories))
      .add("created", created)
      .add("hits", hits)
      .add("image", image)
      .add("title", title)
      .add("titleKeywords", titleKeywords)
      .add("status", status)
      .add("startsWith", startsWith)
      .toString();
  }
}
