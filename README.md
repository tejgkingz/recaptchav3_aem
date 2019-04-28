# recaptchav3_aem
Google reCAPTCHAv3 in AEM Forms

reCAPTCHA v3 provides two ways of validation one from front end and other from back end.
It loads a unique token for each every site visits where it is placed. This token will be validated with google server to identify the type of user interaction with your site visit. Google will return the response for your validation with score which should be always greater than 0.5, lesser than 0.5 will be identified as bot interaction. It also discards the form submit or user interaction within specified time limit and duplicate request at same time.

File details:
button.html - used to identify the action id

customrecaptcha.html - used to load the unique google front end token - place the code under your customrecaptcha component

GetRecaptchaResponseServlet.java - used to validate the token with google server

validate.js - used to validate the google server response.

