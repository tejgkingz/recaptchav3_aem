
package com.aeminmyway.core.servlets;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*** @author Teja G**/
@SlingServlet(paths = "/bin/aeminmyway/getRecaptchaResponseServlet") 
public class GetRecaptchaResponseServlet extends SlingAllMethodsServlet {
	 private static final long serialVersionUID = 1 L;
	 private static final String RESPONSETYPE = "application/json";
	 private static final Logger log = LoggerFactory.getLogger(GetRecaptchaResponseServlet.class);
	 private static final String UTF8 = "UTF-8"; 
	 @Override protected void doPost(final SlingHttpServletRequest req, 
	 	final SlingHttpServletResponse resp)throws ServletException, IOException {
	  log.debug(":::::::::::::: GetRecaptchaResponseServlet GET() Starts :::::::::::::");
		  String response = "";
		  response = getResponse(req);
		  resp.setContentType(RESPONSETYPE);
		  resp.getWriter().write(response);
	  log.debug(":::::::::::::: GetRecaptchaResponseServlet GET() Ends :::::::::::::");
	 }
	 public String getResponse(SlingHttpServletRequest req) {
	  String response = "[]";
	  String str = req.getParameter("g-recaptcha-response") != null ? req.getParameter("g-recaptcha-response") : "";
	  String configUrl = "https://www.google.com/recaptcha/api/siteverify?secret=<your secret key>&response=" + str;
	  try {
	   log.debug("::: Params :: token:: " + str);
		   if (str != null) {
			    String baseUrl = configUrl;
			    CloseableHttpClient client = null;
			    HttpClientBuilder httpClientBuilder = null;
			    httpClientBuilder = HttpClientBuilder.create();
			    client = httpClientBuilder.build();
			    HttpGet request = new HttpGet(baseUrl);
			    HttpResponse response1 = client.execute(request);
			    if(response1 != null && response1.getEntity() != null) {
				     HttpEntity entity = response1.getEntity();
				     response = EntityUtils.toString(entity, UTF8);
			    } log.info("Response::: " + response);
		   }
	  } catch (Exception e) {
	   log.error("Error in processing http request ::: ");
	  }
	  return response;
	 }
}