package ch.study.urlshortener.api.dto;

import ch.study.urlshortener.domain.Url;
import lombok.Getter;

@Getter
public class UrlResponse {
  private String id;
  private String shortenUrl;
  private String originUrl;

  private UrlResponse(Url url) {
    this.id = url.getId();
    this.shortenUrl = url.getShortenUrl();
    this.originUrl = url.getOriginUrl();
  }

  public static UrlResponse of(Url url) {
    return new UrlResponse(url);
  }
}
