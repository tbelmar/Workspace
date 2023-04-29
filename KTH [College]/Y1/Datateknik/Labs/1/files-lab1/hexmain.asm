  # hexmain.asm
  # Written 2015-09-04 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

	.text
main:
	li	$a0, 17		# change this to test different values

	jal	hexasc		# call hexasc
	nop			# delay slot filler (just in case)	

	move	$a0,$v0		# copy return value to argument register

	li	$v0,11		# syscall with v0 = 11 will print out
	syscall			# one byte from a0 to the Run I/O window

stop:	j	stop		# stop after one run
	nop			# delay slot filler (just in case)

  # You can write your own code for hexasc here
hexasc:
	and $a0, $a0, 0xF	# clean up nibbles
	ble $a0, 9, integer 	# if less than or equal to 9, convert to ASCII number
	nop
	
	add $v0, $a0, 0x37	# convert to ASCII value for hex digit
	jr $ra
	nop			# return
integer:
	add $v0, $a0, 0x30	# convert to ASCII value for hex digit
	jr $ra
	nop		# return		
	
	
	
	
