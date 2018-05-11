# MangaEden Client

Java client for the MangaEden API: https://www.mangaeden.com/api/

## Usage

```java
MangaEdenClient client = new ManagaEdenClient();

// Get list of manga
List<Manga> mangaList = client.getMangaList();
Manga manga = mangaList.get(0);

// Get manga details
MangaDetails mangaDetails = client.getMangaDetails(manga.getId());
Chapter[] chapters = mangaDetails.getChapters();

// Get chapter details
ChapterDetails chapterDetails = client.getChapterDetails(chapters[0].getId());
ChapterPage[] pages = chapterDetails.getPages();

// Get chapter page image URLs
String imageUrl = pages[0];
```
