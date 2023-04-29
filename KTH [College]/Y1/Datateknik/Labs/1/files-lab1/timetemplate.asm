  # timetemplate.asm
  # Written 2015 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

.macro	PUSH (%reg)
	addi	$sp,$sp,-4
	sw	%reg,0($sp)
.end_macro

.macro	POP (%reg)
	lw	%reg,0($sp)
	addi	$sp,$sp,4
.end_macro

.data
	.align 2
mytime:	.word 0x5957
timstr:	.ascii "text more text lots of text\0"
colon: 	.byte 0x3A
nullCh: .byte 0x00
const: 	.word 4711
N: 	.byte 0x4E
I:	.byte 0x49
E:	.byte 0x45

.text
main:
	# print timstr
	la	$a0,timstr
	li	$v0,4
	syscall
	nop
	# wait a little
	li	$a0,100
	jal	delay
	nop
	# call tick
	la	$a0,mytime
	jal	tick
	nop
	# call your function time2string
	la	$a0,timstr
	la	$t0,mytime
	lw	$a1,0($t0)
	jal	time2string
	nop
	# print a newline
	li	$a0,10
	li	$v0,11
	syscall
	nop
	# go back and do it all again
	j	main
	nop
# tick: update time pointed to by $a0
tick:	lw	$t0,0($a0)	# get time
	addiu	$t0,$t0,1	# increase
	andi	$t1,$t0,0xf	# check lowest digit
	sltiu	$t2,$t1,0xa	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x6	# adjust lowest digit
	andi	$t1,$t0,0xf0	# check next digit
	sltiu	$t2,$t1,0x60	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa0	# adjust digit
	andi	$t1,$t0,0xf00	# check minute digit
	sltiu	$t2,$t1,0xa00	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x600	# adjust digit
	andi	$t1,$t0,0xf000	# check last digit
	sltiu	$t2,$t1,0x6000	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa000	# adjust last digit
tiend:	sw	$t0,0($a0)	# save updated result
	jr	$ra		# return
	nop

  # you can write your code for subroutine "hexasc" below this line
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
	
delay:
	lw $t0, const		# set i = delay time
	blez $a0, exit		# exit loop if ms left are 0 or less
	addi $a0, $a0, -1	# subtract 1 ms
	j delayLoop
	
delayLoop:
	addi $t0, $t0, -1	# i--
	bgez $t0, delayLoop	# loop back to inner loop if i >= 0 
	j delay			# loop back to outer loop
	nop
	
exit:
	jr $ra
	nop
	
time2string:
	PUSH $ra
	PUSH $s0
	
	move $s0, $a0
	
	and $a1, $a1, 0xFFFF	# delete all bytes except two least significant bytes
	
	srl $a0, $a1, 12	# move first minutes digit to the right
	jal hexasc		# convert first minutes digit to ASCII
	sb $v0, 0($s0)		# PUSH first minutes digit to stack
	
	srl $a0, $a1, 8		# move second minutes digit to the right	
	jal hexasc		# convert second minutes digit to ASCII
	sb $v0, 1($s0)
	
	lb $t0, colon		# move colon to the right
	sb $t0, 2($s0)
	
	srl $a0, $a1, 4		# move first seconds digit to the right
	jal hexasc		# convert first seconds digit to ASCII
	sb $v0, 3($s0)
	
	srl $a0, $a1, 0		# move second seconds digit to the right
	and $a0, $a0, 0xF	# clean up nibbles
	beq $a0, 9, nine2string
	
	jal hexasc		# convert second seconds digit to ASCII
	sb $v0, 4($s0)
	
	lb $t0, nullCh		# move first minutes digit to the right
	sb $t0, 5($s0)
	
	POP $s0
	POP $ra
	jr $ra			# return
	nop

nine2string:
	lb $t0, N		
	sb $t0, 4($s0)
	
	lb $t0, I		
	sb $t0, 5($s0)
	
	lb $t0, N	
	sb $t0, 6($s0)
	
	lb $t0, E	
	sb $t0, 7($s0)
	
	lb $t0, nullCh		
	sb $t0, 8($s0)
	
	POP $s0
	POP $ra
	jr $ra			# return
	nop
