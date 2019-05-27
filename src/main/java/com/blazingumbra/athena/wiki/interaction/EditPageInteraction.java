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

public class EditPageInteraction extends AbstractWikiInteraction{
    public EditPageInteraction() {
        super();
    }

    public static EditResponse execute(WikiSession aWikiSession, String parameter) throws IOException, URISyntaxException {
        String[] parameterArray;
        String pageID;
        String content;
        String csrfToken;

        parameterArray = parameter.split(",");
        pageID = parameterArray[0];
        content = parameterArray[1];
        csrfToken = parameterArray[2];

        String encodedID = URLEncoder.encode(pageID, "UTF-8");
        String encodedContent = URLEncoder.encode(content, "UTF-8");
        String encodedToken = URLEncoder.encode(csrfToken, "UTF-8");
        String pageContentAfterExecution;

        String entityLiteral = "action=edit&format=json&pageid="+encodedID+"&text="+encodedContent+"&bot=1&token="+encodedToken;
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
