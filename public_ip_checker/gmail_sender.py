import base64
from email.mime.audio import MIMEAudio
from email.mime.base import MIMEBase
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import mimetypes
import os
import argparse
#from __future__ import print_function
import httplib2
import os

from apiclient import discovery
import oauth2client
from oauth2client import client
from oauth2client import tools

from apiclient import errors


SCOPES = 'https://www.googleapis.com/auth/gmail.compose'
APPLICATION_NAME = 'Gmail API Python Quickstart'

# try:
#     import argparse
#     flags = argparse.ArgumentParser(parents=[tools.argparser]).parse_args()
# except ImportError:
#     flags = None
flags=None

def SendMessage(service, user_id, message):
  """Send an email message.

  Args:
    service: Authorized Gmail API service instance.
    user_id: User's email address. The special value "me"
    can be used to indicate the authenticated user.
    message: Message to be sent.

  Returns:
    Sent Message.
  """
  try:
    message = (service.users().messages().send(userId=user_id, body=message)
               .execute())
    print 'Message Id: %s' % message['id']
    return message
  except errors.HttpError, error:
    print 'An error occurred: %s' % error


def CreateMessage(sender, to, subject, message_text):
  """Create a message for an email.

  Args:
    sender: Email address of the sender.
    to: Email address of the receiver.
    subject: The subject of the email message.
    message_text: The text of the email message.

  Returns:
    An object containing a base64url encoded email object.
  """
  message = MIMEText(message_text)
  message['to'] = to
  message['from'] = sender
  message['subject'] = subject
  return {'raw': base64.urlsafe_b64encode(message.as_string())}


def get_credentials():
    """Gets valid user credentials from storage.

    If nothing has been stored, or if the stored credentials are invalid,
    the OAuth2 flow is completed to obtain the new credentials.

    Returns:
        Credentials, the obtained credential.
    """
    home_dir = os.path.expanduser('~')
    credential_dir = os.path.join(home_dir, '.credentials')
    if not os.path.exists(credential_dir):
        os.makedirs(credential_dir)
    credential_path = os.path.join(credential_dir,
                                   'sendEmail.json')
    print(credential_path)
    store = oauth2client.file.Storage(credential_path)
    credentials = store.get()
    if not credentials or credentials.invalid:
        flow = client.flow_from_clientsecrets(CLIENT_SECRET_FILE, SCOPES)
        flow.user_agent = APPLICATION_NAME
        if flags:
            credentials = tools.run_flow(flow, store, flags)
        else: # Needed only for compatibility with Python 2.6
            credentials = tools.run(flow, store)
        print('Storing credentials to ' + credential_path)
    return credentials

def main(sender, to, subject, secret):
    SEND_FROM=sender
    SEND_TO=to
    CLIENT_SECRET_FILE = secret
    SUBJECT=subject
    MESSAGE='Message generated automatically. If it was not intended for you, please respond to the message to tell us that you received it and destroy the original message.'

    try:
        credentials = get_credentials()
        http = credentials.authorize(httplib2.Http())
        service = discovery.build('gmail', 'v1', http=http)
        SendMessage(service, "me", CreateMessage(SEND_FROM, SEND_TO, SUBJECT, MESSAGE))

    except Exception, e:
        print e
        raise

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Sends secure email using Gmail.')
    parser.add_argument('--to', help='To whom you want to send the mail')
    parser.add_argument('--sender', help='The sender of the mail')
    parser.add_argument('--subject', help='Subject of the message')
    parser.add_argument('--secret', help='Path of the OAuth2.0 Client ID credentials file')
    args = parser.parse_args()
    main(args.sender, args.to, args.subject, args.secret)