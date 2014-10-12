package net.faintedge.mangaedenclient;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

/**
 *
 */
public class MangaEdenClient {

  private static final Logger LOG = LoggerFactory.getLogger(MangaEdenClient.class);
  private static final Gson GSON = new Gson();

  private static final URI DEFAULT_MANGA_EDEN_URI = URI.create("http://www.mangaeden.com");

  private final OkHttpClient httpClient;
  private final URI baseURI;

  public MangaEdenClient(URI baseURI) {
    this.baseURI = baseURI;
    this.httpClient = new OkHttpClient();
  }

  public MangaEdenClient() {
    this(DEFAULT_MANGA_EDEN_URI);
  }

  public List<Manga> getMangaList() throws Exception {
    return getMangaList(null);
  }

  public List<Manga> getMangaList(int page) throws Exception {
    return getMangaList(new Integer(page));
  }

  private List<Manga> getMangaList(Integer page) throws Exception {
    String suffix = page != null ? "?p=" + page : "";
    URI uri = baseURI.resolve("/api/list/0/" + suffix);
    Request request = new Request.Builder().get().url(uri.toURL()).build();

    Response response = httpClient.newCall(request).execute();
    String responseBody = response.body().string();
    MangaListResponse mangaListResponse = GSON.fromJson(responseBody, MangaListResponse.class);
    return mangaListResponse.getManga();
  }

  public MangaDetails getMangaDetails(String mangaId) throws Exception {
    URI uri = baseURI.resolve("/api/manga/" + mangaId + "/");
    Request request = new Request.Builder().get().url(uri.toURL()).build();

    Response response = httpClient.newCall(request).execute();
    String responseBody = response.body().string();
    return GSON.fromJson(responseBody, MangaDetails.class);
  }

  public ChapterDetails getChapterDetails(String chapterId) throws Exception {
    URI uri = baseURI.resolve("/api/chapter/" + chapterId + "/");
    Request request = new Request.Builder().get().url(uri.toURL()).build();

    Response response = httpClient.newCall(request).execute();
    String responseBody = response.body().string();
    return GSON.fromJson(responseBody, ChapterDetails.class);
  }

}
