package ch.study.urlshortener.domain;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UrlRepository extends PagingAndSortingRepository<Url, String> {
  List<Url> findByShortenUrl(String shortenUrl);
}
