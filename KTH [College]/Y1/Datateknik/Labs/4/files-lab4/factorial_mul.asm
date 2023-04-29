.text

	addi $a0, $0, 4		# set $a0 to n
	
	addi $a1, $0, 0		# $a1 holds current iteration of the outer loop
	addi $v0, $0, 1 	# $v0 holds final result
	
loop:	
	beq $a1, $a0, done	# end outer loop when $a1 = $a0
	add $0, $0, $0		# nop
	
	addi $a1, $a1, 1	# increments $a1 by 1
	
	mul $v0, $v0, $a1
	
	beq $0, $0, loop	# unconditional loop
	add $0, $0, $0
done:	
	beq $0, $0, done
	add $0, $0, $0
