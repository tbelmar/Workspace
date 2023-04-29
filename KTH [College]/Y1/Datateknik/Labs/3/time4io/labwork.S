  # labwork.S
  # Written 2015-2017 by F Lundevall
  # Skeleton file for IS1200/IS1500 lab 1.
  # The macros PUSH and POP are in the public domain.
  # Please add your own code at the end of the file.

  #
  # Please keep the two macros PUSH and POP unchanged
  #
.macro	PUSH reg
	addi	$sp,$sp,-4
	sw	\reg,0($sp)
.endm

.macro	POP reg
	lw	\reg,0($sp)
	addi	$sp,$sp,4
.endm
  #
  # Please add your own code below this line
  #
  	.data

colon: 	.byte 0x3A
nullCh: .byte 0x00
const: 	.word 8900
N: 	.byte 0x4E
I:	.byte 0x49
E:	.byte 0x45
	.text			# return
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

.globl delay	
delay:
	#lw $t0, const		# set i = delay time
	li $t0, 4711
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

.globl time2string
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