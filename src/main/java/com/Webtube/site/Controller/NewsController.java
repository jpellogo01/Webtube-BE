package com.Webtube.site.Controller;


import com.Webtube.site.Exception.NewsNotFoundException;
import com.Webtube.site.Model.News;
import com.Webtube.site.Repository.NewsRepository;
import com.Webtube.site.Repository.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_AUTHOR')")
@RequestMapping("/api/v1")
public class NewsController<NewsService> {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/news")
    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    @PostMapping("/news")
    public News createNews( @RequestBody News news) {
        return newsRepository.save(news);
    }





    //Get news content by id
    @GetMapping("/news/{id}")
    public ResponseEntity<News>
    getNewsById(@PathVariable(value = "id")long newsId){
        News news = newsRepository.findById(newsId).orElseThrow(
                () -> new NewsNotFoundException("News Content not found for this Id :: " + newsId));
        return ResponseEntity.ok().body(news);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(/*@AuthenticationPrincipal UserDetails userDetails,*/
                                           @PathVariable(value = "id") long newsId,
                                           @RequestBody @NotNull News newsDetails)
            throws NewsNotFoundException {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News Content not found for this Id :: " + newsId));

        // Update news details
        news.setTitle(newsDetails.getTitle());
        news.setThumbnailUrl(newsDetails.getThumbnailUrl());
        news.setAuthor(newsDetails.getAuthor());
        news.setDescription(newsDetails.getDescription());
        news.setContent(newsDetails.getContent());
        news.setCategory(newsDetails.getCategory());

        newsRepository.save(news);

        return ResponseEntity.ok().body(news);
    }

    //Delete News Content by id
    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(value = "id") long newsId) throws NewsNotFoundException{
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News Content not found for this Id :: " + newsId));

        newsRepository.deleteById(newsId);
        return ResponseEntity.ok().build();
    }

}
