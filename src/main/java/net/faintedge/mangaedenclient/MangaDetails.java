package net.faintedge.mangaedenclient;

import com.google.gson.annotations.SerializedName;
import net.faintedge.mangaedenclient.internal.Cast;

import java.net.URI;
import java.util.Arrays;
import java.util.Comparator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
public class MangaDetails {

  private String alias;
  private String[] categories;
  private Object[][] chapters;
  private long created;
  private String description;
  private long hits;
  private String image;
  private String title;
  @SerializedName("title_kw")
  private String[] titleKeywords;
  private int status;
  private String startsWith;

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
  
  public Chapter[] getChapters() {
    return objectArraysToChapterArray(chapters);
  }  
  
  public URI getURI() {
    return MangaEden.mangaDetails2URI(this);
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
    final StringBuilder sb = new StringBuilder("MangaDetails{");
    sb.append("alias='").append(alias).append('\'');
    sb.append(", categories=").append(Arrays.toString(categories));
    sb.append(", chapters=").append(Arrays.toString(chapters));
    sb.append(", created=").append(created);
    sb.append(", description='").append(description).append('\'');
    sb.append(", hits=").append(hits);
    sb.append(", image='").append(image).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", titleKeywords=").append(Arrays.toString(titleKeywords));
    sb.append(", status=").append(status);
    sb.append(", startsWith='").append(startsWith).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
