package net.faintedge.mangaedenclient;

import java.net.URI;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
public class ChapterPage {

  private int number;
  private String image;
  private int width;
  private int height;

  public ChapterPage(int number, String image, int width, int height) {
    this.number = number;
    this.image = image;
    this.width = width;
    this.height = height;
  }

  public URI getImageURI() {
    return MangaEden.chapterPage2ImageURI(this);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ChapterPage{");
    sb.append("number=").append(number);
    sb.append(", image='").append(image).append('\'');
    sb.append(", width=").append(width);
    sb.append(", height=").append(height);
    sb.append('}');
    return sb.toString();
  }
}
