# Gmail sender
Simple Python script to send mails using the Gmail API with OAuth2.0.

Based on:
https://developers.google.com/gmail/api/quickstart/python

### 1. Enable the Gmail API

Go to [Google API Console](https://console.developers.google.com/apis) and create a new project. Navigate to "Credentials" and 
fill the OAuth consent screen to get the credentials: a clientID and a client secret.

Download these credentials to a JSON file, which should look something like this:
```json
{
  "installed": {
    "client_id": "012345678901-lksdjf9023jklj87u2sd3lkj87o2lkjj.apps.googleusercontent.com",
    "project_id": "flying-octopus-649874",
    "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    "token_uri": "https://oauth2.googleapis.com/token",
    "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    "client_secret": "D2ye7ijln83Dqu7op6oub93N",
    "redirect_uris": [
      "urn:ietf:wg:oauth:2.0:oob",
      "http://localhost"
    ]
  }
}
```
This file contains your application secret, treat it like a password. **Don't share it**.

Name the file "credentials.json" and save it in the same directory as the "send_gmail.py" script.

### 2. Install the Google client library
```
pip install --upgrade google-api-python-client google-auth-httplib2 google-auth-oauthlib
```
As well as any other required dependencies

### 3. Run the application

From the Linux command line:
```
python send_mail.py --to example@gmail.com --subject "Flying octopus sending mail" --content "Content of the message"
```

The first time it runs, a pop-up browser will appear to log in to your Gmail account and allow access for the application.
After the first successful login, the following interactions will not require human intervention, as the access token is downloaded and saved in a file called "token.pickle".
