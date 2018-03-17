import requests
import sys
import gmail_sender

SENDER='***********'
TO='*************'
SECRET='*********'

IP_FINDER_SERVICE_URL='https://api.ipify.org/?format=json'
IP_FILE='public_ip.txt'

def check_public_ip():
	try:
		r=requests.get(IP_FINDER_SERVICE_URL)
		r.raise_for_status()
	except Exception as e:
		print('The following exception(s) occurred: ')
		print(e)
		sys.exit(1)
	return r.json()['ip']


#Check IP in file
try:
	with open(IP_FILE, 'r') as f:
		prev_ip=f.readline().rstrip()
except (OSError, IOError) as e: # FileNotFoundError does not exist on Python < 3.3
	print(e)
	print('Error reading the file. It will be overwritten or created')
	prev_ip=None
	#sys.exit(1)

my_ip=check_public_ip()

if prev_ip==my_ip:
	print('Public IP has not changed since last execution')
else:
	#Send new address
	gmail_sender.main( SENDER , TO , 'My public IP is '+my_ip, SECRET)

	#Persist changes
	with open(IP_FILE, 'w') as f:
		f.write(my_ip)