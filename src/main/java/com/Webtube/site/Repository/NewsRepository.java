package com.Webtube.site.Repository;

import com.Webtube.site.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
// Custom queries if needed
}
