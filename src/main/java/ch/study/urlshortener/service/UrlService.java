package ch.study.urlshortener.service;

import ch.study.urlshortener.api.dto.UrlResponse;
import ch.study.urlshortener.domain.UrlRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

  private final UrlRepository urlRepository;

  public UrlService(UrlRepository urlRepository) {
    this.urlRepository = urlRepository;
  }

  public List<UrlResponse> convertOriginUrl(String shortenUrl) {
    return urlRepository.findByShortenUrl(shortenUrl).stream().map(UrlResponse::of).collect(
        Collectors.toList());
  }

  public UrlResponse getUrl(String id) {
    return null;
  }
}
