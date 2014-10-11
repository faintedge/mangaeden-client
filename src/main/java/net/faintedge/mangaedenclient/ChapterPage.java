package net.faintedge.mangaedenclient;

import com.google.common.base.Objects;

/**
 *
 */
public class ChapterPage {

  private final int number;
  private final String image;
  private final int width;
  private final int height;

  public ChapterPage(int number, String image, int width, int height) {
    this.number = number;
    this.image = image;
    this.width = width;
    this.height = height;
  }

  public int getNumber() {
    return number;
  }

  public String getImage() {
    return image;
  }

  public String getImageURL() {
    return "http://cdn.mangaeden.com/mangasimg/" + image;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("number", number)
      .add("imageURL", getImageURL())
      .add("width", width)
      .add("height", height)
      .toString();
  }
}
