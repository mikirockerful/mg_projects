#!/bin/python

import os
import time
import send_gmail

i=1

while 1:

	result = os.system('selenium-side-runner -c "browserName=firefox" --output-format=jest dgt_madrid.side')

	if result == 0:
		# Hay cita disponible. Alertar.
		send_gmail.main('USUARIO@gmail.com' , 'Cita previa DGT tramites de oficina disponible en Madrid', 'Enviado por dgt_checker')

	time.sleep(300)
	i=i+1
