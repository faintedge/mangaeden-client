package net.faintedge.mangaedenclient;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
public class Manga {

  @SerializedName("im")
  private String image;

  @SerializedName("t")
  private String title;

  @SerializedName("i")
  private String id;

  @SerializedName("a")
  private String alias;

  @SerializedName("s")
  private int status;

  @SerializedName("ld")
  private double lastChapterDate;

  @SerializedName("h")
  private long hits;


  public Manga(String image, String title, String id, String alias, int status,
               double lastChapterDate, long hits) {
    this.image = image;
    this.title = title;
    this.id = id;
    this.alias = alias;
    this.status = status;
    this.lastChapterDate = lastChapterDate;
    this.hits = hits;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Manga manga = (Manga) o;

    if (hits != manga.hits) return false;
    if (Double.compare(manga.lastChapterDate, lastChapterDate) != 0) return false;
    if (status != manga.status) return false;
    if (alias != null ? !alias.equals(manga.alias) : manga.alias != null) return false;
    if (id != null ? !id.equals(manga.id) : manga.id != null) return false;
    if (image != null ? !image.equals(manga.image) : manga.image != null) return false;
    if (title != null ? !title.equals(manga.title) : manga.title != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = image != null ? image.hashCode() : 0;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (alias != null ? alias.hashCode() : 0);
    result = 31 * result + status;
    temp = Double.doubleToLongBits(lastChapterDate);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (int) (hits ^ (hits >>> 32));
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Manga{");
    sb.append("image='").append(image).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", id='").append(id).append('\'');
    sb.append(", alias='").append(alias).append('\'');
    sb.append(", status=").append(status);
    sb.append(", lastChapterDate=").append(lastChapterDate);
    sb.append(", hits=").append(hits);
    sb.append('}');
    return sb.toString();
  }
}
