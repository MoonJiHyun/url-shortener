package ch.study.urlshortener.api.controller;

import ch.study.urlshortener.api.dto.UrlResponse;
import ch.study.urlshortener.service.UrlService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

  private UrlService urlService;

  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }

  @GetMapping("/{id}")
  public UrlResponse read(@PathVariable String id) {
    return urlService.getUrl(id);
  }

  @GetMapping("/shorten/{shortenUrl}")
  public List<UrlResponse> list(@PathVariable String shortenUrl) {
    return urlService.convertOriginUrl(shortenUrl);
  }
//
//  @PostMapping
//  public String create(@RequestBody @Valid CommentPostRequest commentPostRequest) {
//    return urlService.createComment(commentPostRequest);
//  }
//
//  @PutMapping("/{id}")
//  public void update(@PathVariable String id, @RequestBody @Valid CommentPutRequest commentPutRequest) {
//    urlService.updateComment(id, commentPutRequest);
//  }
//
//  @DeleteMapping("/{id}")
//  public void delete(@PathVariable String id) {
//    urlService.deleteComment(id);
//  }
}
