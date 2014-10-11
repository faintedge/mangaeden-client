package net.faintedge.mangaedenclient;

import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IntegrationTest {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationTest.class);

  @Test
  public void testGetMangaList() throws Exception {
    MangaEdenClient client = getClient();
    List<Manga> mangaList = client.getMangaList();
    Assert.assertFalse(mangaList.isEmpty());

    Iterable<Manga> limitedMangaList = Iterables.limit(mangaList, 10);
    for (Manga manga : limitedMangaList) {
      LOG.info(manga.toString());
    }
  }

  @Test
  public void testGetMangaInfo() throws Exception {
    MangaEdenClient client = getClient();
    String mangaId = "4e70e91ac092255ef70016d6";
    MangaDetails mangaDetails = client.getMangaDetails(mangaId);
    Assert.assertNotNull(mangaDetails);
    Assert.assertNotNull(mangaDetails.getChapters());

    LOG.info(mangaDetails.toString());
    Chapter[] chapters = mangaDetails.getChapters();
    for (int i = 0; i < Math.min(chapters.length, 10); i++) {
      LOG.info(chapters[i].toString());
    }
  }

  @Test
  public void testGetChapterPage() throws Exception {
    MangaEdenClient client = getClient();
    ChapterDetails chapterDetails = client.getChapterDetails("4e711f96c09225616d03b3ba");
    Assert.assertNotNull(chapterDetails);
    Assert.assertNotNull(chapterDetails.getPages());

    ChapterPage[] pages = chapterDetails.getPages();
    for (int i = 0; i < Math.min(pages.length, 10); i++) {
      LOG.info(pages[i].toString());
    }
  }

  private MangaEdenClient getClient() {
    return new MangaEdenClient();
  }
}