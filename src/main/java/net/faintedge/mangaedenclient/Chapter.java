package net.faintedge.mangaedenclient;

import com.google.common.base.Objects;

/**
 *
 */
public class Chapter {

  private final int number;
  private final long date;
  private final String title;
  private final String id;

  public Chapter(int number, long date, String title, String id) {
    this.number = number;
    this.date = date;
    this.title = title;
    this.id = id;
  }

  public int getNumber() {
    return number;
  }

  public long getDate() {
    return date;
  }

  public String getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("number", number)
      .add("date", date)
      .add("title", title)
      .add("id", id)
      .toString();
  }
}
