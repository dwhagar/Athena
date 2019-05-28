package com.blazingumbra.athena.wiki.interaction;

import com.blazingumbra.athena.wiki.objects.EditResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class CreatePageWikiInteraction extends AbstractWikiInteraction{
    public static EditResponse execute(WikiSession aWikiSession, PageProperty pageProperty) throws IOException, URISyntaxException {
        String title;
        String content;
        String csrfToken;

        title = pageProperty.getPageKey();
        content = pageProperty.getContent();
        csrfToken = pageProperty.getCsrfToken();

        String encodedTitle = URLEncoder.encode(title, "UTF-8");
        String encodedContent = URLEncoder.encode(content, "UTF-8");
        String encodedToken = URLEncoder.encode(csrfToken, "UTF-8");
        String pageContentAfterExecution;

        String entityLiteral = "action=edit&format=json&title="+encodedTitle+"&text="+encodedContent+"&bot=1&token="+encodedToken;
        HttpEntity entity = new StringEntity(entityLiteral);
        URIBuilder uriBuilder = new URIBuilder(aWikiSession.getBaseURI());
        URI anURI = uriBuilder
                .build();
        HttpPost post = new HttpPost(anURI);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(entity);
        pageContentAfterExecution = aWikiSession.getResult(post);
        return gson.fromJson(pageContentAfterExecution, EditResponse.class);
    }
}
