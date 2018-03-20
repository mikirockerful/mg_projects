import smtplib
import sys
import argparse

def main(args):
	gmail_user = args.account
	gmail_password = args.password
	to = args.to
	subject = args.subject  
	body = args.message


	# email_text = """\  
	# From: %s  
	# To: %s  
	# Subject: %s

	# %s
	# """ % (gmail_user, ", ".join(to), subject, body)

	message = 'Subject: {}\n\n{}'.format(subject, body)

	try:
		server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
		server.ehlo()
		server.login(gmail_user,gmail_password)
		server.sendmail(gmail_user, to, message)
		server.close()
		print('Email sent!')
	except Exception as e:
		print(e)
		print('Something went wrong...')

if __name__ == "__main__":
	parser = argparse.ArgumentParser(description='Sends email using SMTP using Gmail servers')
	parser.add_argument('--account', help='The user account from which to send mail', required=True)
	parser.add_argument('--password', help='Password for the account', required=True)
	parser.add_argument('--subject', help='Subject of the message', required=True)
	parser.add_argument('--message', help='Body of the message', required=True)
	parser.add_argument('--to', nargs='*', help='List of accounts to send the message to, separated by spaces', required=True)
	args = parser.parse_args()
	main(args)