package net.faintedge.mangaedenclient;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
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

  private final AsyncHttpClient httpClient;
  private final URI baseURI;

  public MangaEdenClient(URI baseURI) {
    this.baseURI = baseURI;
    this.httpClient = new AsyncHttpClient();
  }

  public MangaEdenClient() {
    this(DEFAULT_MANGA_EDEN_URI);
  }

  public List<Manga> getMangaList() throws Exception {
    return getMangaList(Optional.<Integer>absent());
  }

  public List<Manga> getMangaList(Optional<Integer> page) throws Exception {
    String suffix = page.isPresent() ? "?p=" + page.get() : "";
    URI uri = baseURI.resolve("/api/list/0/" + suffix);
    Response response = httpClient.prepareGet(uri.toString()).execute().get();
    String responseBody = response.getResponseBody();
    MangaListResponse mangaListResponse = GSON.fromJson(responseBody, MangaListResponse.class);
    return mangaListResponse.getManga();
  }

  public MangaDetails getMangaDetails(String mangaId) throws Exception {
    URI uri = baseURI.resolve("/api/manga/" + mangaId + "/");
    Response response = httpClient.prepareGet(uri.toString()).execute().get();
    String responseBody = response.getResponseBody();
    return GSON.fromJson(responseBody, MangaDetails.class);
  }

  public ChapterDetails getChapterDetails(String chapterId) throws Exception {
    URI uri = baseURI.resolve("/api/chapter/" + chapterId + "/");
    Response response = httpClient.prepareGet(uri.toString()).execute().get();
    String responseBody = response.getResponseBody();
    return GSON.fromJson(responseBody, ChapterDetails.class);
  }

}
